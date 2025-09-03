-- Customers
INSERT INTO customers (id, email, name) VALUES
(1, 'alice@example.com', 'Alice'),
(2, 'bob@example.com', 'Bob'),
(3, 'charlie@example.com', 'Charlie');

-- Products
INSERT INTO products (id, name, price) VALUES
(1, 'Laptop', 1200.00),
(2, 'Phone', 800.00),
(3, 'Headphones', 150.00),
(4, 'Keyboard', 90.00),
(5, 'Monitor', 300.00);

-- Orders (Alice makes 2 orders, Bob makes 1, Charlie none)
INSERT INTO orders (id, created_at, total_amount, customer_id) VALUES
(1, '2025-09-01 10:15:00', 1350.00, 1), -- Alice
(2, '2025-09-02 14:30:00', 2100.00, 1), -- Alice
(3, '2025-09-03 09:00:00', 890.00, 2);  -- Bob

-- Order_Products (linking orders to products)
-- Order 1: Laptop + Headphones
INSERT INTO order_products (order_id, product_id) VALUES
(1, 1),
(1, 3);

-- Order 2: Phone + Monitor + Keyboard
INSERT INTO order_products (order_id, product_id) VALUES
(2, 2),
(2, 5),
(2, 4);

-- Order 3: Phone + Keyboard
INSERT INTO order_products (order_id, product_id) VALUES
(3, 2),
(3, 4);