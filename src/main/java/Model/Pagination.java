package Model;

import java.util.ArrayList;
import org.json.JSONObject;

public class Pagination {
	public ArrayList<config> config = new ArrayList<config>();

	private String listString;

	public void init(ArrayList<config> config) {

		String linkbook = "https://bookingapiiiii.herokuapp.com/";

		this.config = config;
		this.config.forEach(data -> {

			if (data.getLimit() < 0) {
				data.setLimit(0);
			}
			// - Nếu người dùng truyền vào số trang nhỏ hơn 1 thì ta sẽ gán nó = 1
			if (data.getCurrent_page() < 1) {
				data.setCurrent_page(1);
			}

			int pages = data.getCurrent_page();
			int last = data.getLimit();

			String api = data.getApi();
			String dataapi = linkbook + api + "/" + pages + "/" + last;
			JSONObject list;
			StringBuffer check;

			if (data.getBody() != null && data.getBody() != "") {
				dataapi = linkbook + api;
				String datapost = "{\"keyword\":\"" + data.getBody() + "\",\"page\":" + pages + ",\"limit\":" + last
						+ "}";
				check = JavaWebMVC.API.CallAPI.post(dataapi, datapost);
			} else {
				check = JavaWebMVC.API.CallAPI.Get(dataapi);
			}
			if (check != null) {
				list = new JSONObject(check.toString());
				if (!(list.has("data"))) {
					listString = null;
				} else {
					listString = list.get("data").toString();

					data.setCount(list.getInt("count"));

					int TotalPage = (int) Math.ceil((double) data.getCount() / last);
					data.setTotal_page(TotalPage);

					/*
					 * Sau khi có tổng số trang ta kiểm tra xem nó có nhỏ hơn 0 hay không nếu nhỏ
					 * hơn 0 thì gán nó băng 1 ngay. Vì mặc định tổng số trang luôn bằng 1
					 */
					if (data.getTotal_page() < 0) {
						data.setTotal_page(1);
					}
					/*
					 * Trang hiện tại sẽ rơi vào một trong các trường hợp sau: - Nếu trang hiện tại
					 * người dùng truyền vào lớn hơn tổng số trang thì ta gán nó bằng tổng số trang
					 * Đây là vấn đề giúp web chạy trơn tru hơn, vì đôi khi người dùng cố ý thay đổi
					 * tham số trên url nhằm kiểm tra lỗi web của chúng ta
					 */

					if (data.getCurrent_page() > data.getTotal_page()) {
						data.setCurrent_page(data.getTotal_page());
						pages = data.getCurrent_page();
						dataapi = linkbook + api + "/" + pages + "/" + last;
						list = new JSONObject(JavaWebMVC.API.CallAPI.Get(dataapi).toString());
						listString = list.get("data").toString();

					}
					/*
					 * Bây giờ ta tính số trang ta show ra trang web Trước tiên tính middle, đây
					 * chính là số nằm giữa trong khoảng tổng số trang mà bạn muốn hiển thị ra màn
					 * hình
					 */
					int middle = (int) Math.ceil((double) data.getRange() / 2);

					/*
					 * Ta sẽ lâm vào các trường hợp như bên dưới Trong trường hợp tổng số trang mà
					 * bé hơn range thì ta show hết luôn, không cần tính toán làm gì tức là gán min
					 * = 1 và max = tổng số trang luôn
					 */
					if (data.getTotal_page() < data.getRange()) {
						data.setMin(1);
						data.setMax(data.getTotal_page());
					}
					/* Trường hợp tổng số trang mà lớn hơn range */
					else {

						// Ta sẽ gán min = current_page - middle + 1
						data.setMin(data.getCurrent_page() - middle + 1);

						// Ta sẽ gán max = current_page + middle - 1
						data.setMax(data.getCurrent_page() + middle - 1);

						// Sau khi tính min và max ta sẽ kiểm tra
						// nếu min < 1 thì ta sẽ gán min = 1 và max bằng luôn range
						if (data.getMin() < 1) {
							data.setMin(1);
							data.setMax(data.getRange());
						}

						// Nếu max > tổng số trang
						// ta gán max = tổng số trang và min = (tổng số trang - range) + 1
						else if (data.getMax() > data.getTotal_page()) {
							data.setMax(data.getTotal_page());
							data.setMin(data.getTotal_page() - data.getRange() + 1);
						}
					}
				}
			}
		});
	}

	/*
	 * Hàm Lấy Danh Sách Từ API
	 */
	public String Getlist() {
		return listString;
	}

	private String link(int page) {
		String link_first;
		String link_full;

		link_first = this.config.get(0).link_first;
		link_full = this.config.get(0).link_full;

		// Nếu trang < 1 thì ta sẽ lấy link first
		if (page <= 1) {
			return link_first;
		}
		// Ngược lại ta lấy link_full

		return link_full.replace("{page}", String.valueOf(page));
	}

	/*
	 * Hàm lấy mã html
	 */
	public String html() {
		String p = "";
		if (String.valueOf(this.config.get(0).count) != null && this.config.get(0).count != 0) {

			if (this.config.get(0).count > this.config.get(0).limit) {
				p = "<ul  class=\"pagination\" id=\"pagination\">";

				// Nút prev và first
				if (this.config.get(0).current_page > 1) {
					p += "<li  class=\"page-item\"><a class=\"page-link\"  href=\"" + link(1) + "\">First</a></li>";
					p += "<li  class=\"page-item\"><a class=\"page-link\"  href=\""
							+ link(this.config.get(0).current_page - 1) + "\">Prev</a></li>";
				}

				// lặp trong khoảng cách giữa min và max để hiển thị các nút
				for (int i = this.config.get(0).min; i <= this.config.get(0).max; i++) {
					// Trang hiện tại
					if (this.config.get(0).current_page == i) {
						p += "<li  class=\"page-item active\"><a class=\"page-link\" >" + i + "</a></li>";
					} else {
						p += "<li  class=\"page-item\"><a class=\"page-link\"  href=\"" + link(i) + "\">" + i
								+ "</a></li>";
					}

				}
//
				// Nút last và next
				if (this.config.get(0).current_page < this.config.get(0).total_page) {
					p += "<li class=\"page-item\"><a class=\"page-link\"  href=\""
							+ link(this.config.get(0).current_page + 1) + "\">Next</a></li>";
					p += "<li class=\"page-item\"><a class=\"page-link\"  href=\"" + link(this.config.get(0).total_page)
							+ "\">Last</a></li>";
				}

				p += "</ul>";
			}
			return p;
		}
		return p;
	}
}
