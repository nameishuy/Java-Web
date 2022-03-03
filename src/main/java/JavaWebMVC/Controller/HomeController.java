package JavaWebMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value= {"/","/home"})
	public ModelAndView Index() {
		
		ModelAndView mv = new ModelAndView("/user/index");
		return mv;
	}
	
	@RequestMapping(value={"/signin"})
	public ModelAndView Login() {
		
		ModelAndView mv = new ModelAndView("/user/signin");
		return mv;
	}
	
	@RequestMapping(value={"/signup"})
	public ModelAndView Signup() {
		
		ModelAndView mv = new ModelAndView("/user/signup");
		return mv;
	}
}
