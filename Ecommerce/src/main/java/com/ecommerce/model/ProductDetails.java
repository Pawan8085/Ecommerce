package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
