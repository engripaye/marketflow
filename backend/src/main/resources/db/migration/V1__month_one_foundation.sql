CREATE TABLE app_users (
  id UUID PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE, password_hash VARCHAR(100) NOT NULL,
  full_name VARCHAR(120) NOT NULL, platform_role VARCHAR(20) NOT NULL DEFAULT 'USER',
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(), updated_at TIMESTAMPTZ NOT NULL DEFAULT now(), version BIGINT NOT NULL DEFAULT 0
);
CREATE TABLE businesses (
  id UUID PRIMARY KEY, name VARCHAR(120) NOT NULL, slug VARCHAR(80) NOT NULL UNIQUE,
  currency VARCHAR(3) NOT NULL DEFAULT 'NGN', owner_id UUID NOT NULL REFERENCES app_users(id),
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(), updated_at TIMESTAMPTZ NOT NULL DEFAULT now(), version BIGINT NOT NULL DEFAULT 0
);
CREATE TABLE memberships (
  id UUID PRIMARY KEY, business_id UUID NOT NULL REFERENCES businesses(id) ON DELETE CASCADE,
  user_id UUID NOT NULL REFERENCES app_users(id) ON DELETE CASCADE, role VARCHAR(20) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(), UNIQUE (business_id, user_id)
);
CREATE TABLE products (
  id UUID PRIMARY KEY, business_id UUID NOT NULL REFERENCES businesses(id) ON DELETE CASCADE,
  name VARCHAR(160) NOT NULL, sku VARCHAR(80) NOT NULL, description VARCHAR(1000),
  unit_price NUMERIC(19,2) NOT NULL CHECK (unit_price >= 0), reorder_level INTEGER NOT NULL DEFAULT 0 CHECK (reorder_level >= 0),
  active BOOLEAN NOT NULL DEFAULT TRUE, created_at TIMESTAMPTZ NOT NULL DEFAULT now(), updated_at TIMESTAMPTZ NOT NULL DEFAULT now(), version BIGINT NOT NULL DEFAULT 0,
  UNIQUE (business_id, sku)
);
CREATE TABLE inventory_items (
  id UUID PRIMARY KEY, product_id UUID NOT NULL UNIQUE REFERENCES products(id) ON DELETE CASCADE,
  quantity INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0), updated_at TIMESTAMPTZ NOT NULL DEFAULT now(), version BIGINT NOT NULL DEFAULT 0
);
CREATE TABLE inventory_adjustments (
  id UUID PRIMARY KEY, product_id UUID NOT NULL REFERENCES products(id) ON DELETE CASCADE,
  performed_by UUID NOT NULL REFERENCES app_users(id), delta INTEGER NOT NULL CHECK (delta <> 0), reason VARCHAR(300) NOT NULL,
  quantity_after INTEGER NOT NULL CHECK (quantity_after >= 0), created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE TABLE customers (
  id UUID PRIMARY KEY, business_id UUID NOT NULL REFERENCES businesses(id) ON DELETE CASCADE,
  full_name VARCHAR(160) NOT NULL, phone VARCHAR(30), email VARCHAR(255), notes VARCHAR(1000),
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(), updated_at TIMESTAMPTZ NOT NULL DEFAULT now(), version BIGINT NOT NULL DEFAULT 0
);
CREATE INDEX idx_products_business ON products(business_id);
CREATE INDEX idx_customers_business_name ON customers(business_id, full_name);
CREATE INDEX idx_adjustments_product_created ON inventory_adjustments(product_id, created_at DESC);
