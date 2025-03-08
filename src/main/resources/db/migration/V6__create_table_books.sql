CREATE TABLE books (
    id char(36) PRIMARY KEY,
    product_id char(36) REFERENCES products(id) ON DELETE CASCADE,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE
);
