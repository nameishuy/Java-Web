package JavaWebMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping(value = {"/admin/index"})
	public String Admin() {
		return "/admin/index";
	}
	
	@RequestMapping(value= {"/admin/account-manager"})
	public String Acc_Manager() {
		return "/admin/account-manager";
	}
	
	@RequestMapping(value = {"/admin/bill-pay"})
	public String Bill_Pay() {
		return "/admin/billpay";
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
