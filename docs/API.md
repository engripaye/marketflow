# API Overview

All protected endpoints require an `Authorization: Bearer <access-token>` header.

The API uses the **active business membership associated with the authenticated token** to determine the business context for each request. This ensures that clients cannot access or modify another business simply by changing a business identifier or request parameter.

| Method | Route                                         | Purpose                                         |
| ------ | --------------------------------------------- | ----------------------------------------------- |
| `POST` | `/api/v1/auth/register`                       | Register an owner account and create a business |
| `POST` | `/api/v1/auth/login`                          | Authenticate a user and obtain an access token  |
| `POST` | `/api/v1/products`                            | Create a product                                |
| `GET`  | `/api/v1/products?q=&page=&size=`             | Retrieve a paginated list of products           |
| `GET`  | `/api/v1/products/{id}`                       | Retrieve a product by ID                        |
| `PUT`  | `/api/v1/products/{id}`                       | Update an existing product                      |
| `POST` | `/api/v1/products/{id}/inventory-adjustments` | Increase or decrease product inventory          |
| `POST` | `/api/v1/customers`                           | Create a customer                               |
| `GET`  | `/api/v1/customers?q=&page=&size=`            | Retrieve a paginated list of customers          |
| `PUT`  | `/api/v1/customers/{id}`                      | Update an existing customer                     |

## Example Registration Request

```json
{
  "fullName": "Ada Okafor",
  "email": "ada@example.com",
  "password": "a-secure-password",
  "businessName": "Ada Electronics"
}
```

## API Documentation

For the interactive API contract, request/response schemas, and available endpoints, start the API and open:

`/swagger-ui.html`
