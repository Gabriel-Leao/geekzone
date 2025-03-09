CREATE TABLE books (
    id char(36) PRIMARY KEY,
    product_id char(36) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    UNIQUE (isbn)
);
