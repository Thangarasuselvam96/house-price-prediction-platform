# Software Requirements Specification (SRS)

## Project Name

House Price Prediction Platform

---

# 1. Functional Requirements

Functional requirements describe what the system must do.

## 1.1 User Management

### Buyer

* Register an account
* Login and logout
* Update profile
* Change password
* View profile

### Seller

* Register an account
* Login and logout
* Update profile
* Add, edit, and delete properties
* View enquiries for their properties

### Admin

* Login
* Manage users
* Approve or reject property listings
* Remove inappropriate listings
* View platform statistics

---

## 1.2 Authentication

The system shall:

* Authenticate users using email and password.
* Encrypt passwords before storing them.
* Use JWT for secure API access.
* Restrict access based on user roles (Buyer, Seller, Admin).

---

## 1.3 Property Management

A seller shall be able to:

* Add a new property.
* Edit property details.
* Delete a property.
* Upload multiple property images.
* Mark a property as available or sold.

Each property shall contain:

* Title
* Description
* Property Type
* Price
* Area (sq ft)
* Number of Bedrooms
* Number of Bathrooms
* Parking Availability
* Property Age
* Address
* City
* State
* Pincode
* Latitude
* Longitude
* Images

---

## 1.4 Property Search

A buyer shall be able to search properties using:

* City
* Locality
* Budget
* Property Type
* Number of Bedrooms
* Area
* Parking
* Furnishing (optional)

---

## 1.5 Property Details

The system shall display:

* Property images
* Property description
* Price
* Location
* Amenities
* Seller information
* Date listed

---

## 1.6 Favorites

A buyer shall be able to:

* Save a property.
* Remove a saved property.
* View all saved properties.

---

## 1.7 Contact Seller

A buyer shall be able to:

* Send an enquiry.
* View enquiry status.

---

## 1.8 House Price Prediction

A user shall be able to enter:

* Location
* Area
* Bedrooms
* Bathrooms
* Parking
* Property Age

The system shall return:

* Predicted property price.
* Estimated price per square foot.

---

## 1.9 Dashboard

### Buyer Dashboard

* Saved properties
* Recent searches
* Enquiries

### Seller Dashboard

* Listed properties
* Number of views
* Number of enquiries

### Admin Dashboard

* Total users
* Total properties
* Pending approvals
* Reports

---

# 2. Non-Functional Requirements

## Performance

* API response time should generally be under 2 seconds.
* Search results should load quickly for normal usage.

## Security

* Passwords must be encrypted.
* JWT authentication must protect secured APIs.
* Role-based authorization must be implemented.

## Scalability

* The application should support thousands of users and property listings.

## Availability

* The application should remain available except during planned maintenance.

## Maintainability

* The codebase should follow a layered architecture.
* APIs should follow REST principles.
* Documentation should be maintained.

---

# 3. Business Rules

* Only registered sellers can create property listings.
* Buyers cannot edit or delete properties.
* Admin approval is required before a property becomes publicly visible.
* Every property belongs to exactly one seller.
* A buyer can save multiple properties.
* A seller can own multiple properties.

---

# 4. Assumptions

* Internet connectivity is available.
* Property details entered by sellers are accurate.
* The machine learning model is trained before deployment.

---

# 5. Constraints

* Frontend: Angular
* Backend: Spring Boot
* Database: PostgreSQL
* Machine Learning: Python (Scikit-learn)
* Authentication: JWT
* Deployment: Docker and AWS
