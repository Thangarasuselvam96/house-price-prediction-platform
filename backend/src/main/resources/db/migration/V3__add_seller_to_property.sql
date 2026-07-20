ALTER TABLE property
ADD COLUMN seller_id BIGINT;

ALTER TABLE property
ADD CONSTRAINT fk_property_seller
FOREIGN KEY (seller_id)
REFERENCES users(id);