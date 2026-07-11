# Domain Model

## Purpose

The domain model represents the core business entities and their relationships. It provides a business-oriented view of the system without focusing on database implementation or programming language details.

---

# Core Domain Entities

## User

Represents a person using the platform.

Roles:

* Buyer
* Seller
* Admin

Responsibilities:

* Authenticate
* Manage profile
* Perform actions based on role

---

## Property

Represents a real estate listing.

Responsibilities:

* Store property information
* Maintain listing status
* Associate with a seller
* Receive enquiries
* Be searchable

---

## PropertyImage

Represents an image belonging to a property.

Responsibilities:

* Store image URL
* Define display order

---

## Favorite

Represents a property saved by a buyer.

Responsibilities:

* Link buyer and property
* Prevent duplicate favorites

---

## Enquiry

Represents a buyer's message about a property.

Responsibilities:

* Store enquiry message
* Track enquiry status

---

# Relationships

User (Seller)
│
│ 1
│
│
▼
Property
│
│ 1
│
▼
PropertyImage

User (Buyer)
│
├────────► Favorite ◄──────── Property
│
│
└────────► Enquiry ◄──────── Property

---

# Business Rules

* A Seller can own many properties.
* A Property belongs to exactly one Seller.
* A Buyer can save many properties.
* A Buyer cannot save the same property twice.
* A Buyer can create multiple enquiries.
* A Property can receive multiple enquiries.
* Only approved properties are visible to buyers.

---

# Future Domain Entities

The following entities may be introduced in later versions:

* Review
* Notification
* Appointment
* LoanApplication
* ChatMessage
* Subscription
* PricePredictionHistory
