package com.laptrinhweb.Service.Implementation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laptrinhweb.Convert.User;
import com.laptrinhweb.DTO.UserDTO;
import com.laptrinhweb.Entity.CustomerEntity;
import com.laptrinhweb.Entity.LoginEntity;
import com.laptrinhweb.Entity.OrderEntity;
import com.laptrinhweb.Entity.ProductEntity;
import com.laptrinhweb.Entity.ProductOrderEntity;
import com.laptrinhweb.Key.ProductOrderKey;
import com.laptrinhweb.Service.Interface.IUserDetailService;
import com.laptrinhweb.repository.CustomerRepository;
import com.laptrinhweb.repository.LoginRepository;
import com.laptrinhweb.repository.OrderRepository;
import com.laptrinhweb.repository.ProductOrderRepository;
import com.laptrinhweb.repository.ProductRepository;

@Service
public class UserDetailService implements IUserDetailService{
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	CustomerRepository userRepository;
	@Autowired
	User user;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductOrderRepository productOrderRepository;
	@Autowired    
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CheckLogic checklogic;
	
	@Override
	public List <UserDTO> getAllUser(){
		List <UserDTO> result = new ArrayList<UserDTO>();
		List<CustomerEntity> list = userRepository.findAll();
		for (CustomerEntity x: list) {
			result.add(user.toDTO(x));
		}
		return result;
	}
	
	@Override
	public UserDTO detailUser(String username) {
		for (LoginEntity x: loginRepository.findAll()) {
			if (x.getUsername().equals(username)) {
				return user.toDTO(x.getCustomer());
			}
		}
		return null;
	}
	
	@Override
	public String updateInfoUser(String username, String fullname, String phone, String sex, String address) {
		try {
		LoginEntity loginEntity = loginRepository.findById(username).get();
		CustomerEntity userEntity = loginEntity.getCustomer();
		if (fullname.isEmpty() && phone.isEmpty() && sex.equals("Chọn giới tính") && address.isEmpty()) return "Vui lòng nhập thông tin cá nhân chính xác!";
		
		//Fix lỗ hổng XSS
		
		
		if (!(checklogic.checkStringAlphabet(fullname.toLowerCase())
				&& checklogic.checkStringNumber(phone.toLowerCase())
				&& checklogic.checkStringAlphabet(address.toLowerCase()))) {
			return "Cập nhật thất bại!!!";
		}
		 
		 
		if (fullname != "") userEntity.setName(fullname);
		
		if (phone != "") userEntity.setPhone(phone);
		
		if (sex != "") {
			if (sex.equals("Nam")) userEntity.setSex(true);
			else if (sex.equals("Nữ")) userEntity.setSex(false);
		}
		
		if (address != "") userEntity.setAddress(address);
		userRepository.save(userEntity);
		return "Cập nhật thành công!!!";
		}
		catch(Exception e) {
			return "Cập nhật thất bại!!!";
		}
	}
	
	@Override
	public String addProductToCart(String pid, String username, Long quantity) {
		try {
		OrderEntity orderEntity = new OrderEntity();
		LoginEntity loginEntity = loginRepository.findById(username).get();
		CustomerEntity userEntity = loginEntity.getCustomer();
		ProductEntity productEntity = productRepository.findById(Long.parseLong(pid)).get();
		for (OrderEntity x: orderRepository.findAll()) {
			if (x.getCustomer().getId() == userEntity.getId() ) {
				orderEntity = x;
			}
		}
		if (orderEntity.getCustomer() == null) {
			orderEntity.setCustomer(userEntity);
			orderEntity.setPayment_status("Đang mua hàng!!!");
			orderEntity.setDiscount(0.0);
			orderEntity.setTotal_price(0L);
			orderRepository.save(orderEntity);
			for (OrderEntity x: orderRepository.findAll()) {
				if (x.getCustomer().getId() == userEntity.getId() ) {
					orderEntity = x;
				}
			}
		}
		Logger logger = LoggerFactory.getLogger(UserDetailService.class);
		ProductOrderEntity productOrderEntity = new ProductOrderEntity();
		Double total = orderEntity.getTotal_price() + productEntity.getMoney()*quantity*( (100.0-productEntity.getDiscount())/100.0 );
		orderEntity.setTotal_price(Math.round(total));
		for (ProductOrderEntity x : productOrderRepository.findAll()) {
			if (x.getProduct().getId() == productEntity.getId() && x.getOrder().getId() == orderEntity.getId()) {
				productOrderEntity = x;
			}
		}
		if (productOrderEntity.getProduct() != null) {
			productOrderEntity.setQuantity(productOrderEntity.getQuantity() + quantity);
		}
		else {
			ProductOrderKey productOrderKey = new ProductOrderKey(productEntity.getId(),orderEntity.getId());
			productOrderEntity.setOrder(orderEntity);
			productOrderEntity.setProduct(productEntity);		
			productOrderEntity.setQuantity(quantity);
			productOrderEntity.setId(productOrderKey);
		}
		productOrderRepository.save(productOrderEntity);
		orderRepository.save(orderEntity);
		return "Thêm sản phẩm vào giỏ hàng thành công!!!";
		} catch(Exception e) {
			Logger logger = LoggerFactory.getLogger(UserDetailService.class);
			logger.error(e.toString());
			return "Thêm sản phẩm vào giỏ hàng thất bại!!!";
		}
	}
	@Override
	public String removeProductToCart(String pid, String username) {
		try {
			OrderEntity orderEntity = new OrderEntity();
			LoginEntity loginEntity = loginRepository.findById(username).get();
			CustomerEntity userEntity = loginEntity.getCustomer();
			ProductEntity productEntity = productRepository.findById(Long.parseLong(pid)).get();
			for (OrderEntity x: orderRepository.findAll()) {
				if (x.getCustomer().getId() == userEntity.getId() ) {
					orderEntity = x;
				}
			}
			List <ProductOrderEntity> product_list = productOrderRepository.findAll();
			for (ProductOrderEntity x : product_list) {
				if (x.getProduct().getId() == productEntity.getId() && x.getOrder().getId() == orderEntity.getId()) {
					orderEntity.setTotal_price(orderEntity.getTotal_price() - x.getQuantity()*productEntity.getMoney());
					orderRepository.save(orderEntity);
					productOrderRepository.delete(x);
				}
			}
			return "Xóa sản phẩm khỏi giỏ hàng thành công!!!";
		} catch (Exception e) {
			return "Xóa sản phẩm khỏi giỏ hàng thất bại!!!";
		}
	}
	
