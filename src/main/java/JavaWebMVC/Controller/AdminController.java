package JavaWebMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping(value = {"/admin/index"})
	public String Admin() {
		return "/admin/index";
	}
}
