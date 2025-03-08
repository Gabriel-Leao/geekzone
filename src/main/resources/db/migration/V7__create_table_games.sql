CREATE TABLE games (
    id char(36) PRIMARY KEY,
    product_id char(36) REFERENCES products(id) ON DELETE CASCADE,
    developer VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL
);
