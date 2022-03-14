package JavaWebMVC.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Model.Book;
import Model.User;
import Model.Bill;

@Controller
public class AdminController {
	
	@RequestMapping(value = {"/admin/index"})
	public String Admin() {
		return "/admin/index";
	}
	
	@RequestMapping(value= {"/admin/account-manager"})
	public ModelAndView Acc_Manager() throws UnsupportedEncodingException {
		
		Boolean role = false;
		String linkapi = "https://bookingapiiiii.herokuapp.com/khachhangforadmin/" + role;
		String resAPI = JavaWebMVC.API.CallAPI.Get(linkapi).toString();
		int length=0;
		
		ArrayList<User> listUser = new ArrayList<User>();
		if(resAPI != null) {
			JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkapi).toString());
			json.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				User user = new User();
				user.setId(jsonobject.getString("_id"));
				user.setHoTen(jsonobject.getString("HoTen"));
				if (jsonobject.has("Email")) user.setEmail(jsonobject.getString("Email"));	    	
				user.setTaikhoan(jsonobject.getString("Taikhoan"));
				user.setRole(jsonobject.getBoolean("Role"));
				listUser.add(user);
			});
			length = json.length();
		}
		
		ModelAndView mv = new ModelAndView("/admin/account-manager");
		
		mv.addObject("listUser",listUser);
		mv.addObject("length", length);
		return mv;
	}
	
	@RequestMapping(value = {"/admin/bill-pay"})
	public ModelAndView Bill_Pay() {
		String linkapi = "https://bookingapiiiii.herokuapp.com/DonHang/";
		String resAPI = JavaWebMVC.API.CallAPI.Get(linkapi).toString();
		
		ArrayList<Bill> listBill = new ArrayList<Bill>();
		if(resAPI != null) {
			JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkapi).toString());
			json.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Bill bill = new Bill();
				bill.setDate(jsonobject.getString("Ngaydat"));
				bill.setId(jsonobject.getString("id"));
				bill.setUsername(jsonobject.getString("HoTen"));  	
				listBill.add(bill);
			});
		}
		ModelAndView mv = new ModelAndView("/admin/billpay");
		mv.addObject("listBill",listBill);
		return mv;
	}
	
	@RequestMapping(value = {"/admin/storage-products"})
	public String Storage() {
		return "/admin/storage";
	}
	
	@RequestMapping(value = {"/admin/setting"})
	public String Setting() {
		return "/admin/setting";
	}
}
