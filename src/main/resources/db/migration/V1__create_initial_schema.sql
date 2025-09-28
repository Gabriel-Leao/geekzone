CREATE TABLE users (
    id            BIGINT       GENERATED    ALWAYS AS IDENTITY       PRIMARY KEY,
    name          VARCHAR(150) NOT NULL,
    email         VARCHAR(200) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    birthdate     DATE         NOT NULL,
    role          VARCHAR(10)  NOT NULL,
    street        VARCHAR(255) NOT NULL,
    number        VARCHAR(10)  NOT NULL,
    neighborhood  VARCHAR(255) NOT NULL,
    postal_code   CHAR(8)      NOT NULL,
    city          VARCHAR(255) NOT NULL,
    state         CHAR(2)      NOT NULL,
    complement    VARCHAR(255),
    created_at    TIMESTAMP    NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT UNIQUE_USER_EMAIL      UNIQUE(email),
    CONSTRAINT CHECK_USER_ROLE        CHECK (role  IN ('USER', 'ADMIN')),
    CONSTRAINT CHECK_USER_STATE       CHECK (state IN (
      'AC','AL','AP','AM','BA','CE','DF','ES','GO','MA',
      'MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN',
      'RS','RO','RR','SC','SP','SE','TO'
    ))
);

CREATE TABLE brands (
    id          BIGINT       GENERATED    ALWAYS AS IDENTITY       PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    logo_url    VARCHAR(255),
    created_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id          BIGINT       GENERATED    ALWAYS AS IDENTITY       PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL,
    description TEXT,
    created_at  TIMESTAMP    NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT UNIQUE_CATEGORY_NAME UNIQUE (name)
);

CREATE TABLE products (
    id           BIGINT         GENERATED    ALWAYS AS IDENTITY       PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    description  TEXT           NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    quantity     INT            NOT NULL,
    image_url    VARCHAR(255)   NOT NULL,
    release_date DATE           NOT NULL,
    category_id  BIGINT,
    brand_id     BIGINT,
    type         VARCHAR(50)    NOT NULL,
    attributes   JSONB          NOT NULL,
    created_at   TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP      NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_BRAND_ID_PRODUCT    FOREIGN KEY (brand_id)    REFERENCES brands(id)     ON DELETE CASCADE,
    CONSTRAINT FK_CATEGORY_ID_PRODUCT FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    CONSTRAINT CHECK_QTY_PRODUCT      CHECK (quantity >= 0)
);

CREATE TABLE cart (
    user_id    BIGINT    NOT NULL,
    product_id BIGINT    NOT NULL,
    quantity   INT       NOT NULL,
    created_at TIMESTAMP NOT NULL   DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL   DEFAULT CURRENT_TIMESTAMP,


    CONSTRAINT PK_CART                         PRIMARY KEY (user_id, product_id),
    CONSTRAINT FK_PRODUCT_ID_CART              FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT FK_USER_ID_CART                 FOREIGN KEY (user_id)    REFERENCES users(id)    ON DELETE CASCADE,
    CONSTRAINT CHECK_QTY_CART                  CHECK (quantity > 0)
);

CREATE TABLE wishlist (
    user_id     BIGINT      NOT NULL,
    product_id  BIGINT      NOT NULL,
    created_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT PK_WISHLIST                         PRIMARY KEY (user_id, product_id),
    CONSTRAINT FK_PRODUCT_ID_WISHLIST              FOREIGN KEY (product_id)     REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT FK_USER_ID_WISHLIST                 FOREIGN KEY (user_id)        REFERENCES users(id)    ON DELETE CASCADE
);

CREATE TABLE orders (
    id          BIGINT         GENERATED    ALWAYS AS IDENTITY       PRIMARY KEY,
    user_id     BIGINT         NOT NULL,
    total       NUMERIC(12, 2) NOT NULL,
    status      VARCHAR(50)    NOT NULL,
    created_at  TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_USER_ID_ORDERS  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT CHECK_ORDER_STATUS CHECK (status IN ('PENDING', 'SHIPPED', 'DELIVERED', 'CANCELED', 'REFUNDED', 'COMPLETED'))
);

CREATE TABLE order_items (
     id         BIGINT         GENERATED    ALWAYS AS IDENTITY       PRIMARY KEY,
     order_id   BIGINT         NOT NULL,
     product_id BIGINT         NOT NULL,
     quantity   INT            NOT NULL,
     price      DECIMAL(10, 2) NOT NULL,
     created_at TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP      NOT NULL     DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT FK_ORDER_ID_ORDER_ITEMS     FOREIGN KEY (order_id)   REFERENCES orders(id)    ON DELETE CASCADE,
     CONSTRAINT FK_PRODUCT_ID_ORDER_ITEMS   FOREIGN KEY (product_id) REFERENCES products(id)  ON DELETE CASCADE,
     CONSTRAINT CHECK_QTY_ORDER_ITEMS       CHECK (quantity > 0)
);
