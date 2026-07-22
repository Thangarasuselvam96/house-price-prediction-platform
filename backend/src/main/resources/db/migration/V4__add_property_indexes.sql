CREATE INDEX idx_property_city
    ON property(city);

CREATE INDEX idx_property_price
    ON property(price);

CREATE INDEX idx_property_type
    ON property(property_type);

CREATE INDEX idx_property_seller
    ON property(seller_id);

CREATE INDEX idx_city_type
    ON property(city, property_type);