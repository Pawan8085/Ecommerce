# *Rest Api for Ecommerce application
### ER Diagram :

![Ecommerce_er](https://user-images.githubusercontent.com/101393436/230717165-fbecb33c-631b-408d-8fa9-0e03a8bb74d5.png)

#### Tech stack :- SpringBoot, Java, Hibernate, MySql, Spring Security

# FEATURES :-<br>
### ADMIN :-
. Register Login <br>
. Add Category, Add Product<br>
. Updated Product<br>
. Total Users <br>
. Total Revenue<br>
. Product details : Sold Products, Revenue, Stock <br>

### USER :-
. Register Login <br>
. Buy Product<br>
. Add to Cart<br>
. Orders<br>
. Sorting & Filtering<br>
. Comments & Ratings<br>


&nbsp;

## ADMIN :-
### Register Admin
`POST /ecommerce/admin/register`
```
http://localhost:8080/ecommerce/admin/register
```
### Login Admin
`POST /ecommerce/admin/login`
```
http://localhost:8080/ecommerce/admin/login
```
### Add Category
`POST /ecommerce/admin/category`
```
http://localhost:8080/ecommerce/admin/category
```
### Get All Category
`GET /ecommerce/admin/category`
```
http://localhost:8080/ecommerce/admin/category
```
### Add Product to the Category
`POST /ecommerce/admin/product/{id}`
```
http://localhost:8080/ecommerce/admin/product/{id}
```
### Get total User count
`GET /ecommerce/admin/totaluser`
```
http://localhost:8080/ecommerce/admin/totaluser
```
### Get total Revenue
`GET /ecommerce/admin/revenue`
```
http://localhost:8080/ecommerce/admin/revenue
```
### Get Product detail
`GET /ecommerce/admin/product/detail/{id}`
```
http://localhost:8080/ecommerce/admin/product/detail/{id}
```
### Update Product details
`PUT ecommerce/admin/product/update/{id}`
```
http://localhost:8080/ecommerce/admin/product/update/{id}
```


&nbsp;

## USER :-
### Register User
`POST /ecommerce/user/register`
```
http://localhost:8080/ecommerce/user/register
```
### Login User
`POST /ecommerce/user/login`
```
http://localhost:8080/ecommerce/user/login
```
### By Product
`GET /ecommerce/user/product/{productid}/{quantity}`
```
http://localhost:8080/ecommerce/user/product/{productid}/{quantity}
```
### Add Product to Cart
`POST /ecommerce/user/cart/{productid}/{quantity}`
```
http://localhost:8080/ecommerce/user/cart/{productid}/{quantity}
```
### By Product from Cart
`GET /ecommerce/user/cart/{cartid}`
```
http://localhost:8080/ecommerce/user/cart/{cartid}
```
### Get all from Cart 
`GET /ecommerce/user/cart`
```
http://localhost:8080/ecommerce/user/cart
```
### Get all from Order 
`GET /ecommerce/user/orders`
```
http://localhost:8080/ecommerce/user/orders
```
### Get all Products
`GET /ecommerce/user/products`
```
http://localhost:8080/ecommerce/user/products
```
### Search Product
`GET /ecommerce/user/products/{keyword}`
```
http://localhost:8080/ecommerce/user/products/{keyword}
```
### Search Product And filter by Rating
`GET /ecommerce/user/products/{keyword}/{rating}`
```
http://localhost:8080/ecommerce/user/products/{keyword}/{rating}
```
### Search Product And filter by Price
`GET /ecommerce/user/products/{keyword}/{min}/{max}`
```
http://localhost:8080/ecommerce/user/products/{keyword}/{min}/{max}
```
### Add Comment and Rating to the Product
`POST /ecommerce/user/products/com/{productid}`
```
http://localhost:8080/ecommerce/user/products/com/{productid}
```
