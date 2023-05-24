package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {
	
	@Bean
	public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
	
	
		http.authorizeHttpRequests( (auth)->auth
				.antMatchers("/ecommerce/admin/login",
						"/ecommerce/admin/category", 
						"/ecommerce/admin/product/**",
				"/ecommerce/admin/totaluser", 
				"/ecommerce/admin/revenue",
				"/ecommerce/admin/product/detail/**")
				.hasAuthority("ADMIN")
				.antMatchers("/ecommerce/user/login",
						"/ecommerce/user/product/*/*",
						"/ecommerce/user/cart/*/*",
						"/ecommerce/user/cart/**", 
						"/ecommerce/user/cart",
						"/ecommerce/user/orders",
						"/ecommerce/user/products",
						"/ecommerce/user/products/**",
						"/ecommerce/user/products/rating/*/*",
						"/ecommerce/user/products/sort/*/*",
						"/ecommerce/user/products/price/*/*/*",
						"/ecommerce/user/products/com/**")
				.hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/ecommerce/admin/register", 
						"/ecommerce/user/register", 
						"/welcome")
				.permitAll()
				
		).csrf().disable()
		.httpBasic();
	
		return http.build();
   
	}
	
	
	

	@Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }

}
