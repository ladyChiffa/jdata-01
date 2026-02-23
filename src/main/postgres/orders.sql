CREATE TABLE ORDERS (
 id SERIAL PRIMARY KEY,
 date DATE,
 customer_id INTEGER,
 product_name VARCHAR(50),
 amount INTEGER,
 FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);


