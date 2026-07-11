# UI Wireframes

## Overview

This document defines the screens of the House Price Prediction Platform, their purpose, key components, and the APIs they consume.

The wireframes focus on functionality rather than visual design.

---

# Public Pages

## Home Page

### Purpose

Provide an overview of the platform and allow users to search properties.

### Components

* Navigation Bar
* Hero Section
* Property Search Bar
* Featured Properties
* Price Prediction CTA
* Footer

### APIs

* GET /properties

---

## Login Page

### Purpose

Authenticate users.

### Components

* Email
* Password
* Login Button
* Register Link
* Forgot Password Link

### APIs

* POST /auth/login

---

## Register Page

### Purpose

Allow new users to create an account.

### Components

* First Name
* Last Name
* Email
* Password
* Role (Buyer/Seller)
* Register Button

### APIs

* POST /auth/register

---

## Property Search Page

### Purpose

Allow buyers to search and filter properties.

### Components

* Search Filters
* Property Cards
* Pagination
* Sort Dropdown

### APIs

* GET /properties

---

## Property Details Page

### Purpose

Display complete property information.

### Components

* Image Gallery
* Property Details
* Seller Information
* Contact Seller Button
* Save to Favorites Button
* Similar Properties

### APIs

* GET /properties/{id}

---

## Price Prediction Page

### Purpose

Predict the market value of a property.

### Components

* Prediction Form
* Predict Button
* Prediction Result Card

### APIs

* POST /predictions

---

# Buyer Pages

## Buyer Dashboard

### Components

* Dashboard Summary
* Saved Properties
* Recent Enquiries
* Profile Shortcut

### APIs

* GET /favorites
* GET /users/me

---

## Favorites Page

### Components

* Saved Property Cards
* Remove Favorite Button

### APIs

* GET /favorites
* DELETE /favorites/{propertyId}

---

# Seller Pages

## Seller Dashboard

### Components

* Statistics Cards
* My Properties
* Recent Enquiries
* Quick Actions

### APIs

* GET /seller/dashboard

---

## Add Property Page

### Components

* Property Form
* Image Upload
* Submit Button

### APIs

* POST /properties

---

## Edit Property Page

### Components

* Property Form
* Existing Images
* Update Button

### APIs

* GET /properties/{id}
* PUT /properties/{id}

---

## My Properties Page

### Components

* Property Table
* Status Badge
* Edit Button
* Delete Button

### APIs

* GET /seller/properties

---

# Admin Pages

## Admin Dashboard

### Components

* Total Users
* Total Properties
* Pending Approvals
* Reports

### APIs

* GET /admin/dashboard

---

## Pending Properties

### Components

* Property Table
* Approve Button
* Reject Button

### APIs

* GET /admin/properties/pending
* PUT /admin/properties/{id}/approve
* PUT /admin/properties/{id}/reject

---

# Shared Components

The following UI components should be reusable:

* Navbar
* Footer
* Property Card
* Property Image Gallery
* Search Filters
* Pagination
* Loading Spinner
* Confirmation Dialog
* Toast Notification
* Error Message Component

---

# Angular Routing

| Route                       | Screen             |
| --------------------------- | ------------------ |
| /                           | Home               |
| /login                      | Login              |
| /register                   | Register           |
| /properties                 | Property Search    |
| /properties/:id             | Property Details   |
| /predict                    | Price Prediction   |
| /buyer/dashboard            | Buyer Dashboard    |
| /buyer/favorites            | Favorites          |
| /seller/dashboard           | Seller Dashboard   |
| /seller/properties          | My Properties      |
| /seller/properties/new      | Add Property       |
| /seller/properties/:id/edit | Edit Property      |
| /admin/dashboard            | Admin Dashboard    |
| /admin/properties/pending   | Pending Properties |

---

# Development Order

Recommended implementation sequence:

1. Authentication
2. Home Page
3. Property Search
4. Property Details
5. Seller Property Management
6. Buyer Favorites
7. Dashboards
8. Admin Features
9. Price Prediction
10. UI Enhancements
