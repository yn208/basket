# basket
 Fresh Basket

Steps to run the application:
1. mvn clean package 
2. java -jar target/basket-0.0.1-SNAPSHOT.jar

Sample requests to get categories: GET Method
http://localhost:8080/searchCategory?orderBy=desc
Note: No mandatory props 
name - Contains match
orderBy - name desc or asc

Sample requests to get Items: GET Method
http://localhost:8080/searchItems?name=lays&order=rating:asc
Note: No mandatory props 
name - Contains match
rating - Greater than rating
category - Exact match
order - allowed values are name,rating,price eg: rating:desc or rating:asc