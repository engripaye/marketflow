# Architecture

The application follows a **modular monolith architecture** for Month 1. Each domain is independently structured within a single deployable API and shares a transactional PostgreSQL database.

This approach keeps deployment and operational complexity low while establishing clear domain boundaries that can be extracted into independent services as the system evolves.

## Modules

* **`identity`** — User registration, password hashing, JWT issuance, and access control.
* **`business`** — Business ownership, tenant isolation, and membership management.
* **`catalog`** — Product management, SKU rules, and stock policies.
* **`inventory`** — Immutable inventory adjustment ledger and stock balance management.
* **`customers`** — Business-scoped customer directory and management.

All business-scoped endpoints derive the tenant context from the authenticated user's active membership. Clients cannot provide a business ID to access or modify another tenant's data.

## Data Integrity & Security

* **Business-scoped SKU uniqueness** — Each SKU must be unique within its business.
* **Non-negative inventory** — Stock balances cannot fall below zero.
* **Immutable inventory ledger** — Every inventory change creates an immutable `InventoryAdjustment` record for traceability and auditability.
* **Optimistic locking** — Concurrent updates are protected against silent stock overwrites.
* **Secure password storage** — Passwords are stored exclusively as BCrypt hashes; raw passwords are never persisted or returned by the API.

## Future Offline Contract

Month 3 will introduce a **client event outbox** and a `/sync/events` endpoint to support offline synchronization.

The existing stable UUID-based public identifiers and immutable inventory adjustment ledger are intentionally designed to provide reliable synchronization identities and audit evidence as the platform evolves.
