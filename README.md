# MarketFlow

### Offline-First Commerce & Inventory Platform for Small Businesses

MarketFlow is an open-source commerce management platform designed for small and medium-sized businesses operating in environments where internet connectivity, infrastructure, and digital business tools may be unreliable or expensive.

The platform is being developed with an **offline-first architecture**, beginning with core business management capabilities and progressively expanding into inventory synchronization, customer credit management, payments, logistics coordination, and intelligent business analytics.

> **Project Status:** 🚧 Month 1 — Foundation & Core Business Management

---

## Overview

Many small businesses still rely on paper records, spreadsheets, messaging applications, and disconnected tools to manage everyday operations.

MarketFlow aims to provide a single platform for managing:

* Products and inventory
* Sales and transactions
* Customers
* Customer credit/debt
* Business records
* Orders
* Local deliveries
* Business analytics

The long-term goal is to provide a reliable system that continues to work even when internet connectivity is unavailable and synchronizes data when connectivity is restored.

---

## Current Development Focus

The first month focuses on establishing the application's core architecture and business foundation.

### Month 1 Objectives

* [x] Repository and project architecture
* [ ] Spring Boot backend initialization
* [ ] React + TypeScript frontend initialization
* [ ] PostgreSQL database setup
* [ ] User authentication
* [ ] JWT-based authorization
* [ ] Business/workspace management
* [ ] Product management
* [ ] Inventory management
* [ ] Customer management
* [ ] REST API development
* [ ] API validation and error handling
* [ ] Pagination and filtering
* [ ] OpenAPI documentation
* [ ] Unit and integration testing
* [ ] CI pipeline
* [ ] Initial deployment

---

# Planned Architecture

MarketFlow will begin as a **modular monolith** rather than immediately adopting multiple microservices.

This allows the project to establish clear domain boundaries while keeping development and deployment manageable.

```text
                         ┌─────────────────────┐
                         │     React PWA       │
                         │  TypeScript + Vite  │
                         └──────────┬──────────┘
                                    │
                              REST / WebSocket
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    Spring Boot      │
                         │       API           │
                         └──────────┬──────────┘
                                    │
                 ┌──────────────────┼──────────────────┐
                 │                  │                  │
                 ▼                  ▼                  ▼
          ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
          │ PostgreSQL  │    │    Redis    │    │   Storage   │
          │             │    │             │    │             │
          └─────────────┘    └─────────────┘    └─────────────┘
```

Future services will be introduced only when they provide a clear architectural or operational benefit.

---

# Technology Stack

## Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* PostgreSQL
* Redis
* Maven
* OpenAPI / Swagger

## Frontend

* React
* TypeScript
* Vite
* React Router
* Progressive Web App capabilities

## Testing

* JUnit
* Mockito
* Spring Boot Test
* Testcontainers
* Vitest
* Playwright

## DevOps

* Docker
* GitHub Actions
* GitHub
* Environment-based configuration

## Planned Technologies

The following technologies are part of the longer-term roadmap and are **not required for the Month 1 implementation**:

* Python
* FastAPI
* Kafka
* WebSockets
* IndexedDB
* Advanced offline synchronization
* Machine learning
* OpenTelemetry
* Prometheus
* Grafana
* Cloud object storage

---

# Core Domain

The application is organized around several business domains.

```text
MarketFlow
│
├── Identity
│   ├── Users
│   ├── Authentication
│   └── Authorization
│
├── Business
│   ├── Businesses
│   └── Staff
│
├── Catalog
│   ├── Products
│   └── Categories
│
├── Inventory
│   ├── Stock
│   ├── Adjustments
│   └── Stock History
│
├── Customers
│   ├── Customer Profiles
│   └── Customer Accounts
│
├── Sales
│   ├── Transactions
│   ├── Payments
│   └── Receipts
│
├── Credit
│   ├── Customer Debts
│   └── Debt Payments
│
├── Orders
│   └── Order Management
│
└── Logistics
    ├── Delivery Requests
    ├── Riders
    └── Delivery Tracking
```

Only the initial domains are being implemented during Month 1.

---

# Month 1 Architecture

The first release focuses on the following flow:

