package JavaWebMVC.Controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	// Data String data = "{\n \"Taikhoan\": \"dung\",\n \"Matkhau\":
	// \"123456\"\n}";
	@RequestMapping(value = { "/", "/home" })
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("/user/index");
		return mv;
	}

	@RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
	public String Login(HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "/user/signin";
		}
		System.out.println(session.getAttribute("id"));
		return "/user/index";
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

			ModelAndView mv = new ModelAndView("/user/index");
			mv.addObject("HoTen", session.getAttribute("User"));
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/user/signin");
			mv.addObject("Messenger", json.getString("Messenger"));
			return mv;
		}

	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView Signup() {

		ModelAndView mv = new ModelAndView("/user/signup");
		return mv;
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView Signup(HttpServletRequest req, HttpSession session) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		String data = "{\n \"HoTen\": \"" + req.getParameter("fullname") + "\", \n \"Taikhoan\": \""
				+ req.getParameter("username") + "\",\n \"Matkhau\": \"" + req.getParameter("pass")
				+ "\",\n \"ConfirmMatKhau\" : \"" + req.getParameter("compass") + "\"\n}";

		String link = "https://bookingapiiiii.herokuapp.com/khachhang";

		JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.post(link, data).toString());
		System.out.println(req.getParameter("fullname"));
		if (json.getString("Messenger").equalsIgnoreCase("Đăng Ký Thành Công")) {
			session.setAttribute("id", json.getString("id"));
			session.setAttribute("User", json.getString("HoTen"));
			ModelAndView mv = new ModelAndView("/user/index");
			mv.addObject("HoTen", session.getAttribute("User"));
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/user/signup");
			mv.addObject("Messenger", json.getString("Messenger"));
			return mv;
		}
	}

	@RequestMapping(value = { "/signout" })
	public String Signout(HttpSession session) {
		session.removeAttribute("id");
		return "/user/index";
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
				System.out.println(data);
				System.out.println(json);
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

	@RequestMapping(value = { "/products" })
	public String Products() {
		return "/user/products";
	}
	
	@RequestMapping(value = {"/details"})
	public String Details() {
		return "/user/details";
	}
}
