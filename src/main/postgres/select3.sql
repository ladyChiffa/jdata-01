SELECT product_name FROM orders WHERE customer_id in (
    SELECT id FROM customers WHERE LOWER(name) = 'alexey'
);
