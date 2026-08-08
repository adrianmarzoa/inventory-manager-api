-- ============================================================
--  Inventory Manager
-- ============================================================

DROP TABLE IF EXISTS stock_movements CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS warehouses CASCADE;

-- ------------------------------------------------------------
--  categories
-- ------------------------------------------------------------
CREATE TABLE categories
(
    id   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- ------------------------------------------------------------
--  suppliers
-- ------------------------------------------------------------
CREATE TABLE suppliers
(
    id    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name  VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE,
    phone VARCHAR(30)
);

-- ------------------------------------------------------------
--  warehouses
-- ------------------------------------------------------------
CREATE TABLE warehouses
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     VARCHAR(150) NOT NULL,
    location VARCHAR(255)
);

-- ------------------------------------------------------------
--  products
-- ------------------------------------------------------------
CREATE TABLE products
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sku         VARCHAR(50)    NOT NULL UNIQUE,
    name        VARCHAR(200)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL DEFAULT 0.00 CHECK (price >= 0),
    stock       INTEGER        NOT NULL DEFAULT 0 CHECK (stock >= 0),
    category_id INTEGER        REFERENCES categories (id) ON DELETE SET NULL,
    supplier_id INTEGER        REFERENCES suppliers (id) ON DELETE SET NULL
);

CREATE INDEX idx_product_category ON products (category_id);
CREATE INDEX idx_product_supplier ON products (supplier_id);

-- ------------------------------------------------------------
--  stock_movements
-- ------------------------------------------------------------
CREATE TABLE stock_movements
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type         VARCHAR(3) NOT NULL CHECK (type IN ('IN', 'OUT')),
    quantity     INTEGER    NOT NULL CHECK (quantity > 0),
    date         TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    product_id   INTEGER    NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    warehouse_id INTEGER    NOT NULL REFERENCES warehouses (id) ON DELETE RESTRICT
);

CREATE INDEX idx_stock_movement_product ON stock_movements (product_id);
CREATE INDEX idx_stock_movement_warehouse ON stock_movements (warehouse_id);
CREATE INDEX idx_stock_movement_date ON stock_movements (date);
