-- CREATE TABLE products (
--                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
--                           title VARCHAR(255) NOT NULL,
--                           image_url VARCHAR(500),
--                           price DECIMAL(10,2) NOT NULL,
--                           price_with_shipping DECIMAL(10,2) NOT NULL,
--                           short_description VARCHAR(500),
--                           detailed_description TEXT,
--                           stock_quantity INT NOT NULL
-- );

-- Insert example product
INSERT INTO products (title, image_url, price, price_with_shipping, short_description, detailed_description, stock_quantity)
VALUES (
           'Gaming Laptop',
           'https://example.com/laptop.jpg',
           1200.00,
           1250.00,
           'High-performance gaming laptop',
           'This gaming laptop features a powerful processor, high-end graphics card, and fast SSD storage.',
           10
       );

