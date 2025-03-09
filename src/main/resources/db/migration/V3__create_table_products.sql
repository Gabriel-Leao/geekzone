CREATE TABLE products (
    id char(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    category VARCHAR(50) NOT NULL,
    brand_id char(36) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE RESTRICT,
    CHECK (quantity >= 0)
);
