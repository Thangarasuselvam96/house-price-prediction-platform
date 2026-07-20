Business Requirements

A user should be able to:

Register
Log in
Own properties
Have one or more roles
Be active or inactive

User Fields
| Field       | Required | Description                     |
| ----------- | -------- | ------------------------------- |
| id          | Yes      | Primary key                     |
| firstName   | Yes      | User's first name               |
| lastName    | Yes      | User's last name                |
| email       | Yes      | Login username (must be unique) |
| password    | Yes      | Encrypted password              |
| phoneNumber | No       | Contact number                  |
| role        | Yes      | USER / ADMIN                    |
| status      | Yes      | ACTIVE / INACTIVE               |
| createdAt   | Yes      | Created timestamp               |
| updatedAt   | Yes      | Updated timestamp               |
