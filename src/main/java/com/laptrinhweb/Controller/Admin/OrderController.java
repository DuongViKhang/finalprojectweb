package com.laptrinhweb.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhweb.Service.Implementation.UserDetailService;
import com.laptrinhweb.repository.OrderRepository;

@Controller
public class OrderController {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserDetailService userDetailService;
	@GetMapping("/manager-order")
	public String adminhome(Model model) {
		model.addAttribute("orderlist", orderRepository.findAll());
		return "orderadmin";
	}
	@GetMapping("manager-orderconfirm")
	public String confirm(RedirectAttributes redirectAttributes,Model model,@RequestParam(name = "customerid", required = false) String customerid) {
		String result = userDetailService.adminconfirmcart(customerid);
		if (result.equals("Xác nhận giao hàng thành công!!!"))
			redirectAttributes.addFlashAttribute("messagesuccess", result);
		else 
			redirectAttributes.addFlashAttribute("messagefail", result);
		return "redirect:/manager-order";
	}
}
