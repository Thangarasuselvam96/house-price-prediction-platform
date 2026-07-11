# System Architecture

## Overview

The House Price Prediction Platform is a full-stack web application that enables users to search, list, and manage properties while providing AI-powered house price predictions.

The application follows a layered architecture to ensure maintainability, scalability, and separation of concerns.

---

# Architecture Style

The application uses a layered architecture consisting of:

* Angular Frontend
* Spring Boot REST API
* Machine Learning Service
* PostgreSQL Database
* Cloud Storage (for property images)

---

# High-Level Architecture

```
+------------------------+
|     Angular Frontend   |
+-----------+------------+
            |
            | REST API (HTTP/HTTPS)
            |
+-----------v------------+
| Spring Boot Backend    |
|                        |
| Controllers            |
| Services               |
| Repositories           |
| Security (JWT)         |
+-----------+------------+
            |
     +------+------+
     |             |
     |             |
+----v----+   +----v----------------+
|PostgreSQL|  | ML Prediction Service|
| Database |  | (Python + Scikit)    |
+-----------+  +----------------------+
            |
            |
     Cloud Image Storage
```

---

# Components

## Angular Frontend

Responsibilities:

* User interface
* Authentication
* Property search
* Property listing forms
* Dashboard
* Price prediction form
* API communication

---

## Spring Boot Backend

Responsibilities:

* Business logic
* Authentication
* Authorization
* Property management
* User management
* Search functionality
* Communication with ML service

---

## Machine Learning Service

Responsibilities:

* Load trained model
* Receive prediction requests
* Predict house prices
* Return predicted values

---

## PostgreSQL

Stores:

* Users
* Properties
* Favorites
* Enquiries
* Property Images
* Prediction History (optional)

---

# Layered Architecture

Presentation Layer

* Angular UI

↓

API Layer

* REST Controllers

↓

Business Layer

* Services

↓

Persistence Layer

* Repositories

↓

Database

* PostgreSQL

---

# Authentication Flow

1. User logs in.
2. Backend validates credentials.
3. JWT token is generated.
4. Frontend stores the token securely.
5. Every secured API request includes the JWT token.
6. Backend validates the token before processing the request.

---

# Property Listing Flow

1. Seller logs in.
2. Seller submits property details.
3. Backend validates input.
4. Property is saved with a status of **Pending Approval**.
5. Admin reviews the listing.
6. Admin approves the property.
7. Property becomes searchable by buyers.

---

# House Price Prediction Flow

1. User enters property details.
2. Angular sends the request to Spring Boot.
3. Spring Boot forwards the request to the ML service.
4. ML service predicts the price.
5. ML service returns the prediction.
6. Spring Boot returns the response to Angular.
7. Angular displays the estimated property price.

---

# Security

* JWT Authentication
* Role-Based Authorization
* Password Encryption
* Input Validation
* Secure REST APIs

---

# Scalability Considerations

The architecture allows future enhancements such as:

* Redis caching
* Elasticsearch for advanced search
* Notification service
* Payment gateway
* Google Maps integration
* Recommendation engine
* Microservices
