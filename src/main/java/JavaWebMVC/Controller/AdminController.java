package JavaWebMVC.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Model.Book;
import Model.NXB;
import Model.Tacgia;
import Model.User;
import Model.theloai;
import Model.Bill;

@Controller
public class AdminController {

	@RequestMapping(value = { "/admin/index" })
	public ModelAndView Admin(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {
			
			ModelAndView mv = new ModelAndView("/admin/index");
			mv.addObject("Admin",session.getAttribute("Name").toString());
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}
			
	}

	@RequestMapping(value = { "/admin/account-manager" })
	public ModelAndView Acc_Manager(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {

			int last = 3, pages = 1;

			if (request.getParameter("pages") != null) {
				if ((int) Integer.parseInt(request.getParameter("pages")) <= 0) {
					pages = 1;
				} else {
					pages = (int) Integer.parseInt(request.getParameter("pages"));
				}
			}
			System.out.println(pages);
			Boolean role = false;
			String linkapi = "https://bookingapiiiii.herokuapp.com/khachhangforadmin/" + role + "/" + pages + "/"
					+ last;

			StringBuffer resAPI = JavaWebMVC.API.CallAPI.Get(linkapi);

			int length = 0;

			ArrayList<User> listUser = new ArrayList<User>();
			if (resAPI != null) {
				JSONObject data = new JSONObject(resAPI.toString());
				if (!data.has("count")) {
					length = 0;
				} else {
					length = (int) data.get("count");

					JSONArray json = (JSONArray) data.get("data");
					json.forEach(result -> {
						JSONObject jsonobject = (JSONObject) result;
						User user = new User();
						user.setId(jsonobject.getString("_id"));
						user.setHoTen(jsonobject.getString("HoTen"));
						if (jsonobject.has("Email"))
							user.setEmail(jsonobject.getString("Email"));
						user.setTaikhoan(jsonobject.getString("Taikhoan"));
						user.setRole(jsonobject.getBoolean("Role"));
						listUser.add(user);
					});
				}
			}
			int TotalPage = (int) Math.ceil((double) length / last);
			ModelAndView mv = new ModelAndView("/admin/account-manager");
			mv.addObject("listUser", listUser);
			mv.addObject("length", length);
			mv.addObject("pages", pages);
			mv.addObject("TotalPage", TotalPage);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}

	}

	@RequestMapping(value = { "/admin/bill-pay" })

