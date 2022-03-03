package JavaWebMVC.Controller;

import JavaWebMVC.API.Login;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
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

		JSONObject json = new JSONObject(JavaWebMVC.API.Login.post(link, data).toString());

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
		System.out.println(req.getParameter("fullname"));
		String data = "{\n \"HoTen\": \"" + req.getParameter("fullname") + "\", \n \"Taikhoan\": \""
				+ req.getParameter("username") + "\",\n \"Matkhau\": \"" + req.getParameter("pass")
				+ "\",\n \"ConfirmMatKhau\" : \"" + req.getParameter("compass") + "\"\n}";

		String link = "https://bookingapiiiii.herokuapp.com/khachhang";

		JSONObject json = new JSONObject(JavaWebMVC.API.Login.post(link, data).toString());

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
}
