CREATE TABLE cart (
    id char(36) PRIMARY KEY,
    user_id char(36) REFERENCES users(id) ON DELETE CASCADE,
    product_id char(36) REFERENCES products(id) ON DELETE CASCADE,
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (user_id, product_id)
);
