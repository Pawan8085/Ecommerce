package com.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productDetailId;
	private Integer productId;
	private Integer totalProduct;
	private Integer sold;
	private Integer totalRevenue;

}
