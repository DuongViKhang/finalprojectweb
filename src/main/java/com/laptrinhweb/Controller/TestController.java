package com.laptrinhweb.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.laptrinhweb.Controller.Customer.ProfileController;

@Controller
public class TestController {

	@GetMapping("/test")
	public String Test1() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "accountadmin";
	}
	@GetMapping("/test1")
	public String Tes2t() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "account-profileadmin";
	}
	@GetMapping("/test2")
	public String Test3() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "categoryadmin";
	}
	@GetMapping("/test3")
	public String Test4() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "category-updateadmin";
	}
	@GetMapping("/test4")
	public String Test5() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "footeradmin";
	}
	@GetMapping("/test5")
	public String Test6() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "headeradmin";
	}
	@GetMapping("/test6")
	public String Test7() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "indexadmin";
	}
	@GetMapping("/test7")
	public String Test8() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "orderadmin";
	}
	@GetMapping("/test8")
	public String Test9() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "productadmin";
	}
	@GetMapping("/test9")
	public String Test10() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "product-updateadmin";
	}
	@GetMapping("/test10")
	public String Test11() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "storeadmin";
	}
	@GetMapping("/test11")
	public String Test12() {
		/*
		 * Logger logger = LoggerFactory.getLogger(ProfileController.class);
		 * logger.error(authentication.getName());
		 */
		return "store-updateadmin";
	}
}