	@Override
	public Long total(String username) {
		OrderEntity orderEntity = new OrderEntity();
		LoginEntity loginEntity = loginRepository.findById(username).get();
		CustomerEntity userEntity = loginEntity.getCustomer();
		for (OrderEntity x: orderRepository.findAll()) {
			if (x.getCustomer().getId() == userEntity.getId() ) {
				orderEntity = x;
			}
		}
		if (orderEntity.getCustomer() != null) return orderEntity.getTotal_price();
		else return 0L;
	}
	
	@Override
	public List<OrderEntity> getAllOrder(String username){
		OrderEntity orderEntity = new OrderEntity();
		LoginEntity loginEntity = loginRepository.findById(username).get();
		CustomerEntity userEntity = loginEntity.getCustomer();
		return userEntity.getOrders();
	}
	@Override
	public OrderEntity getCart(String username) {
		OrderEntity orderEntity = new OrderEntity();
		LoginEntity loginEntity = loginRepository.findById(username).get();
		CustomerEntity userEntity = loginEntity.getCustomer();
		for (OrderEntity x: orderRepository.findAll()) {
			if (x.getCustomer().getId() == userEntity.getId())
				orderEntity = x;
		}
		return orderEntity;
	}
	@Override
	public String confirmcart(String username) {
		try {
			LoginEntity loginEntity = loginRepository.findById(username).get();
			CustomerEntity userEntity = loginEntity.getCustomer();
			OrderEntity orderEntity = getCart(username);
			orderEntity.setPayment_status("Đang chờ xác nhận!!!");
			orderRepository.save(orderEntity);
			OrderEntity neworder = new OrderEntity();
			neworder.setCustomer(userEntity);
			neworder.setPayment_status("Đang mua hàng");
			neworder.setTotal_price(0L);
			neworder.setDiscount(0.0);
			orderRepository.save(neworder);
			return "Đặt hàng thành công!!!";
		} catch (Exception e) {
			return "Đặt hàng thất bại!!!";
		}
	}
	@Override
	public String changepassword(String username, String oldpassword, String newpassword, String confirm) {
		try {
		Logger logger = LoggerFactory.getLogger(UserDetailService.class);
		Boolean flag = false;
		LoginEntity login = new LoginEntity();
		for (LoginEntity x: loginRepository.findAll()) {
			if (x.getUsername().equals(username)) {
				flag = true;
				login = x;
			}
		}
		if (!flag) return "Sai tên đăng nhập!!!";
		if (bCryptPasswordEncoder.matches(oldpassword,login.getPassword())) {
			if (newpassword.equals(confirm)) {
				login.setPassword(bCryptPasswordEncoder.encode(newpassword));
				loginRepository.save(login);
				return "Đặt lại mật khẩu thành công!!!";
			}
		}
		else return "Sai mật khẩu!!!";
		return null;
		} catch (Exception e) {
			return "Đặt lại mật khẩu thất bại!!!";
		}
	}
	@Override
	public String adminconfirmcart(String customerid) {
		try {
		OrderEntity orderEntity = new OrderEntity();
		for (OrderEntity x: orderRepository.findAll()) {
			if (x.getCustomer().getId() == Long.parseLong(customerid))
				orderEntity = x;
		}
		orderEntity.setPayment_status("Đã xác nhận giao hàng, vui lòng kiểm tra điện thoại thường xuyên!!!");
		orderRepository.save(orderEntity);
		return "Xác nhận giao hàng thành công!!!";
		} catch (Exception e) {
			return "Xác nhận giao hàng thất bại!!!";
		}
	}

	@Override
	public String addNewUser(String fullname, String username, String email, String phone, String password,
			String gender) {
		try {
				if (loginRepository.findByUsername(username).size() > 0) return "fail";
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setName(fullname);
				customerEntity.setEmail(email);
				customerEntity.setPhone(phone);
				customerEntity.setSex(gender.equals("Nam") ? true : false);
				customerEntity.setImage("");
				LoginEntity loginEntity = new LoginEntity();
				loginEntity.setUsername(username);
				loginEntity.setPassword(bCryptPasswordEncoder.encode(password));
				loginEntity.setRole(false);
				userRepository.save(customerEntity);
				loginEntity.setCustomer(customerEntity);
				loginRepository.save(loginEntity);
				customerEntity.setLogin(loginEntity);
				OrderEntity orderEntity = new OrderEntity();
				orderEntity.setCustomer(customerEntity);
				orderEntity.setTotal_price(0L);
				orderRepository.save(orderEntity);
				List<OrderEntity> orderList = new ArrayList<>();
				orderList.add(orderEntity);
				customerEntity.setOrders(orderList);
				userRepository.save(customerEntity);
				return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
}