```text
User
  │
  ▼
React Application
  │
  │ HTTPS / REST
  ▼
Spring Boot API
  │
  ├── Authentication
  ├── Business Management
  ├── Product Management
  ├── Inventory
  └── Customer Management
  │
  ▼
PostgreSQL
```

---

# Authentication

MarketFlow will use token-based authentication.

Initial authentication flow:

```text
User
  │
  ▼
POST /api/v1/auth/register
  │
  ▼
Account Created
  │
  ▼
POST /api/v1/auth/login
  │
  ▼
JWT Access Token
  │
  ▼
Authenticated API Requests
```

Security features planned for the first phase include:

* Password hashing
* JWT authentication
* Role-based authorization
* Request validation
* Protected API endpoints
* Authentication error handling
* Secure configuration management

---

# Product Management

Businesses will be able to create and manage products.

Example product information:

```json
{
  "name": "USB-C Charger",
  "sku": "CHG-001",
  "category": "Electronics",
  "sellingPrice": 8500,
  "costPrice": 5000,
  "stockQuantity": 25
}
```

Planned operations:

```text
POST   /api/v1/products
GET    /api/v1/products
GET    /api/v1/products/{id}
PUT    /api/v1/products/{id}
DELETE /api/v1/products/{id}
```

Additional functionality will include:

* Pagination
* Search
* Filtering
* Product categories
* Stock status
* Validation
* Audit history

---

# Inventory Management

Inventory management is one of the core components of MarketFlow.

The initial inventory system will support:

* Stock quantities
* Stock adjustments
* Stock history
* Low-stock identification
* Inventory validation
* Product availability

Example:

```text
Product
   │
   ├── Current Stock
   ├── Reorder Level
   └── Stock History
          │
          ├── Purchase
          ├── Sale
          ├── Adjustment
          └── Return
```

The inventory system will eventually become the foundation for the offline synchronization engine.

---

# Customer Management

Businesses will be able to maintain customer records.

Initial customer information includes:

* Name
* Phone number
* Email
* Address
* Customer status
* Transaction history

Future versions will introduce customer credit and debt tracking.

---

# API Design

MarketFlow APIs will follow a versioned REST architecture.

Example:

```text
/api/v1/auth
/api/v1/users
/api/v1/businesses
/api/v1/products
/api/v1/inventory
/api/v1/customers
```

The API will follow consistent conventions for:

* HTTP status codes
* Request validation
* Error responses
* Pagination
* Filtering
* Resource naming
* API versioning

---

# Example Error Response

MarketFlow will use structured API errors.

```json
{
  "timestamp": "2026-09-18T12:00:00Z",
  "status": 400,
  "error": "VALIDATION_ERROR",
  "message": "Product name is required",
  "path": "/api/v1/products"
}
```

---

# Database

PostgreSQL is the primary relational database.

Initial database domains:

```text
users
businesses
roles
products
categories
inventory
inventory_movements
customers
```

Database migrations will be version-controlled so that development, testing, staging, and production environments can maintain consistent schemas.

---

# Testing Strategy

Testing is being treated as part of feature development rather than something added at the end of the project.

The backend will contain:

### Unit Tests

Testing individual business components.

```text
Service
   ↓
Unit Test
```

### Integration Tests

Testing application components together.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### End-to-End Tests

The frontend will eventually contain browser-level tests covering critical business workflows.

---

# Development Workflow

MarketFlow follows a feature-branch workflow.

```text
main
 │
 ├── feature/authentication
 ├── feature/product-management
 ├── feature/inventory
 ├── feature/customer-management
 └── fix/...
```

Each feature should generally follow:

```text
Issue
  ↓
Feature Branch
  ↓
Implementation
  ↓
Tests
  ↓
Pull Request
  ↓
Code Review
  ↓
CI
  ↓
Merge
```

The project intentionally uses pull requests for incremental development, code review, testing, documentation, and architectural changes.

---

# Contribution Guidelines

Contributions are welcome.

Before submitting a pull request:

1. Create or identify an issue describing the change.
2. Create a dedicated feature or fix branch.
3. Keep changes focused on one problem.
4. Add or update tests where appropriate.
5. Update documentation when behavior changes.
6. Ensure the project builds successfully.
7. Ensure the test suite passes.
8. Open a pull request against `main`.

