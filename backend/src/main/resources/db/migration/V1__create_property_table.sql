CREATE TABLE property (
                          id BIGSERIAL PRIMARY KEY,

                          title VARCHAR(255) NOT NULL,
                          description TEXT,

                          property_type VARCHAR(50) NOT NULL,
                          listing_type VARCHAR(20) NOT NULL,

                          price NUMERIC(12,2) NOT NULL,
                          area_sqft INTEGER NOT NULL,

                          city VARCHAR(100) NOT NULL,
                          state VARCHAR(100) NOT NULL,
                          pincode VARCHAR(10) NOT NULL,

                          listing_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);