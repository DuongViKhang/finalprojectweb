package com.laptrinhweb.Controller.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laptrinhweb.Convert.User;
import com.laptrinhweb.DTO.UserDTO;
import com.laptrinhweb.Entity.CustomerEntity;
import com.laptrinhweb.Service.Implementation.UserDetailService;
import com.laptrinhweb.repository.CustomerRepository;
import com.laptrinhweb.repository.LoginRepository;
import com.laptrinhweb.repository.OrderRepository;
import com.laptrinhweb.repository.ProductOrderRepository;
import com.laptrinhweb.repository.ProductRepository;

@Controller
public class UserController {
	@Autowired
	CustomerRepository userRepository;
	@Autowired
	User user;
	
	@GetMapping("/manager-account")
	public String adminhome(Model model) {
		List <UserDTO> result = new ArrayList<UserDTO>();
		List<CustomerEntity> list = userRepository.findAll();
		for (CustomerEntity x: list) {
			result.add(user.toDTO(x));
		}
		model.addAttribute("accountlist", result);
		return "accountadmin";
	}
}
