# Contributing

Contributions are welcome and appreciated. To maintain a high-quality, maintainable codebase, please follow the contribution guidelines below.

## Development Workflow

* Open an issue before starting significant changes to align on scope and approach.
* Use a focused feature or fix branch for each change.
* Add or update tests to cover new or modified functionality.
* Keep each pull request focused on one coherent change.
* Ensure all local checks pass before submitting a pull request.

## Local Verification

Run the following checks before opening a pull request:

```bash
cd backend && mvn test

cd frontend && npm run lint && npm run build
```

## Commit Convention

Use **Conventional Commits** to keep the project history clear, consistent, and easy to understand.

Example:

```text
feat(inventory): add low-stock report
```

Clear commits, focused pull requests, and automated verification help keep the project reliable and maintainable as it evolves.
