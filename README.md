MyRetail-API
============

MyRetail is a RestFul API designed to provide product information for the clients.


Getting Started
===============

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

Prerequisites
=============

1. Mongodb needs to be installed on host 127.0.0.1 at port 27017. 

Install/Deploy MyRetail-API
===========================

1. Open a command prompt/terminal and drive towards the extracted folder. (\MyRetail-API\target\)
2. Execute the following command to install the MyRetail-API :
	java -jar MyRetail-API-0.0.1-SNAPSHOT.jar
3. This should install/deploy MyRetail-API application at http://127.0.0.1:8080/products/{productId}


Testing
=======

1. Get Product ID - 13860428 --> Sending a GET request to the URI with the product ID 13860428 which should return a 200 OK.

Request :
		URL : http://127.0.0.1:8080/products/13860428
		Request Method : GET

Response :
		Status: 200 OK
		Content-Type: application/json
		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": 48.99
		} 

2. Get Product ID - 156  --> Sending a GET request to the URI with the product ID 156 which should return a 404 Not Found.

Request :
		URL : http://127.0.0.1:8080/products/156
		Request Method : GET
Response :
		Status: 404 Not Found
		Content-Type: application/json
		{
		    "status": "NOT_FOUND",
		    "timestamp": "29-09-2017 12:40:51",
		    "message": "Product - 156 was not found."
		}

3. Get Product ID -  asdf  --> Sending a GET request to the URI with the product ID 'asdf' which should return a 400 Bad Request.

Request :
		URL : http://127.0.0.1:8080/products/asdf
		Request Method : GET
Response :
		Status: 400 Bad Request
		Content-Type: application/json
		{
		    "status": "BAD_REQUEST",
		    "timestamp": "29-09-2017 12:42:58",
		    "message": "Invalid Param for Product ID."
		}

4. Get Product ID - *&  --> Sending a GET request to the URI with the product ID '*&' which should return a 400 Bad Request.

Request :
		URL : http://127.0.0.1:8080/products/*&
		Request Method : GET
Response :
		Status: 400 Bad Request
		Content-Type: application/json
		{
		    "status": "BAD_REQUEST",
		    "timestamp": "29-09-2017 12:42:58",
		    "message": "Invalid Param for Product ID."
		}
		
5. PUT Product ID - 13860428  --> Sending a PUT request to the URI with the product ID '13860428' which should return a 205 Reset Content.

Request :
		URL : http://127.0.0.1:8080/products/13860428
		Request Method : PUT
		Content-Type: application/json
		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": 74.56
		}
Response :
		Status: 205 Reset Content
		
		
6. PUT Product ID - 138604  --> Sending a PUT request to the URI with the product ID '138604' which should return a 409 Conflict.

Request :
		URL : http://127.0.0.1:8080/products/138604
		Request Method : PUT
		Content-Type: application/json
		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": 74.56
		}
Response :
		Status: 409 Conflict
		Content-Type: application/json
		{
		    "status": "CONFLICT",
		    "timestamp": "29-09-2017 01:02:28",
		    "message": "Product ID in request and param doesn't match."
		}

7. PUT Product ID - 189 --> Sending a PUT request to the URI with the product ID '189' which should return a 404 Not Found.

Request :
		URL : http://127.0.0.1:8080/products/189
		Request Method : PUT
		Content-Type: application/json
		{
		    "id": 189,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": 74.56
		}
Response :
		Status: 404 Not Found
		Content-Type: application/json
		{
		    "status": "NOT_FOUND",
		    "timestamp": "29-09-2017 01:01:15",
		    "message": "Product Name was not found for product ID -189"
		}

8. PUT Product ID - *&   --> Sending a PUT request to the URI with the product ID '*&' which should return a 400 Bad Request.

Request :
		URL : http://127.0.0.1:8080/products/*&
		Request Method : PUT
		Content-Type: application/json
		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": 74.56
		}
Response :
		Status: 400 Bad Request
		Content-Type: application/json
		{
		    "status": "BAD_REQUEST",
		    "timestamp": "29-09-2017 12:56:22",
		    "message": "Product ID is missing in the request param"
		}

9. PUT Product ID - 13860428  --> Sending a PUT request to the URI with the product ID 13860428 and Content Type as Text should return a 400 Bad Request.

Request :
		URL : http://127.0.0.1:8080/products/13860428
		Request Method : PUT
		Content-Type: Text
		{
		    "id": 13860428,
		    "name": "The Big Lebowski (Blu-ray)",
		    "current_price": 74.56
		}
Response :
		Status: 400 Bad Request
		Content-Type: application/json
		{
		    "status": "BAD_REQUEST",
		    "timestamp": "29-09-2017 01:04:01",
		    "message": "Please send the request in JSON Content Type"
		}
		
10. DELETE Product ID - 13860424  --> Sending a DELETE request to the URI with the product ID 13860424 which should return a 406 Not Acceptable. 

Request :
		URL : http://127.0.0.1:8080/products/13860424
		Request Method : DELETE
		Content-Type: Text
		{
	        "productId": 13860424,
	        "name": "Moment of truth (Blu-ray)",
	        "price": 45.21
	    }
Response :
		Status: 406 Not Acceptable
		Content-Type: application/json
		{
		    "status": "NOT_ACCEPTABLE",
		    "timestamp": "28-09-2017 09:56:53",
		    "message": "Http Request Method - DELETE is not supported"
		}
		
Open Source Tools/Technologies Used
======================
1. Spring Boot
2. MongoDB
3. POSTMAN plugin on chrome


Built With
==========
1. Maven
2. STS IDE
3. DevTools 

Acknowledgments
==============

 --> Read the documentation at https://docs.spring.io/spring-boot/docs/current/reference/html/
	