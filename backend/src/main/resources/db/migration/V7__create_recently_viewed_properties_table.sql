CREATE TABLE recently_viewed_properties (

   id BIGSERIAL PRIMARY KEY,

   user_id BIGINT NOT NULL,

   property_id BIGINT NOT NULL,

   last_viewed_at TIMESTAMP NOT NULL,

   CONSTRAINT fk_recently_viewed_property_user
       FOREIGN KEY (user_id)
           REFERENCES users(id),

   CONSTRAINT fk_recently_viewed_property_property
       FOREIGN KEY (property_id)
           REFERENCES property(id),

   CONSTRAINT uk_recently_viewed_user_property UNIQUE(user_id, property_id)
);