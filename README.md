# Backend Service APIs

A modular Spring Boot application built with **Spring Modulith** and the **Java Platform Module System (JPMS)**.

---

## Tech Stack

| Component            | Version / Detail                      |
|----------------------|---------------------------------------|
| Java                 | 25                                    |
| Spring Boot          | 4.0.x                                |
| Spring Modulith      | 2.0.5                                |
| Build Tool           | Maven (wrapper included)             |
| Database             | PostgreSQL                           |
| API Docs             | SpringDoc OpenAPI 3                  |
| Observability        | Micrometer Tracing + OpenTelemetry   |

## Application Modules

The application is organized into the following Spring Modulith modules:

```
com.example.backend
├── cart          — Cart management
├── catalogue     — Product catalogue
├── customer      — Customer management
├── inventory     — Inventory tracking
├── order         — Order processing
├── payment       — Payment processing
└── security      — Authentication & authorization
```

Each domain module follows a consistent internal structure:

```
<module>/
├── api/              — Public API (NamedInterface: "api")
├── config/           — Spring @Configuration classes (internal)
├── domain/
│   ├── dto/          — Data transfer objects (NamedInterface: "dtos")
│   ├── entity/       — JPA entities (internal)
│   └── event/        — Domain events (NamedInterface: "events")
├── repository/       — Spring Data JPA repositories (internal)
└── service/          — Business logic services (internal)
```

---

## Prerequisites

1. **JDK 25** — Install via [SDKMAN](https://sdkman.io/) or your preferred method:
   ```bash
   sdk install java 25-open
   ```

2. **PostgreSQL** — A running PostgreSQL instance with a database created for your profile.

3. **VS Code** (recommended) with the following extensions:
   - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   - [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)

---

## Setup from Scratch

### 1. Clone the Repository

```bash
git clone <repository-url>
cd backend-services
```

### 2. Verify Java Version

```bash
java --version
# Expected: openjdk 25 or later
```

### 3. Build the Project

Use the included Maven wrapper — no separate Maven installation required:

```bash
./mvnw clean compile
```

### 4. Configure the Database

The application uses **profile-based configuration**. Each profile has its own `application-<profile>.yaml` under `src/main/resources/`.

For the `ab` profile, the datasource is configured in `application-ab.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://192.168.64.10/backend_services_ab?sslmode=require&sslfactory=org.postgresql.ssl.NonValidatingFactory
    username: backend_services_ab
    password: backend_services_ab
```

Update these values to match your PostgreSQL instance.

### 5. Run Tests

```bash
# Run all tests
./mvnw test

# Run only modulith architecture verification
./mvnw test -Dtest=ModulithTest#verifyModules

# Generate Spring Modulith documentation (PlantUML + Asciidoc)
./mvnw test -Dtest=ModulithTest#writeDocumentationSnippets
```

Generated documentation is output to `target/spring-modulith-docs/`.

---

## Running the Application

### Option 1: VS Code Launch Configuration (Recommended)

The project includes a pre-configured `.vscode/launch.json`:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Backend Services",
      "request": "launch",
      "cwd": "${workspaceFolder}",
      "mainClass": "backend.services/com.example.backend.BackendService",
      "projectName": "backend-services",
      "args": "",
      "vmArgs": "-Dspring.profiles.active=ab",
      "envFile": "${workspaceFolder}/.env"
    }
  ]
}
```

**To run:**

1. Open the project folder in VS Code.
2. Go to **Run and Debug** panel (`⇧⌘D` / `Ctrl+Shift+D`).
3. Select **"Backend Services"** from the dropdown.
4. Press **▶️ Start Debugging** (`F5`).

**To change the active profile**, edit the `vmArgs` value in `.vscode/launch.json`:

```
"vmArgs": "-Dspring.profiles.active=<your-profile>"
```

**To use environment variables**, create a `.env` file in the project root:

```env
DB_PASSWORD=your_secret_password
```

### Option 2: Maven Command Line

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=ab
```

### Option 3: Run the JAR Directly

```bash
./mvnw clean package -DskipTests
java -Dspring.profiles.active=ab -jar target/backend-services-0.0.1-SNAPSHOT.jar
```

---

## Configuration Profiles

| Profile    | File                        | Description                     |
|------------|-----------------------------|---------------------------------|
| _(default)_ | `application.yaml`         | Base config — port `55105`, production-safe defaults |
| `ab`       | `application-ab.yaml`       | Development profile with debug logging and Swagger API docs |

The base `application.yaml` is always loaded. Profile-specific files override/extend it.

---

## JPMS Module Structure

This project is a fully modularized JPMS application. The Java module is declared in `src/main/java/module-info.java` as `backend.services`.

Key points:
- All Spring dependencies are referenced via `requires` directives.
- Packages containing Spring-managed components are selectively `opens`'d to `spring.core`, `spring.beans`, and `spring.context` for reflection access.
- The `mainClass` in `launch.json` uses the JPMS format: `backend.services/com.example.backend.BackendService`.

---

## Generating Module Documentation

The `ModulithTest` class generates living architecture documentation:

```bash
./mvnw test -Dtest=ModulithTest#writeDocumentationSnippets
```

This produces the following in `target/spring-modulith-docs/`:

| File                      | Description                                      |
|---------------------------|--------------------------------------------------|
| `components.puml`         | C4 overview diagram of all modules               |
| `module-<name>.puml`      | Per-module PlantUML diagram                      |
| `module-<name>.adoc`      | Asciidoc canvas (beans, events, config props)    |
| `all-docs.adoc`           | Aggregating document linking everything together |

**Rendering PlantUML diagrams:**

```bash
# Install PlantUML (macOS)
brew install plantuml

# Render all diagrams as SVG
plantuml -tsvg target/spring-modulith-docs/*.puml
```

---

## API Documentation

When running with the `ab` profile, OpenAPI docs are available at:

- **OpenAPI JSON**: `http://localhost:55105/backend-services/v3/api-docs`

Swagger UI is disabled by default. To enable it, set `springdoc.swagger-ui.enabled: true` in your profile config.
