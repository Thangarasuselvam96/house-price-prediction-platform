# Property Domain Model

## Purpose

Represents a property listing that can be published for sale or rent.

## Fields

| Field | Type | Required | Description |
|------|------|----------|-------------|
| id | BIGSERIAL | Yes | Primary key |
| title | VARCHAR(255) | Yes | Property title |
| description | TEXT | No | Property description |
| property_type | VARCHAR(50) | Yes | Apartment, Villa, House, Plot |
| listing_type | VARCHAR(20) | Yes | SALE or RENT |
| price | NUMERIC(12,2) | Yes | Property price |
| area_sqft | INTEGER | Yes | Area in square feet |
| city | VARCHAR(100) | Yes | City |
| state | VARCHAR(100) | Yes | State |
| pincode | VARCHAR(10) | Yes | Postal code |
| listing_status | VARCHAR(20) | Yes | ACTIVE, INACTIVE, SOLD |
| created_at | TIMESTAMP | Yes | Creation timestamp |
| updated_at | TIMESTAMP | Yes | Last modification timestamp |

## Business Rules

- Title is mandatory.
- Price must be greater than zero.
- Area must be greater than zero.
- Listing status defaults to ACTIVE.