package mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/mainpage")
	public String mainpage() {
		return "mainpage";
	}
	
	@GetMapping("/myreservation")
	public String myreservation() {
		return "myreservation";
	}
	
	@GetMapping("/review")
	public String review() {
		return "review";
	}
}
