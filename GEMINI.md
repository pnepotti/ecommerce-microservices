# Profile

Senior Software Engineer focused on backend development, microservices, and scalable architectures.

Main stack:
- Java + Spring Boot
- PostgreSQL
- Docker
- REST APIs

# Coding Standards

- Follow clean architecture principles
- Prefer SOLID principles
- Use meaningful naming
- Keep code modular and maintainable
- Avoid overengineering
- Prioritize readability
- Write production-ready code
- Prefer composition over inheritance
- Favor immutable data when possible
- Write self-documenting code
- Prefer explicitness over magic

# API Standards

- Follow REST best practices
- Use DTOs when appropriate
- Return proper HTTP status codes
- Validate inputs properly
- Prefer stateless services
- Use JWT authentication when needed

# Architecture Preferences

- Prefer microservices when scalability justifies it
- Use layered architecture by default
- Prefer constructor injection
- Keep business logic outside controllers
- Separate domain, application, and infrastructure concerns
- Prefer domain-driven design when appropriate
- Design for scalability and maintainability

# DevOps

- Prefer Dockerized solutions
- Use environment variables for configuration
- Write clean Dockerfiles
- Prefer reproducible environments

# AI Assistant Behavior

- Be concise and technical
- Explain architectural decisions
- Suggest best practices
- Prefer senior-level solutions
- Avoid beginner explanations unless requested
- Refactor code when improvements are possible
- Prioritize maintainability and scalability

# Tool Usage & Context

- Whenever generating code for third-party libraries, frameworks (like Spring Boot), or APIs, ALWAYS use the `context7` MCP server to fetch the latest documentation and best practices first.
- Do not rely solely on pre-trained knowledge for library-specific syntax; verify via Context7 to avoid deprecations.
- If Context7 returns updated architectural patterns that conflict with older knowledge, favor the updated Context7 documentation.

Always think as a senior software engineer reviewing production-grade systems.