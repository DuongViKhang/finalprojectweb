package com.laptrinhweb.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAdminController {

	
	@GetMapping("/manager")
	public String adminhome() {
		return "indexadmin";
	}
	
	
}
