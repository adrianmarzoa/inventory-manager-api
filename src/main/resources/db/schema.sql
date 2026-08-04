-- ============================================================
--  Inventory Manager
-- ============================================================

DROP TABLE IF EXISTS stock_movement CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS warehouse CASCADE;

-- ------------------------------------------------------------
--  category
-- ------------------------------------------------------------
CREATE TABLE category
(
    id   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- ------------------------------------------------------------
--  supplier
-- ------------------------------------------------------------
CREATE TABLE supplier
(
    id    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name  VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE,
    phone VARCHAR(30)
);

-- ------------------------------------------------------------
--  warehouse
-- ------------------------------------------------------------
CREATE TABLE warehouse
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     VARCHAR(150) NOT NULL,
    location VARCHAR(255)
);

-- ------------------------------------------------------------
--  product
-- ------------------------------------------------------------
CREATE TABLE product
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku         VARCHAR(50)    NOT NULL UNIQUE,
    name        VARCHAR(200)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL DEFAULT 0.00 CHECK (price >= 0),
    stock       INTEGER        NOT NULL DEFAULT 0 CHECK (stock >= 0),
    category_id INTEGER        REFERENCES category (id) ON DELETE SET NULL,
    supplier_id INTEGER        REFERENCES supplier (id) ON DELETE SET NULL
);

CREATE INDEX idx_product_category ON product (category_id);
CREATE INDEX idx_product_supplier ON product (supplier_id);

-- ------------------------------------------------------------
--  stock_movement
-- ------------------------------------------------------------
CREATE TABLE stock_movement
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type         VARCHAR(3) NOT NULL CHECK (type IN ('IN', 'OUT')),
    quantity     INTEGER    NOT NULL CHECK (quantity > 0),
    date         TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    product_id   INTEGER    NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    warehouse_id INTEGER    NOT NULL REFERENCES warehouse (id) ON DELETE RESTRICT
);

CREATE INDEX idx_stock_movement_product ON stock_movement (product_id);
CREATE INDEX idx_stock_movement_warehouse ON stock_movement (warehouse_id);
CREATE INDEX idx_stock_movement_date ON stock_movement (date);
