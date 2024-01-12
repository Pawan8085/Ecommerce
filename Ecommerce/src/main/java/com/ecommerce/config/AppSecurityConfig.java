package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {
	
	
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http)throws Exception{
		
		http.authorizeHttpRequests()
		.requestMatchers( "/myApp/admin/register", "/myApp/user/register").permitAll()
		.requestMatchers("/myApp/admin/signIn", "/myApp/admin/category", "/myApp/admin/product/*", "/myApp/admin/totaluser", "/myApp/admin/revenue", "/myApp/admin/product/detail/*", "/myApp/admin/product/update/*")
		.hasAuthority("ADMIN")
		.requestMatchers( "/myApp/user/signIn", "/myApp/user/product/*/*", "/myApp/user/cart/*/*", "/myApp/user/cart/**", "/myApp/user/cart", "/myApp/user/orders", "/myApp/user/products", "/myApp/user/products/**", "/myApp/user/products/rating/*/*", "/myApp/user/products/sort/*/*", "/myApp/user/products/price/*/*/*", "/myApp/user/products/com/**")
		.hasAuthority("USER")
		.and()
		.csrf().disable()
		.formLogin()
		.and()
		.httpBasic();
		
		return http.build();
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