	public ModelAndView Bill_Pay(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {

			String linkapicout = "https://bookingapiiiii.herokuapp.com/DonHang/";

			int last = 3, pages = 1;

			if (request.getParameter("pages") != null) {
				if ((int) Integer.parseInt(request.getParameter("pages")) <= 0) {
					pages = 1;
				} else {
					pages = (int) Integer.parseInt(request.getParameter("pages"));
				}
			}

			String linkapi = "https://bookingapiiiii.herokuapp.com/DonHang/" + pages + "/" + last;
			StringBuffer resAPI = JavaWebMVC.API.CallAPI.Get(linkapi);
			int length = 0;
			ArrayList<Bill> listBill = new ArrayList<Bill>();
			if (resAPI != null) {
				JSONArray jsoncount = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkapicout).toString());
				JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkapi).toString());
				json.forEach(data -> {
					JSONObject jsonobject = (JSONObject) data;
					Bill bill = new Bill();
					bill.setDate(jsonobject.getString("Ngaydat"));
					bill.setId(jsonobject.getString("id"));
					bill.setUsername(jsonobject.getString("HoTen"));
					bill.setTongTien(jsonobject.getDouble("TongTien"));
					listBill.add(bill);
				});
				length = jsoncount.length();
			}
			int TotalPage = (int) Math.ceil((double) length / last);
			ModelAndView mv = new ModelAndView("/admin/billpay");
			mv.addObject("listBill", listBill);
			mv.addObject("length", length);
			mv.addObject("pages", pages);
			mv.addObject("TotalPage", TotalPage);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}

	}

	@RequestMapping(value = { "/admin/storage-products" })
	public ModelAndView Storage(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {

			String linkapicout = "https://bookingapiiiii.herokuapp.com/sach";

			int last = 2, pages = 1;

			if (request.getParameter("pages") != null) {
				if ((int) Integer.parseInt(request.getParameter("pages")) <= 0) {
					pages = 1;
				} else {
					pages = (int) Integer.parseInt(request.getParameter("pages"));
				}
			}

			String linkapi = "https://bookingapiiiii.herokuapp.com/sachpagination/" + pages + "/" + last;

			StringBuffer resAPI = JavaWebMVC.API.CallAPI.Get(linkapi);
			int length = 0;
			ArrayList<Book> listBook = new ArrayList<Book>();
			if (resAPI != null) {
				JSONArray jsoncount = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkapicout).toString());
				JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkapi).toString());
				json.forEach(data -> {
					JSONObject jsonobject = (JSONObject) data;
					Book book = new Book();
					book.setID(jsonobject.getString("id"));
					book.setTensach(jsonobject.getString("Tensach"));
					book.setAnh(jsonobject.getString("Anh"));
					book.setMota(jsonobject.getString("Mota"));
					book.setSoluongton(jsonobject.getInt("Soluongton"));
					book.setGiaban(jsonobject.getDouble("Giaban"));
					listBook.add(book);
				});
				length = jsoncount.length();
			}
			int TotalPage = (int) Math.ceil((double) length / last);
			ModelAndView mv = new ModelAndView("/admin/storage");
			mv.addObject("listBook", listBook);
			mv.addObject("length", length);
			mv.addObject("pages", pages);
			mv.addObject("TotalPage", TotalPage);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}

	}

	@RequestMapping(value = { "/admin/setting" })
	public ModelAndView Setting(HttpSession session) {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {
			// Code more here:

			String linkgetallchudeTGNXB = "https://bookingapiiiii.herokuapp.com/GETALL";

			StringBuffer resAPI = JavaWebMVC.API.CallAPI.Get(linkgetallchudeTGNXB);

			ArrayList<NXB> listnxb = new ArrayList<NXB>();
			ArrayList<theloai> listcd = new ArrayList<theloai>();
			ArrayList<Tacgia> listtacgia = new ArrayList<Tacgia>();

			if (resAPI != null) {
				JSONObject jsonObject = new JSONObject(resAPI.toString());
				JSONArray jsonchude = (JSONArray) jsonObject.get("NXB");

				jsonchude.forEach(data -> {
					JSONObject dataCD = (JSONObject) data;
					NXB nxb = new NXB();
					nxb.set_id(dataCD.getString("_id"));
					nxb.setTenNXB(dataCD.getString("TenNXB"));
					listnxb.add(nxb);
				});

				JSONArray jsontacgia = (JSONArray) jsonObject.get("tacgia");
				jsontacgia.forEach(data -> {
					JSONObject dataTG = (JSONObject) data;
					Tacgia tg = new Tacgia();
					tg.set_id(dataTG.getString("_id"));
					tg.setTenTG(dataTG.getString("TenTG"));
					listtacgia.add(tg);
				});

				JSONArray jsonNXB = (JSONArray) jsonObject.get("chude");
				jsonNXB.forEach(data -> {
					JSONObject dataNXB = (JSONObject) data;
					theloai cd = new theloai();
					cd.set_id(dataNXB.getString("_id"));
					cd.setTenChuDe(dataNXB.getString("TenChuDe"));
					listcd.add(cd);
				});
			}

			ModelAndView mv = new ModelAndView("/admin/setting");
			mv.addObject("listnxb", listnxb);
			mv.addObject("listcd", listcd);
			mv.addObject("listtacgia", listtacgia);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}
	}

	@RequestMapping(value = { "/admin/setting" }, method = RequestMethod.POST)
	public ModelAndView Setting(HttpServletRequest req, HttpSession session) {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {
			// Code more here:
			try {
				req.setCharacterEncoding("UTF-8");
				if (req.getParameter("inputNXB") != null && req.getParameter("inputAddress") != null
						&& req.getParameter("inputNXB") != "" && req.getParameter("inputAddress") != "") {
					String data = "{\"TenNXB\":\"" + req.getParameter("inputNXB") + "\",\"Diachi\":\""
							+ req.getParameter("inputAddress") + "\",\"DienThoai\":\"" + req.getParameter("inputPhone")
							+ "\"}";				
					String linkapi = "https://bookingapiiiii.herokuapp.com/nhaxuatban";
					JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.post(linkapi, data).toString());					
				}

				if (req.getParameter("inputCategory") != null && req.getParameter("inputCategory") != "") {
					String dataCategory = "{\"TenChuDe\":\"" + req.getParameter("inputCategory") + "\"}";
					String apiNewCategory = "https://bookingapiiiii.herokuapp.com/chude";
					JSONObject json = new JSONObject(
							JavaWebMVC.API.CallAPI.post(apiNewCategory, dataCategory).toString());				
				}

				if (req.getParameter("inputAuthorName") != null && req.getParameter("inputAuthorName") != ""
						&& req.getParameter("inputAuthorAddr") != null && req.getParameter("inputAuthorAddr") != ""
						&& req.getParameter("inputAuthorHist") != null && req.getParameter("inputAuthorHist") != ""
						&& req.getParameter("inputAuthorPhone") != null && req.getParameter("inputAuthorPhone") != "") {
					String dataAuthor = "{\"TenTG\":\"" + req.getParameter("inputAuthorName") + "\",\"Diachi\":\""
							+ req.getParameter("inputAuthorAddr") + "\",\"Tieusu\":\""
							+ req.getParameter("inputAuthorHist") + "\",\"Dienthoai\":\""
							+ req.getParameter("inputAuthorPhone") + "\"}";
					String apiNewAuthor = "https://bookingapiiiii.herokuapp.com/tacgia";
					JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.post(apiNewAuthor, dataAuthor).toString());					
				}

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ModelAndView mv = new ModelAndView("redirect:/admin/setting");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}
	}

	@RequestMapping(value = { "/admin/new-book" })
	public ModelAndView NewBook(HttpServletRequest req, HttpSession session) {
		if (session.getAttribute("Role") != null && (session.getAttribute("Role").toString() == "true")) {

			String linkgetallchudeTGNXB = "https://bookingapiiiii.herokuapp.com/GETALL";

			StringBuffer resAPI = JavaWebMVC.API.CallAPI.Get(linkgetallchudeTGNXB);

			ArrayList<NXB> listnxb = new ArrayList<NXB>();
			ArrayList<theloai> listcd = new ArrayList<theloai>();
			ArrayList<Tacgia> listtacgia = new ArrayList<Tacgia>();

			if (resAPI != null) {
				JSONObject jsonObject = new JSONObject(resAPI.toString());
				JSONArray jsonchude = (JSONArray) jsonObject.get("NXB");

				jsonchude.forEach(data -> {
					JSONObject dataCD = (JSONObject) data;
					NXB nxb = new NXB();
					nxb.set_id(dataCD.getString("_id"));
					nxb.setTenNXB(dataCD.getString("TenNXB"));
					listnxb.add(nxb);
				});

				JSONArray jsontacgia = (JSONArray) jsonObject.get("tacgia");
				jsontacgia.forEach(data -> {
					JSONObject dataTG = (JSONObject) data;
					Tacgia tg = new Tacgia();
					tg.set_id(dataTG.getString("_id"));
					tg.setTenTG(dataTG.getString("TenTG"));
					listtacgia.add(tg);
				});

				JSONArray jsonNXB = (JSONArray) jsonObject.get("chude");
				jsonNXB.forEach(data -> {
					JSONObject dataNXB = (JSONObject) data;
					theloai cd = new theloai();
					cd.set_id(dataNXB.getString("_id"));
					cd.setTenChuDe(dataNXB.getString("TenChuDe"));
					listcd.add(cd);
				});
			}

			ModelAndView mv = new ModelAndView("/admin/addnewbook");
			mv.addObject("listnxb", listnxb);
			mv.addObject("listcd", listcd);
			mv.addObject("listtacgia", listtacgia);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/admin/NoAdmin");
			return mv;
		}
	}

}
