# Machine Learning Design

## Overview

The House Price Prediction Platform uses a supervised machine learning model to estimate the market value of residential properties based on their characteristics.

The trained model is exposed through a prediction service that is consumed by the Spring Boot backend.

---

# Business Goal

Provide users with a realistic estimate of a property's market value to support buying and selling decisions.

The prediction is intended as a guidance tool, not as an official valuation.

---

# Machine Learning Workflow

Dataset
↓

Data Cleaning

↓

Feature Engineering

↓

Train/Test Split

↓

Model Training

↓

Model Evaluation

↓

Model Serialization

↓

Prediction API

↓

Spring Boot Integration

↓

Angular UI

---

# Dataset

Initial Dataset

* Bengaluru House Price Dataset

Future Improvements

* Multiple cities
* Real property listing data
* Periodic data updates

---

# Features (Input Variables)

| Feature      | Type        |
| ------------ | ----------- |
| Location     | Categorical |
| Area (sq ft) | Numerical   |
| Bedrooms     | Numerical   |
| Bathrooms    | Numerical   |
| Parking      | Boolean     |
| Property Age | Numerical   |

---

# Target Variable

Predicted House Price

---

# Data Preprocessing

The preprocessing pipeline includes:

* Remove duplicate records
* Handle missing values
* Remove invalid entries
* Remove outliers
* Encode categorical features
* Scale numerical features (if required)

---

# Candidate Algorithms

The following algorithms may be evaluated:

* Linear Regression
* Decision Tree Regressor
* Random Forest Regressor

The model with the best evaluation metrics will be selected.

---

# Model Evaluation

Metrics to compare models:

* R² Score
* Mean Absolute Error (MAE)
* Mean Squared Error (MSE)
* Root Mean Squared Error (RMSE)

---

# Model Storage

After training, the selected model will be serialized.

Example:

model.pkl

Additional metadata (such as feature names) may also be stored.

---

# Prediction Flow

1. User enters property details.
2. Angular sends the request to Spring Boot.
3. Spring Boot validates the request.
4. Spring Boot calls the ML Prediction Service.
5. The ML service loads the trained model.
6. Input data is transformed into the expected feature format.
7. The model predicts the price.
8. The prediction is returned to Spring Boot.
9. Spring Boot sends the response to Angular.
10. Angular displays the estimated price.

---

# Retraining Strategy

The model can be retrained when:

* New property data becomes available.
* Model accuracy decreases.
* Additional cities are introduced.

Retraining steps:

1. Update the dataset.
2. Clean and preprocess the data.
3. Train candidate models.
4. Evaluate performance.
5. Replace the production model if the new model performs better.

---

# Error Handling

The prediction service should:

* Validate all inputs.
* Reject invalid values.
* Handle unavailable models gracefully.
* Return meaningful error messages.

---

# Future Enhancements

Possible future improvements include:

* Support for multiple cities
* Additional property features (balcony, furnishing, floor, amenities)
* Confidence score for predictions
* Explainable AI (feature importance)
* Personalized price recommendations
* Automatic retraining pipeline
