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
	public ModelAndView Profile(HttpSession session) {

		if (session.getAttribute("id") != null) {
			String link = "https://bookingapiiiii.herokuapp.com/khachhangbyid/" + session.getAttribute("id");
			if (JavaWebMVC.API.CallAPI.Get(link) != null) {
				JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.Get(link).toString());
				ModelAndView mv = new ModelAndView("/user/profile");
				mv.addObject("HoTen", json.getString("HoTen"));
				mv.addObject("Email", json.getString("Email"));
				mv.addObject("Anh", json.getString("Anh"));
				mv.addObject("DiachiKH", json.getString("DiachiKH"));
				mv.addObject("DienthoaiKH", json.getString("DienthoaiKH"));
				mv.addObject("Ngaysinh", json.getString("Ngaysinh"));
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

	@RequestMapping(value = { "/myprofile" }, method = RequestMethod.POST)
	public ModelAndView Profile(HttpServletRequest req, HttpSession session,
			@RequestParam(value = "imgchoose", required = false) MultipartFile photo) {

		try {
			String data;
			req.setCharacterEncoding("UTF-8");
			if (photo.getOriginalFilename() == "") {
				data = "{\n\"id\" : \"" + session.getAttribute("id") + "\",\n \"HoTen\": \"" + req.getParameter("HoTen")
						+ "\", \n \"Email\": \"" + req.getParameter("Email") + "\",\n \"DiachiKH\": \""
						+ req.getParameter("DiaChi") + "\",\n \"DienthoaiKH\" : \"" + req.getParameter("SDT")
						+ "\",\n \"Ngaysinh\": \"" + req.getParameter("Date") + "\"\n}";
			} else {
				byte[] encodedBytes = Base64.getEncoder().encode(photo.getBytes());
				String originalString = new String(encodedBytes, StandardCharsets.UTF_8);
				String base64img = "data:image/jpeg;base64," + originalString;

				data = "{\n\"id\" : \"" + session.getAttribute("id") + "\",\n \"HoTen\": \"" + req.getParameter("HoTen")
						+ "\", \n \"Email\": \"" + req.getParameter("Email") + "\",\n \"DiachiKH\": \""
						+ req.getParameter("DiaChi") + "\",\n \"DienthoaiKH\" : \"" + req.getParameter("SDT")
						+ "\",\n \"Ngaysinh\": \"" + req.getParameter("Date") + "\",\n \"Anh\":\"" + base64img
						+ "\"\n}";
			}

			String link = "https://bookingapiiiii.herokuapp.com/khachhang";		
			if (JavaWebMVC.API.CallAPI.put(link, data) != null) {
				JSONObject json = new JSONObject(JavaWebMVC.API.CallAPI.put(link, data).toString());
				ModelAndView mv = new ModelAndView();
				mv.setViewName("/user/profile");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("/user/profile");
				mv.addObject("Messenger", data);
				return mv;
			}
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("/user/profile");
			mv.addObject("Messenger", e);
			return mv;
		}
	}

	@RequestMapping(value = { "/changepass" }, method = RequestMethod.POST)
	public String changepass(HttpServletRequest req, HttpSession session) {
		try {
			req.setCharacterEncoding("UTF-8");
			return "/user/profile";
		} catch (Exception e) {
			return "/user/profile";
		}
	}

	@RequestMapping(value = { "/products" })
	public String Products() {
		return "/user/products";
	}
}
