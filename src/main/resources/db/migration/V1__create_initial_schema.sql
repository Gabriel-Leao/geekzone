CREATE TABLE users (
    id         SERIAL       PRIMARY KEY,
    name       VARCHAR(150) NOT NULL,
    email      VARCHAR(200) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    birthdate  DATE         NOT NULL,
    role       VARCHAR(10)  NOT NULL,
    created_at TIMESTAMP    NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT UNIQUE_USER_EMAIL UNIQUE(email),
    CONSTRAINT CHECK_ROLE        CHECK (role IN ('USER', 'ADMIN'))
);

CREATE TABLE brands (
    id          SERIAL       PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    logo_url    VARCHAR(255),
    created_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id          SERIAL      PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT CATEGORY_NAME_UNIQUE UNIQUE (name)
);

CREATE TABLE products (
    id           SERIAL         PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    description  TEXT           NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    quantity     INT            NOT NULL,
    image_url    VARCHAR(255)   NOT NULL,
    release_date DATE           NOT NULL,
    category_id  INT,
    brand_id     INT,
    type         VARCHAR(50)    NOT NULL,
    attributes   JSONB          NOT NULL,
    created_at   TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_BRAND_ID_PRODUCT    FOREIGN KEY (brand_id)    REFERENCES brands(id)     ON DELETE CASCADE,
    CONSTRAINT FK_CATEGORY_ID_PRODUCT FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    CONSTRAINT CHECK_QTY_PRODUCT      CHECK (quantity >= 0)
);

CREATE TABLE cart (
    id         SERIAL    PRIMARY KEY,
    user_id    INT       NOT NULL,
    product_id INT       NOT NULL,
    quantity   INT       NOT NULL,
    created_at TIMESTAMP NOT NULL   DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL   DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_PRODUCT_ID_CART              FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT FK_USER_ID_CART                 FOREIGN KEY (user_id)    REFERENCES users(id)    ON DELETE CASCADE,
    CONSTRAINT CHECK_QTY_CART                  CHECK (quantity > 0),
    CONSTRAINT UNIQUE_USER_AND_PRODUCT_ID_CART UNIQUE (user_id, product_id)
);

CREATE TABLE wishlist (
    id          SERIAL      PRIMARY KEY,
    user_id     INT         NOT NULL,
    product_id  INT         NOT NULL,
    created_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_PRODUCT_ID_WISHLIST              FOREIGN KEY (product_id)     REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT FK_USER_ID_WISHLIST                 FOREIGN KEY (user_id)        REFERENCES users(id)    ON DELETE CASCADE,
    CONSTRAINT UNIQUE_USER_AND_PRODUCT_ID_WISHLIST UNIQUE (user_id, product_id)
);

CREATE TABLE orders (
    id          SERIAL         PRIMARY KEY,
    user_id     INT            NOT NULL,
    total       DECIMAL(10, 2) NOT NULL,
    status      VARCHAR(50)    NOT NULL,
    created_at  TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_USER_ID_ORDERS  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT CHECK_ORDER_STATUS CHECK (status IN ('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELED', 'REFUNDED', 'COMPLETED'))
);

CREATE TABLE order_items (
     id         SERIAL         PRIMARY KEY,
     order_id   INT            NOT NULL,
     product_id INT            NOT NULL,
     quantity   INT            NOT NULL,
     price      DECIMAL(10, 2) NOT NULL,
     created_at TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT FK_ORDER_ID_ORDER_ITEMS     FOREIGN KEY (order_id)   REFERENCES orders(id),
     CONSTRAINT FK_PRODUCT_ID_ORDER_ITEMS   FOREIGN KEY (product_id) REFERENCES products(id),
     CONSTRAINT CHECK_QTY_ORDER_ITEMS       CHECK (quantity > 0)
);
