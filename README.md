# rewards-calculator
Overview

This Spring Boot application calculates customer reward points based on transaction amounts over a configurable number of months.

The application provides REST APIs to retrieve reward points earned by individual customers and all customers.

Reward Calculation Rules

A customer receives:

2 points for every dollar spent over $100 in a transaction.
1 point for every dollar spent between $50 and $100 in a transaction.
No points for purchases of $50 or less.

Technology Stack
Java 17
Spring Boot 3.x
Spring Data JPA
H2 Database
Lombok
Maven
JUnit 5
Mockito


API Endpoints
Get Rewards for a Customer
GET /api/rewards/{customerId}

Example:

GET /api/rewards/1

Response:
{
    "customerId": 1,
    "customerName": "kiran",
    "monthlyRewards": [
        {
            "month": "2026-04",
            "points": 90
        },
        {
            "month": "2026-05",
            "points": 25
        },
        {
            "month": "2026-06",
            "points": 250
        }
  ],
    "totalRewards": 365
}


Get Rewards for All Customers
GET /api/rewards

Response:

[
    {
        "customerId": 1,
        "customerName": "kiran",
        "monthlyRewards": [
            {
                "month": "2026-04",
                "points": 90
            },
            {
                "month": "2026-05",
                "points": 25
            },
            {
                "month": "2026-06",
                "points": 250
            }
        ],
        "totalRewards": 365
    },
    {
        "customerId": 2,
        "customerName": "ratna",
        "monthlyRewards": [
            {
                "month": "2026-05",
                "points": 0
            },
            {
                "month": "2026-06",
                "points": 25
            },
            {
                "month": "2026-07",
                "points": 50
            }
        ],
        "totalRewards": 75
    }
]
