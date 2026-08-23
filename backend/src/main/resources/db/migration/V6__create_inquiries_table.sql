CREATE TABLE inquiries (

   id BIGSERIAL PRIMARY KEY,

   buyer_id BIGINT NOT NULL,

   seller_id BIGINT NOT NULL,

   property_id BIGINT NOT NULL,

   message TEXT NOT NULL,

   status VARCHAR(30) NOT NULL,

   created_at TIMESTAMP NOT NULL,

   updated_at TIMESTAMP NOT NULL,

   CONSTRAINT fk_inquiry_buyer
       FOREIGN KEY (buyer_id)
           REFERENCES users(id),

   CONSTRAINT fk_inquiry_seller
       FOREIGN KEY (seller_id)
           REFERENCES users(id),

   CONSTRAINT fk_inquiry_property
       FOREIGN KEY (property_id)
           REFERENCES property(id)
);