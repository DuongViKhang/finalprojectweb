package com.laptrinhweb.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.laptrinhweb.Service.Implementation.UserService;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserService userService;
	@Autowired
	UserDetailsService userDetailsService;
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService) 
            .passwordEncoder(passwordEncoder());
    }
	
	 @Override
    protected void configure(HttpSecurity http) throws Exception  {
		http.authorizeRequests().antMatchers("/home", "/shop", "/contact", "/blog","/product-detail","/register","/registersave","/main/resources/**","/web/**","/admin/**").permitAll()
		.antMatchers("/manager/**").access("hasRole('ADMIN')")
		.anyRequest().authenticated()
	    .and().formLogin()
	    //.loginPage("/login")
	        .permitAll()
	    .defaultSuccessUrl("/home")
	    //.failureUrl("/blog")
	    //.loginProcessingUrl("/j_spring_security_check")
	    .usernameParameter("username")
	    .passwordParameter("password")
	    .and().logout().logoutSuccessUrl("/home").invalidateHttpSession(true)
	    .deleteCookies("JSESSIONID").permitAll();
		
		
		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
		//Fix lỗ hổng XSS
		http.headers().xssProtection().and().contentSecurityPolicy("script-src 'self'");
		
		//Thiết lập CSP Header
		http
        .headers()
            .contentSecurityPolicy("default-src 'self'; "
            		+ "script-src 'self' https://cdnjs.cloudflare.com; "
            		+ "style-src 'self' 'unsafe-inline'; "
            		+ "img-src 'self' data:; "
            		+ "font-src 'self'; "
            		+ "frame-ancestors 'none'; "
            		+ "block-all-mixed-content;");
	}
	 @Override
     public void configure(WebSecurity web) throws Exception {
         web
                 .ignoring()
                 .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**","/register","/registersave");
     }
}
