package com.laptrinhweb.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laptrinhweb.repository.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/manager-category")
	public String adminhome(Model model) {
		model.addAttribute("categorylist", categoryRepository.findAll());
		return "categoryadmin";
	}
}
