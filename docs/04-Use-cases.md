# Use Cases

## UC-001: User Registration

### Goal

Allow a new user to create an account.

### Primary Actor

Guest User

### Preconditions

* User is not logged in.

### Main Flow

1. User opens the registration page.
2. User enters name, email, password, and role.
3. User clicks **Register**.
4. System validates the input.
5. System checks if the email already exists.
6. System creates the account.
7. System displays a success message.

### Alternate Flow

* Email already exists.
* Invalid input.
* Weak password.

### Postconditions

* User account is created.

---

## UC-002: User Login

### Goal

Allow a registered user to log in.

### Primary Actor

Buyer, Seller, Admin

### Preconditions

* User has a registered account.

### Main Flow

1. User enters email and password.
2. User clicks **Login**.
3. System validates the credentials.
4. System generates a JWT token.
5. User is redirected to the dashboard.

### Alternate Flow

* Invalid email.
* Incorrect password.

### Postconditions

* User is authenticated.

---

## UC-003: Add Property

### Goal

Allow a seller to create a new property listing.

### Primary Actor

Seller

### Preconditions

* Seller is logged in.

### Main Flow

1. Seller opens the Add Property page.
2. Seller enters property details.
3. Seller uploads images.
4. Seller clicks **Submit**.
5. System validates the information.
6. System saves the property with a status of **Pending Approval**.
7. System displays a success message.

### Alternate Flow

* Missing required fields.
* Invalid image format.
* Upload failure.

### Postconditions

* Property is stored and awaits admin approval.

---

## UC-004: Search Properties

### Goal

Allow a buyer to search for properties.

### Primary Actor

Buyer

### Preconditions

* Properties exist in the system.

### Main Flow

1. Buyer enters search criteria.
2. Buyer applies filters.
3. Buyer clicks **Search**.
4. System retrieves matching properties.
5. System displays the results.

### Alternate Flow

* No matching properties found.

### Postconditions

* Matching properties are displayed.

---

## UC-005: View Property Details

### Goal

Allow a buyer to view complete information about a property.

### Primary Actor

Buyer

### Preconditions

* Buyer selects a property from the search results.

### Main Flow

1. Buyer clicks a property.
2. System displays property details, images, seller information, and location.

### Alternate Flow

* Property has been removed.
* Property is no longer available.

### Postconditions

* Buyer views the property details.

---

## UC-006: Predict House Price

### Goal

Estimate the market value of a property.

### Primary Actor

Buyer or Seller

### Preconditions

* User is on the Price Prediction page.

### Main Flow

1. User enters location, area, bedrooms, bathrooms, parking, and property age.
2. User clicks **Predict Price**.
3. Backend sends the request to the machine learning service.
4. ML model predicts the price.
5. System displays the estimated market value and price per square foot.

### Alternate Flow

* Invalid input values.
* Prediction service unavailable.

### Postconditions

* Predicted price is displayed.

---

## UC-007: Approve Property

### Goal

Allow an administrator to approve property listings.

### Primary Actor

Administrator

### Preconditions

* Property status is **Pending Approval**.

### Main Flow

1. Admin logs in.
2. Admin opens the Pending Properties page.
3. Admin reviews the property.
4. Admin clicks **Approve**.
5. System updates the property status to **Approved**.
6. Property becomes visible to buyers.

### Alternate Flow

* Admin rejects the listing.

### Postconditions

* Property status is updated.
