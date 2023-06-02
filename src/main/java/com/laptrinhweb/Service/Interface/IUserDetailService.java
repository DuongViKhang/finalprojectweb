package com.laptrinhweb.Service.Interface;

import java.util.List;

import com.laptrinhweb.DTO.UserDTO;
import com.laptrinhweb.Entity.OrderEntity;

public interface IUserDetailService {

	UserDTO detailUser(String username);

	String updateInfoUser(String username, String fullname, String phone, String sex, String address);

	String addProductToCart(String pid, String username, Long quantity);

	Long total(String username);

	String removeProductToCart(String pid, String username);

	List<UserDTO> getAllUser();

	List<OrderEntity> getAllOrder(String username);

	OrderEntity getCart(String username);

	String confirmcart(String username);
	
	String addNewUser(String fullname, String username, String email, String phone, String password, String gender);
	
	String changepassword(String username, String oldpassword, String newpassword, String confirm);

	String adminconfirmcart(String customerid);

}
