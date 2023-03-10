package com.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Boolean IsOutOfStock;
	private Integer productStock;
	private String productHighlights;
	private Integer productRating;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer ratingSum;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer ratingCount;
	private String imgaeLink;
	
	public Product() {
		this.IsOutOfStock=false;
		this.productRating=0;
		this.ratingSum=0;
		this.ratingCount=0;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Comment> comments;
	
	@ManyToOne
	@JsonBackReference
	private Category category;
}
