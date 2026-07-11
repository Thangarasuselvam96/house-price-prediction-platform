# REST API Design

## Overview

This document defines the REST API endpoints for the House Price Prediction Platform.

### Base URL

```
/api/v1
```

---

# Authentication APIs

## Register User

| Field          | Value          |
| -------------- | -------------- |
| Method         | POST           |
| Endpoint       | /auth/register |
| Authentication | No             |

Request

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@email.com",
  "password": "password123",
  "role": "BUYER"
}
```

Response

```json
{
  "message": "User registered successfully"
}
```

---

## Login

| Field          | Value       |
| -------------- | ----------- |
| Method         | POST        |
| Endpoint       | /auth/login |
| Authentication | No          |

Response

```json
{
  "accessToken": "...",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

---

## Get Current User

| Field          | Value     |
| -------------- | --------- |
| Method         | GET       |
| Endpoint       | /users/me |
| Authentication | Yes       |

---

# Property APIs

## Create Property

| Field          | Value       |
| -------------- | ----------- |
| Method         | POST        |
| Endpoint       | /properties |
| Authentication | Seller      |

---

## Update Property

| Field          | Value            |
| -------------- | ---------------- |
| Method         | PUT              |
| Endpoint       | /properties/{id} |
| Authentication | Seller           |

---

## Delete Property

| Field          | Value            |
| -------------- | ---------------- |
| Method         | DELETE           |
| Endpoint       | /properties/{id} |
| Authentication | Seller           |

---

## Get Property By ID

| Field          | Value            |
| -------------- | ---------------- |
| Method         | GET              |
| Endpoint       | /properties/{id} |
| Authentication | No               |

---

## Search Properties

| Field    | Value       |
| -------- | ----------- |
| Method   | GET         |
| Endpoint | /properties |

Query Parameters

* city
* minPrice
* maxPrice
* bedrooms
* propertyType
* page
* size
* sort

Example

```
GET /api/v1/properties?city=Bangalore&bedrooms=2&minPrice=4000000&page=0&size=10
```

---

# Favorite APIs

## Save Property

POST

```
/favorites/{propertyId}
```

---

## Remove Favorite

DELETE

```
/favorites/{propertyId}
```

---

## List Favorites

GET

```
/favorites
```

---

# Enquiry APIs

## Send Enquiry

POST

```
/enquiries
```

Request

```json
{
  "propertyId": 15,
  "message": "Is this property still available?"
}
```

---

## Seller Enquiries

GET

```
/seller/enquiries
```

---

# Price Prediction APIs

## Predict Price

| Field    | Value        |
| -------- | ------------ |
| Method   | POST         |
| Endpoint | /predictions |

Request

```json
{
  "city": "Bangalore",
  "areaSqft": 1200,
  "bedrooms": 2,
  "bathrooms": 2,
  "parking": true,
  "propertyAge": 5
}
```

Response

```json
{
  "predictedPrice": 8450000,
  "pricePerSqft": 7041.67
}
```

---

# Admin APIs

## Pending Properties

GET

```
/admin/properties/pending
```

---

## Approve Property

PUT

```
/admin/properties/{id}/approve
```

---

## Reject Property

PUT

```
/admin/properties/{id}/reject
```

---

## Dashboard Statistics

GET

```
/admin/dashboard
```

---

# HTTP Status Codes

| Status | Meaning               |
| ------ | --------------------- |
| 200    | Success               |
| 201    | Created               |
| 204    | No Content            |
| 400    | Bad Request           |
| 401    | Unauthorized          |
| 403    | Forbidden             |
| 404    | Not Found             |
| 409    | Conflict              |
| 500    | Internal Server Error |

---

# API Design Principles

* Use nouns instead of verbs in URLs.
* Use HTTP methods appropriately.
* Return consistent JSON responses.
* Validate all incoming requests.
* Use pagination for list endpoints.
* Secure protected endpoints using JWT.
* Version APIs using `/api/v1`.
