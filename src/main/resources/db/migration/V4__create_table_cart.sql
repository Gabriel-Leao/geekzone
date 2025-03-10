CREATE TABLE cart (
    id char(36) PRIMARY KEY,
    user_id char(36) NOT NULL,
    product_id char(36) NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CHECK (quantity > 0),
    UNIQUE (user_id, product_id)
);
