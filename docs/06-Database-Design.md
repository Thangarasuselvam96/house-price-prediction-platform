# Database Design

## Overview

The application uses PostgreSQL as the primary relational database.

The database is designed to support user management, property listings, favorites, enquiries, and future platform enhancements.

---

# Entity Relationship Overview

The core entities are:

* User
* Property
* PropertyImage
* Favorite
* Enquiry

Relationship summary:

* One User (Seller) can own many Properties.
* One Property can have many Property Images.
* One Buyer can save many Properties.
* One Property can be saved by many Buyers.
* One Buyer can send many Enquiries.
* One Property can receive many Enquiries.

---

# Entity: User

Purpose:

Stores information about buyers, sellers, and administrators.

| Column       | Type         | Constraints            |
| ------------ | ------------ | ---------------------- |
| id           | BIGSERIAL    | Primary Key            |
| first_name   | VARCHAR(100) | Not Null               |
| last_name    | VARCHAR(100) | Not Null               |
| email        | VARCHAR(255) | Unique, Not Null       |
| password     | VARCHAR(255) | Not Null               |
| phone_number | VARCHAR(20)  | Nullable               |
| role         | VARCHAR(20)  | BUYER / SELLER / ADMIN |
| created_at   | TIMESTAMP    | Not Null               |
| updated_at   | TIMESTAMP    | Not Null               |

---

# Entity: Property

Purpose:

Stores property listings.

| Column        | Type          | Constraints                          |
| ------------- | ------------- | ------------------------------------ |
| id            | BIGSERIAL     | Primary Key                          |
| seller_id     | BIGINT        | Foreign Key → User.id                |
| title         | VARCHAR(255)  | Not Null                             |
| description   | TEXT          | Nullable                             |
| property_type | VARCHAR(50)   | Not Null                             |
| price         | DECIMAL(15,2) | Not Null                             |
| area_sqft     | DECIMAL(10,2) | Not Null                             |
| bedrooms      | INTEGER       | Not Null                             |
| bathrooms     | INTEGER       | Not Null                             |
| parking       | BOOLEAN       | Not Null                             |
| property_age  | INTEGER       | Nullable                             |
| address       | TEXT          | Not Null                             |
| city          | VARCHAR(100)  | Not Null                             |
| state         | VARCHAR(100)  | Not Null                             |
| pincode       | VARCHAR(10)   | Not Null                             |
| latitude      | DECIMAL(10,8) | Nullable                             |
| longitude     | DECIMAL(11,8) | Nullable                             |
| status        | VARCHAR(20)   | PENDING / APPROVED / REJECTED / SOLD |
| created_at    | TIMESTAMP     | Not Null                             |
| updated_at    | TIMESTAMP     | Not Null                             |

---

# Entity: PropertyImage

Purpose:

Stores image URLs for each property.

| Column        | Type      | Constraints               |
| ------------- | --------- | ------------------------- |
| id            | BIGSERIAL | Primary Key               |
| property_id   | BIGINT    | Foreign Key → Property.id |
| image_url     | TEXT      | Not Null                  |
| display_order | INTEGER   | Default 1                 |

---

# Entity: Favorite

Purpose:

Stores properties saved by buyers.

| Column      | Type      | Constraints               |
| ----------- | --------- | ------------------------- |
| id          | BIGSERIAL | Primary Key               |
| buyer_id    | BIGINT    | Foreign Key → User.id     |
| property_id | BIGINT    | Foreign Key → Property.id |
| created_at  | TIMESTAMP | Not Null                  |

Business Rule:

* A buyer cannot save the same property more than once.

(Implement using a unique constraint on `buyer_id` and `property_id`.)

---

# Entity: Enquiry

Purpose:

Stores enquiries sent by buyers to sellers.

| Column      | Type        | Constraints               |
| ----------- | ----------- | ------------------------- |
| id          | BIGSERIAL   | Primary Key               |
| buyer_id    | BIGINT      | Foreign Key → User.id     |
| property_id | BIGINT      | Foreign Key → Property.id |
| message     | TEXT        | Not Null                  |
| status      | VARCHAR(20) | OPEN / CLOSED             |
| created_at  | TIMESTAMP   | Not Null                  |

---

# Relationships

## User → Property

Relationship:

One Seller can own many Properties.

Cardinality:

1 → Many

---

## Property → PropertyImage

Relationship:

One Property can have multiple images.

Cardinality:

1 → Many

---

## Buyer → Favorite

Relationship:

One Buyer can save many Properties.

Cardinality:

Many → Many

Implemented through the Favorite table.

---

## Buyer → Enquiry

Relationship:

One Buyer can create many Enquiries.

Cardinality:

1 → Many

---

## Property → Enquiry

Relationship:

One Property can receive many Enquiries.

Cardinality:

1 → Many

---

# Indexes

Create indexes on frequently searched columns:

* property.city
* property.price
* property.property_type
* property.bedrooms
* property.status
* user.email

These indexes improve search performance.

---

# Future Enhancements

Possible future tables:

* Review
* PropertyViewHistory
* Notification
* LoanApplication
* PricePredictionHistory
* ChatMessage
* Appointment
* Payment
* Subscription