Example:

```bash
git checkout -b feature/product-pagination
```

```bash
git add .
git commit -m "feat(products): add pagination"
```

```bash
git push origin feature/product-pagination
```

---

# Project Roadmap

## Phase 1 — Foundation

**Month 1**

* Authentication
* Business accounts
* Products
* Inventory
* Customers
* REST API
* PostgreSQL
* Testing
* CI/CD

## Phase 2 — Commerce

**Month 2**

* Sales
* Payments
* Receipts
* Customer credit
* Debt tracking
* Transaction history
* Business reports

## Phase 3 — Offline-First

**Month 3**

* Local data storage
* IndexedDB
* Offline application mode
* Synchronization queue
* Retry mechanisms
* Conflict resolution
* Idempotent synchronization
* Network status indicators

## Phase 4 — Logistics

**Month 4**

* Orders
* Delivery requests
* Rider management
* Delivery assignment
* Pickup manifests
* Delivery status
* Real-time updates

## Phase 5 — Intelligence

**Month 5**

* Sales analytics
* Inventory analytics
* Demand forecasting
* Inventory anomaly detection
* Business insights

## Phase 6 — Production

**Month 6**

* Security hardening
* Performance optimization
* Observability
* Production deployment
* Documentation
* Public API documentation
* Contributor onboarding
* Release automation

---

# Long-Term Vision

MarketFlow aims to evolve into a reliable digital infrastructure layer for small businesses operating in markets where connectivity and access to sophisticated business software cannot always be assumed.

The long-term platform will combine:

```text
Commerce
   +
Inventory
   +
Payments
   +
Credit
   +
Offline Synchronization
   +
Logistics
   +
Analytics
   +
Business Intelligence
```

The architecture is intentionally being developed incrementally so that each capability can be independently tested, deployed, and improved.

---

# Engineering Principles

MarketFlow follows several core engineering principles:

### 1. Offline First

Connectivity should improve the experience, not determine whether the application works.

### 2. Modular Architecture

Business domains should remain independently understandable and maintainable.

### 3. API First

Backend functionality should be exposed through well-defined APIs.

### 4. Security by Default

Authentication, authorization, validation, and auditing should be considered during feature development.

### 5. Testable Systems

Business logic should be designed so that it can be tested independently.

### 6. Incremental Complexity

Technologies such as Kafka, distributed services, and advanced machine learning should only be introduced when the product requires them.

### 7. Open Development

Issues, pull requests, architectural decisions, and documentation should provide a transparent development history.

---

# Repository Structure

```text 1
marketflow/
│
├── backend/
├── frontend/
├── ai-service/
├── docs/
├── docker/
├── .github/
│   ├── workflows/
│   └── ISSUE_TEMPLATE/
│
├── CONTRIBUTING.md
├── SECURITY.md
├── ROADMAP.md
├── ARCHITECTURE.md
├── LICENSE
└── README.md
```

The repository structure will evolve as new domains and services are introduced.

---

# Development Status

| Component               | Status         |
| ----------------------- | -------------- |
| Repository setup        | 🟢 In progress |
| Backend foundation      | 🟡 Planned     |
| Frontend foundation     | 🟡 Planned     |
| Authentication          | 🟡 Planned     |
| Product management      | 🟡 Planned     |
| Inventory               | 🟡 Planned     |
| Customer management     | 🟡 Planned     |
| Sales                   | ⚪ Planned      |
| Offline synchronization | ⚪ Planned      |
| Logistics               | ⚪ Planned      |
| AI/Analytics            | ⚪ Planned      |
| CI/CD                   | 🟡 Planned     |
| Production deployment   | ⚪ Planned      |

---

# Project Goals

The project is being developed with four major goals:

1. Build a practical business management platform.
2. Explore reliable offline-first application architecture.
3. Demonstrate production-oriented backend and distributed-systems engineering.
4. Build an open-source project through transparent, incremental development.

---

# License

This project will be released under an open-source license. License details will be added before the first public release.

---

## Development

MarketFlow is currently under active development.

**Current milestone:** Month 1 — Foundation & Core Business Management

More documentation will be added as the architecture and individual modules mature.
