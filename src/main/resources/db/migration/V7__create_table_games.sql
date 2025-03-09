CREATE TABLE games (
    id char(36) PRIMARY KEY,
    product_id char(36) NOT NULL,
    developer VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
