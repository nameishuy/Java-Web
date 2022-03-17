package JavaWebMVC.Controller;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Model.Bill;
import Model.Book;
import Model.Cart;
import Model.Chude;
import Model.UserBill;

@Controller
public class HomeController {
	@RequestMapping(value = { "/", "/home" })
	public ModelAndView Index() {
		String linkapi = "https://bookingapiiiii.herokuapp.com/Banner";
		String resBanner = JavaWebMVC.API.CallAPI.Get(linkapi).toString();

		if (resBanner != null) {
			JSONObject jsonobject = new JSONObject(resBanner);

			// Add to mv
			ModelAndView mv = new ModelAndView("/user/index");
			mv.addObject("banner1", jsonobject.getString("Anh1"));
			mv.addObject("banner2", jsonobject.getString("Anh2"));
			mv.addObject("banner3", jsonobject.getString("Anh3"));
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/user/index");
			mv.addObject("Messenger", "Không Thể Call API");
			return mv;
		}
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
	public ModelAndView Login(HttpSession session) {
		if (session.getAttribute("id") == null) {
			ModelAndView mv = new ModelAndView("/user/signin");
			return mv;
		}
		return this.Index();
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.POST)
	public ModelAndView Login(HttpServletRequest req, HttpSession session) {
		String data = "{\n \"Taikhoan\": \"" + req.getParameter("username") + "\",\n \"Matkhau\": \""
				+ req.getParameter("pass") + "\"\n}";

		String link = "https://bookingapiiiii.herokuapp.com/login";

		JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.post(link, data).toString());

		if (json.getString("Messenger").equalsIgnoreCase("Đăng Nhập Thành Công")) {
			session.setAttribute("id", json.getString("id"));
			session.setAttribute("User", json.getString("HoTen"));
			session.setAttribute("Role", json.getBoolean("Role"));

			String link2 = "https://bookingapiiiii.herokuapp.com/khachhangbyid/" + json.getString("id");
			String res2 = JavaWebMVC.API.CallAPI.Get(link2).toString();
			if (res2 != null) {
				JSONObject json2 = new JSONObject(res2);
				try {
					session.setAttribute("Name", json2.getString("HoTen"));

					if (!json2.getString("HoTen").isBlank() && !json2.getString("DienthoaiKH").isBlank()) {
						session.setAttribute("Email", json2.getString("Email"));
						session.setAttribute("SDT", json2.getString("DienthoaiKH"));
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			return this.Index();
		} else {
			ModelAndView mv = new ModelAndView("/user/signin");
			mv.addObject("Messenger", json.getString("Messenger"));
			return mv;
		}

	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView Signup(HttpSession session) {
		if (session.getAttribute("id") == null) {
			ModelAndView mv = new ModelAndView("/user/signup");
			return mv;
		}
		return this.Index();
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView Signup(HttpServletRequest req, HttpSession session) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		String data = "{\n \"HoTen\": \"" + req.getParameter("fullname") + "\", \n \"Taikhoan\": \""
				+ req.getParameter("username") + "\",\n \"Matkhau\": \"" + req.getParameter("pass")
				+ "\",\n \"ConfirmMatKhau\" : \"" + req.getParameter("compass") + "\"\n}";

		String link = "https://bookingapiiiii.herokuapp.com/khachhang";

		JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.post(link, data).toString());
		if (json.getString("Messenger").equalsIgnoreCase("Đăng Ký Thành Công")) {
			session.setAttribute("id", json.getString("id"));
			session.setAttribute("User", json.getString("HoTen"));
			return this.Index();
		} else {
			ModelAndView mv = new ModelAndView("/user/signup");
			mv.addObject("Messenger", json.getString("Messenger"));
			return mv;
		}
	}

	@RequestMapping(value = { "/signout" })
	public ModelAndView Signout(HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("User");
		session.removeAttribute("Role");
		return this.Index();
	}

	@RequestMapping(value = { "/myprofile" }, method = RequestMethod.GET)
	public ModelAndView Profile(HttpSession session, String mess) {

		if (session.getAttribute("id") != null) {
			String link = "https://bookingapiiiii.herokuapp.com/khachhangbyid/" + session.getAttribute("id");
			String res = JavaWebMVC.API.CallAPI.Get(link).toString();
			if (res != null) {
				JSONObject json = new JSONObject(res);
				ModelAndView mv = new ModelAndView("/user/profile");
				mv.addObject("HoTen", json.getString("HoTen"));
				mv.addObject("Email", json.getString("Email"));
				mv.addObject("Anh", json.getString("Anh"));
				mv.addObject("DiachiKH", json.getString("DiachiKH"));
				mv.addObject("DienthoaiKH", json.getString("DienthoaiKH"));
				mv.addObject("Ngaysinh", json.getString("Ngaysinh"));
				mv.addObject("Messenger", mess);
				return mv;
			}
		} else {
			ModelAndView mv = new ModelAndView("/user/profile");
			mv.addObject("Messenger", "Không Thể Call API");
			return mv;
		}
		ModelAndView mv = new ModelAndView("/user/signin");
		return mv;
	}

	// MultipartFile photo Để Lấy Dữ Liệu Của ảnh
	@RequestMapping(value = { "/myprofile" }, method = RequestMethod.POST)
	public ModelAndView Profile(HttpServletRequest req, HttpSession session,
			@RequestParam(value = "imgchoose", required = false) MultipartFile photo) {

		try {
			String data;
			// setCharacterEncoding để định dạng UTF-8 Nhưng Code Nó không hiểu
			req.setCharacterEncoding("UTF-8");
			if (photo.getOriginalFilename() == "") {

				// Json Được Chuyển Thành Dạng String vào https://jsontostring.com
				data = "{\n\"id\" : \"" + session.getAttribute("id") + "\",\n \"HoTen\": \"" + req.getParameter("HoTen")
						+ "\", \n \"Email\": \"" + req.getParameter("Email") + "\",\n \"DiachiKH\": \""
						+ req.getParameter("DiaChi") + "\",\n \"DienthoaiKH\" : \"" + req.getParameter("SDT")
						+ "\",\n \"Ngaysinh\": \"" + req.getParameter("Date") + "\"\n}";
			} else {

				// Chỉ Nhận ảnh JPG Và Chuyển ảnh thành dạng Base64 để Lưu Vô cơ sở dữ liệu
				byte[] encodedBytes = Base64.getEncoder().encode(photo.getBytes());
				String originalString = new String(encodedBytes, StandardCharsets.UTF_8);
				String base64img = "data:image/jpeg;base64," + originalString;

				// Json Được Chuyển Thành Dạng String vào https://jsontostring.com
				data = "{\n\"id\" : \"" + session.getAttribute("id") + "\",\n \"HoTen\": \"" + req.getParameter("HoTen")
						+ "\", \n \"Email\": \"" + req.getParameter("Email") + "\",\n \"DiachiKH\": \""
						+ req.getParameter("DiaChi") + "\",\n \"DienthoaiKH\" : \"" + req.getParameter("SDT")
						+ "\",\n \"Ngaysinh\": \"" + req.getParameter("Date") + "\",\n \"Anh\":\"" + base64img
						+ "\"\n}";
			}
			// Link đăng Kí Có Trên PostMan
			String link = "https://bookingapiiiii.herokuapp.com/khachhang";
			String res = JavaWebMVC.API.CallAPI.put(link, data).toString();
			// Gọi hàm put bên Package API nếu trả về Null là Call tới Api Thất Bại
			if (res != null) {

				// Json là dữ Liệu Khi Được Trả về khi call api
				JSONObject json = new JSONObject(res);

				// sửa lại session để đồng bộ với DB
				session.setAttribute("User", json.getString("HoTen"));

				// gọi lại hàm Get Phía Trên để có thể hiển thị thông tin vì t không biết return
				// back
				return Profile(session, json.getString("Messenger"));
			} else {
				// để báo lỗi
				return Profile(session, "Không Thể Call API");
			}
		} catch (Exception e) {
			return Profile(session, e.getMessage());
		}
	}

	@RequestMapping(value = { "/changepass" }, method = RequestMethod.POST)
	public ModelAndView changepass(HttpServletRequest req, HttpSession session) {
		try {
			req.setCharacterEncoding("UTF-8");

			String data = "{\"id\":\"" + session.getAttribute("id") + "\",\"Matkhaued\":\""
					+ req.getParameter("oldpass") + "\",\"newMatkhau\":\"" + req.getParameter("newpass")
					+ "\",\"ConfirmMatKhau\":\"" + req.getParameter("compass") + "\"}";

			String link = "https://bookingapiiiii.herokuapp.com/khachhangmk";
			String res = JavaWebMVC.API.CallAPI.put(link, data).toString();
			if (res != null) {

				// Json là dữ Liệu Khi Được Trả về khi call api
				JSONObject json = new JSONObject(res);
				// gọi lại hàm Get Phía Trên để có thể hiển thị thông tin vì t không biết return
				// back
				return Profile(session, json.getString("Messenger"));
			} else {
				// để báo lỗi
				return Profile(session, "Không Thể Call API");
			}
		} catch (Exception e) {
			return Profile(session, e.getMessage());
		}
	}

	@RequestMapping(value = { "/details" }, method = RequestMethod.GET)
	public ModelAndView getvalue(HttpServletRequest req) {

		String link = "https://bookingapiiiii.herokuapp.com/sachbyid/" + req.getParameter("id");
		String check = JavaWebMVC.API.CallAPI.Get(link).toString();

		ArrayList<Book> detail = new ArrayList<Book>();
		if (check != null) {
			JSONArray json = new JSONArray(check);
			json.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Book book = new Book();
				book.setID(jsonobject.getString("id"));
				book.setTensach(jsonobject.getString("Tensach"));
				book.setAnh(jsonobject.getString("Anh"));
				book.setMota(jsonobject.getString("Mota"));
				book.setGiaban(jsonobject.getDouble("Giaban"));
				book.setTenTG(jsonobject.getString("TenTG"));
				book.settenNXB(jsonobject.getString("TenNXB"));
				book.setSoluongton(jsonobject.getInt("Soluongton"));
				detail.add(book);
			});
		}

		ModelAndView model = new ModelAndView("/user/details");
		model.addObject("Book", detail);
		return model;
	}

	@RequestMapping(value = { "/my-cart" })
	public String MyCart(HttpSession sesstion) {
		if (sesstion.getAttribute("id") == null) {
			return "redirect:/signin";
		} else {
			return "/user/cart";
		}
	}

	// thêm hàng hóa vào giỏ hàng
	ArrayList<Cart> Cart = new ArrayList<Cart>();

	@RequestMapping(value = { "/addCart/{id}" })
	public String addCart(HttpServletRequest req, HttpSession sesstion, @PathVariable String id) {
		Cart cart = new Cart();
		String link = "https://bookingapiiiii.herokuapp.com/sachbyid/" + id;
		String check = JavaWebMVC.API.CallAPI.Get(link).toString();
		Boolean flag = true;
		Double TotalPriceInCart = 0.0;
		if (sesstion.getAttribute("id") == null) {
			return "redirect:/signin";
		} else {
			for (Cart item : Cart) {
				if (item.getBookId().equalsIgnoreCase(id)) {
					flag = false;
					item.setQuatity(item.getQuatity() + 1);
					JSONArray json = new JSONArray(check);
					json.forEach(data -> {
						JSONObject jsonobject = (JSONObject) data;
						item.setTotalPrice(item.getQuatity() * jsonobject.getDouble("Giaban"));
					});
				}
			}
			if (check != null && flag == true) {
				JSONArray json = new JSONArray(check);
				json.forEach(data -> {
					JSONObject jsonobject = (JSONObject) data;
					cart.setTotalPrice(jsonobject.getDouble("Giaban"));
					cart.setPicBook(jsonobject.getString("Anh"));
					cart.setDescription(jsonobject.getString("Mota"));
					cart.setBookId(id);
					cart.setQuatity(1);
					Cart.add(cart);
				});
			}
			for (Cart item : Cart) {
				TotalPriceInCart = TotalPriceInCart + item.getTotalPrice();
			}
		
			sesstion.setAttribute("ItemCart", Cart);
			sesstion.setAttribute("TotalPriceInCart", TotalPriceInCart);
			return "redirect:/my-cart";
		}
	}

	// phần tăng 1 quatity vào giỏ hàng
	@RequestMapping(value = { "/addmore/{id}" })
	public String AddMore(HttpServletRequest req, HttpSession sesstion, @PathVariable String id) {
		String link = "https://bookingapiiiii.herokuapp.com/sachbyid/" + id;
		String check = JavaWebMVC.API.CallAPI.Get(link).toString();
		Double TotalPriceInCart = 0.0;
		if (id != null && id != "" && !Cart.isEmpty()) {
			for (Cart item : Cart) {
				if (item.getBookId().equalsIgnoreCase(id)) {
					item.setQuatity(item.getQuatity() + 1);
					JSONArray json = new JSONArray(check);
					json.forEach(data -> {
						JSONObject jsonobject = (JSONObject) data;
						item.setTotalPrice(item.getQuatity() * jsonobject.getDouble("Giaban"));
					});
				}
			}
		}
		for (Cart item : Cart) {
			TotalPriceInCart = TotalPriceInCart + item.getTotalPrice();
		}
	
		sesstion.setAttribute("ItemCart", Cart);
		sesstion.setAttribute("TotalPriceInCart", TotalPriceInCart);
		return "redirect:" + req.getHeader("Referer");
	}

	// phần giảm 1 quatity vào giỏ hàng
	@RequestMapping(value = { "/addless/{id}" })
	public String AddLess(HttpServletRequest req, HttpSession sesstion, @PathVariable String id) {
		String link = "https://bookingapiiiii.herokuapp.com/sachbyid/" + id;
		String check = JavaWebMVC.API.CallAPI.Get(link).toString();
		Double TotalPriceInCart = 0.0;
		if (id != null && id != "") {
			for (Cart item : Cart) {
				if (item.getBookId().equalsIgnoreCase(id) && item.getQuatity() > 1) {
					item.setQuatity(item.getQuatity() - 1);
					JSONArray json = new JSONArray(check);
					json.forEach(data -> {
						JSONObject jsonobject = (JSONObject) data;
						item.setTotalPrice(item.getQuatity() * jsonobject.getDouble("Giaban"));
					});
				}
			}
		}
		for (Cart item : Cart) {
			TotalPriceInCart = TotalPriceInCart + item.getTotalPrice();
		}
		
		sesstion.setAttribute("ItemCart", Cart);
		sesstion.setAttribute("TotalPriceInCart", TotalPriceInCart);
		return "redirect:" + req.getHeader("Referer");
	}

	// Phần xóa một sp trong giỏ hàng
	@RequestMapping(value = { "/remove/{id}" })
	public String Remove(HttpServletRequest req, HttpSession sesstion, @PathVariable String id) {
		Double TotalPriceInCart = 0.0;
		try {
			if (id != null && id != "") {
				for (Cart item : Cart) {
					if (item.getBookId().equalsIgnoreCase(id)) {
						Cart.remove(Cart.indexOf(item));
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		for (Cart item : Cart) {
			TotalPriceInCart = TotalPriceInCart + item.getTotalPrice();
		}
		sesstion.setAttribute("ItemCart", Cart);
		sesstion.setAttribute("TotalPriceInCart", TotalPriceInCart);
		return "redirect:" + req.getHeader("Referer");
	}

	// Phần xóa toàn bộ trong giỏ hàng
	@RequestMapping(value = { "/removeAll" })
	public String RemoveAll(HttpSession sesstion, HttpServletRequest req) {
		Double TotalPriceInCart = 0.0;
		try {
			Cart.removeAll(Cart);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		for (Cart item : Cart) {
			TotalPriceInCart = TotalPriceInCart + item.getTotalPrice();
		}
		sesstion.setAttribute("ItemCart", Cart);
		sesstion.setAttribute("TotalPriceInCart", TotalPriceInCart);
		return "redirect:" + req.getHeader("Referer");
	}

	// Phần đặt hàng
	@RequestMapping(value = { "/order" })
	public String Oder(HttpSession sesstion, HttpServletRequest req) {
		ArrayList<Object> IdInCart = new ArrayList<Object>();
		ArrayList<Integer> QuatityInCart = new ArrayList<>();
		if (sesstion.getAttribute("Date") != null && !Cart.isEmpty()
				&& Double.parseDouble(sesstion.getAttribute("TotalPriceInCart").toString()) > 0
				&& sesstion.getAttribute("id") != null) {
			for (Cart item : Cart) {
				IdInCart.add(item.getBookId());
				QuatityInCart.add(item.getQuatity());
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			String dataDH = "{\n \"Dathanhtoan\":" + false + ",\n \"Tinhtranggiaohang\":" + false + ",\n \"Ngaydat\":\""
					+ date + "\",\n \"TongTien\":" + sesstion.getAttribute("TotalPriceInCart") + ",\n \"MaKH\":\""
					+ sesstion.getAttribute("id") + "\",\n \"MasachCheck\":" + IdInCart + ",\n \"SoluongCheck\":"
					+ QuatityInCart + "\n}";

			String linkDH = "https://bookingapiiiii.herokuapp.com/DonHang";
			JSONObject body = new JSONObject(dataDH);
			String BodyString = body.toString();
		
			JSONObject jsonDH = new JSONObject(JavaWebMVC.API.CallAPI.post(linkDH, BodyString).toString());

			if (jsonDH.getString("_id") != null) {
				Boolean flag = true;
				for (Cart item2 : Cart) {
					String dataCTDH = "{\n\"MaDonHang\":\"" + jsonDH.getString("_id") + "\",\n\"Masach\":\""
							+ item2.getBookId() + "\",\n\"Soluong\":" + item2.getQuatity() + ",\n\"Dongia\":"
							+ item2.getTotalPrice() + "\n}";

					String linkCTDH = "https://bookingapiiiii.herokuapp.com/CTDonHang";

					JSONObject jsonCTDH = new JSONObject(JavaWebMVC.API.CallAPI.post(linkCTDH, dataCTDH).toString());
					if (jsonCTDH.getString("_id") == null) {
						flag = false;
					}
				}
				if (flag) {
					sesstion.setAttribute("Status", "Cảm ơn bạn đã chọn mua sản phẩm của nhà sách chúng tôi");
					return "/user/dialogOrder";
				} else {
					sesstion.setAttribute("Status", "Đã xảy ra lỗi trong quá trình thêm Chi tiết đơn đặt hàng");
					return "/user/dialogOrder";
				}
			} else {
				sesstion.setAttribute("Status", "Đã xảy ra lỗi trong quá trình đặt hàng");
				return "/user/dialogOrder";
			}
		}
		return "/user/dialogOrder";
	}

	@RequestMapping(value = { "/history-pay" })
	public ModelAndView HistoryPay(HttpSession session) {
		ArrayList<UserBill> listUserBill = new ArrayList<UserBill>();
		if (session.getAttribute("id") != null) {
			String link = "https://bookingapiiiii.herokuapp.com/DonHangbyidKH/" + session.getAttribute("id");
			StringBuffer res = JavaWebMVC.API.CallAPI.Get(link);

			if (res != null) {
				JSONArray json = new JSONArray(res.toString());
				json.forEach(data -> {
					JSONObject jsonobject = (JSONObject) data;
					UserBill userBill = new UserBill();
					userBill.setId(jsonobject.getString("_id"));
					userBill.setDatthanhtoan(jsonobject.getBoolean("Dathanhtoan"));
					userBill.setTinhtranggiaohang(jsonobject.getBoolean("Tinhtranggiaohang"));
					userBill.setNgaydat(jsonobject.getString("Ngaydat"));
					userBill.setTongTien(jsonobject.getDouble("TongTien"));
					listUserBill.add(userBill);
				});
			} else {
				ModelAndView mv = new ModelAndView("/user/historypay");
				mv.addObject("Mess", "Chưa Có Lịch Sử Mua Hàng");
				return mv;
			}
		}
		ModelAndView mv = new ModelAndView("/user/historypay");
		mv.addObject("listUserBill", listUserBill);
		return mv;
	}
}
