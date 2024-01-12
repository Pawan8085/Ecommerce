package com.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	
	@Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.productPrice, p.productHighlights) LIKE %?1%")
	public List<Product> searchProduct(String keyword);
	
	

//    @Query("SELECT p FROM Product p WHERE p.productRating >= :minRating")
//    List<Product> findByRatingGreaterThanEqual(int minRating);
    
    @Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.productPrice, p.productHighlights) LIKE %?1% AND p.productRating >= ?2")
    List<Product> searchProductByKeywordAndRating(String keyword, Integer minRating);

    
    
//    @Query("SELECT p FROM Product p WHERE p.productPrice >= :minPrice AND p.productPrice <= :maxPrice")
//    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
    
    
    @Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.productPrice, p.productHighlights) LIKE %?1% AND p.productPrice >= ?2 AND p.productPrice <= ?3")
    List<Product> searchProductByKeywordAndPriceRange(String keyword, Integer minPrice, Integer maxPrice);
    
    
    @Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.productPrice, p.productHighlights) LIKE %?1% ORDER BY "
            + "CASE WHEN ?2 = 'name' AND ?3 = 'asc' THEN p.productName END ASC,"
            + "CASE WHEN ?2 = 'name' AND ?3 = 'desc' THEN p.productName END DESC,"
            + "CASE WHEN ?2 = 'price' AND ?3 = 'asc' THEN p.productPrice END ASC,"
            + "CASE WHEN ?2 = 'price' AND ?3 = 'desc' THEN p.productPrice END DESC")
    public List<Product> searchProductAndSortByField(String keyword, String field, String order);


}
