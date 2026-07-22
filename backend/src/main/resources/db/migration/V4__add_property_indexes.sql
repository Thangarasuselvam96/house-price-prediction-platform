CREATE INDEX idx_property_city
    ON properties(city);

CREATE INDEX idx_property_price
    ON properties(price);

CREATE INDEX idx_property_type
    ON properties(property_type);

CREATE INDEX idx_property_seller
    ON properties(seller_id);

CREATE INDEX idx_city_type
    ON properties(city, property_type);