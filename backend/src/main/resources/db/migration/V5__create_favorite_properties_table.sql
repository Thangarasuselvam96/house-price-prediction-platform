CREATE TABLE favorite_properties (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    property_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_favorite_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_favorite_property FOREIGN KEY (property_id) REFERENCES property(id),
    CONSTRAINT uk_user_property UNIQUE(user_id, property_id)
)