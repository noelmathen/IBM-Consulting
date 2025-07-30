CREATE DATABASE sample_library;
USE sample_library;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    genre VARCHAR(100),
    page_count INT,
    published_year INT
);

INSERT INTO books (title, author, genre, page_count, published_year) VALUES
('The Hobbit', 'J.R.R. Tolkien', 'fantasy', 310, 1937),
('1984', 'George Orwell', 'dystopian', 328, 1949),
('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'fantasy', 309, 1997),
('To Kill a Mockingbird', 'Harper Lee', 'classic', 281, 1960),
('The Great Gatsby', 'F. Scott Fitzgerald', 'classic', 180, 1925),
('A Game of Thrones', 'George R.R. Martin', 'fantasy', 694, 1996);

SELECT * FROM books;

SELECT title, author
FROM books
WHERE genre = 'fantasy';

SELECT COUNT(*) AS total_books FROM books;

SELECT SUM(page_count) AS total_pages FROM books;

SELECT MIN(page_count) AS min_pages, MAX(page_count) AS max_pages FROM books;

SELECT *
FROM books
ORDER BY title ASC
LIMIT 5;

SELECT *
FROM books
WHERE genre = 'fantasy' AND page_count > 300;






SELECT genre, COUNT(*) AS book_count
FROM books
GROUP BY genre;

SELECT genre, COUNT(*) AS book_count
FROM books
GROUP BY genre
HAVING COUNT(*) > 5;




CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone_number CHAR(10)
);

INSERT INTO customers (customer_id, name, email, phone_number) VALUES
(1, 'John Doe', 'john.doe@example.com', '1234567890'),
(2, 'Jane Smith', 'jane.smith@example.com', '0987654321'),
(3, 'Alice Johnson', 'alice.johnson@example.com', NULL);

UPDATE customers
SET email = 'new_email@example.com'
WHERE customer_id = 1;

DELETE FROM customers
WHERE phone_number IS NULL;

ALTER TABLE customers
ADD city VARCHAR(255);

SELECT * FROM customers;
