package detail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {
	@GetMapping("/detail")
	public String detail() {
		// @RequestParam(name = "id") int id
		// 파라미터 값 controller에서 안받아와도 되는지?
		return "detail";
	}
	
	@GetMapping("/reserve")
	public String reserve() {
		return "reserve";
	}
}
