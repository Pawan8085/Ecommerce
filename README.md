# *Rest Api for Ecommerce application
## An Individual project built within 2 weeks
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
`POST /app/admin/register`
```
http://localhost:8080/app/admin/register
```
### Login Admin
`POST /app/admin/signIn`
```
http://localhost:8080/app/admin/signIn
```
### Add Category
`POST /app/admin/category`
```
http://localhost:8080/app/admin/category
```
### Get All Category
`GET /app/admin/category`
```
http://localhost:8080/app/admin/category
```
### Add Product to the Category
`POST /app/admin/product/{id}`
```
http://localhost:8080/app/admin/product/{id}
```
### Get total User count
`GET /app/admin/totaluser`
```
http://localhost:8080/app/admin/totaluser
```
### Get total Revenue
`GET /app/admin/revenue`
```
http://localhost:8080/app/admin/revenue
```
### Get Product detail
`GET /app/admin/product/detail/{id}`
```
http://localhost:8080/app/admin/product/detail/{id}
```
### Update Product details
`PUT app/admin/product/update/{id}`
```
http://localhost:8080/app/admin/product/update/{id}
```


&nbsp;

## USER :-
### Register User
`POST /app/user/register`
```
http://localhost:8080/app/user/register
```
### Login User
`POST /app/user/signIn`
```
http://localhost:8080/app/user/signIn
```
### By Product
`GET /app/user/product/{productid}/{quantity}`
```
http://localhost:8080/app/user/product/{productid}/{quantity}
```
### Add Product to Cart
`POST /app/user/cart/{productid}/{quantity}`
```
http://localhost:8080/app/user/cart/{productid}/{quantity}
```
### By Product from Cart
`GET /app/user/cart/{cartid}`
```
http://localhost:8080/app/user/cart/{cartid}
```
### Get all from Cart 
`GET /app/user/cart`
```
http://localhost:8080/app/user/cart
```
### Get all from Order 
`GET /app/user/orders`
```
http://localhost:8080/app/user/orders
```
### Get all Products
`GET /app/user/products`
```
http://localhost:8080/app/user/products
```
### Search Product
`GET /app/user/products/{keyword}`
```
http://localhost:8080/app/user/products/{keyword}
```
### Search Product And filter by Rating
`GET /app/user/products/{keyword}/{rating}`
```
http://localhost:8080/app/user/products/{keyword}/{rating}
```
### Search Product And filter by Price
`GET /app/user/products/{keyword}/{min}/{max}`
```
http://localhost:8080/app/user/products/{keyword}/{min}/{max}
```
### Add Comment and Rating to the Product
`POST /app/user/products/com/{productid}`
```
http://localhost:8080/app/user/products/com/{productid}
```
