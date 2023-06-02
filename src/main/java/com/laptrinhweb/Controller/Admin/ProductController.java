package com.laptrinhweb.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhweb.Service.Implementation.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/manager-product")
	public String adminhome(Model model) {
		model.addAttribute("productlist", productService.getAllProduct());
		return "productadmin";
	}
	@GetMapping("/manager-updateproduct")
	public String updateproduct(Model model, @RequestParam(name = "pid", required = false) String pid) {
	
		if (pid != null) model.addAttribute("product", productService.findbyId(pid));
		return "product-updateadmin";
	}
	@GetMapping("/manager-saveproduct")
	public String saveproduct(Model model,RedirectAttributes redirectAttributes, 
							  @RequestParam(name = "pid", required = true) String pid,
							  @RequestParam(name = "name", required = true) String name,
							  @RequestParam(name = "price", required = true) String price,
							  @RequestParam(name = "discount", required = true) String discount,
							  @RequestParam(name = "sold-quantity", required = true) String sold) {
		String result = productService.updateProduct(pid, name, price, discount, sold);
		if (result.equals("Cập nhật sản phẩm thành công!!!") || result.equals("Thêm sản phẩm thành công!!!")) {
			redirectAttributes.addFlashAttribute("messagesuccess", result);
		}
		else redirectAttributes.addFlashAttribute("messagefail", result);
		return "redirect:/manager-product";
	}
	@GetMapping("/manager-deleteproduct")
	public String deleteproduct(Model model ,RedirectAttributes redirectAttributes,@RequestParam(name = "pid", required = true) String pid)
	{
		String result = productService.deleteProduct(pid);
		if (result.equals("Xóa sản phẩm thành công!!!"))
			redirectAttributes.addFlashAttribute("messagesuccess", result);
		else redirectAttributes.addFlashAttribute("messagefail", result);
		return "redirect:/manager-product";
	}
}
