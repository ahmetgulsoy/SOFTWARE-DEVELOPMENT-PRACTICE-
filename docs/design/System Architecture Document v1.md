# System Architecture Documentation

## 1. Overview

The Indigenous Craft Platform is a web-based application for discovering, sharing, and managing handmade crafts. It supports craft browsing and searching, user profiles, social interactions, and administrative management.

The system follows a **Modular Monolith** architecture: all functional modules run within one Spring Boot application while maintaining clear responsibility boundaries. The web interface uses **Thymeleaf**, **Bootstrap**, and JavaScript, with **PostgreSQL** as the relational database.

## 2. Architectural Style

### 2.1 Modular Monolith

The application is organized into logical modules but deployed as a single Spring Boot application. Modules share the same runtime and database and are not independently deployed services.

This approach keeps deployment and development manageable while allowing code to be organized by functional responsibility.

### 2.2 Layered Design

Each module uses the following layers where applicable:

| Layer | Responsibility |
|---|---|
| Controller | Handles HTTP requests and returns views or responses. |
| Service | Implements business rules, validations, and workflows. |
| Repository | Performs persistence operations through Spring Data JPA / Hibernate. |

The normal request flow is **Controller → Service → Repository → Database**. When a module needs functionality owned by another module, it should call that module's Service layer rather than access its Repository directly.

### 2.3 Server-Side Rendering

Spring MVC controllers return Thymeleaf templates populated with application data. Bootstrap provides responsive styling, while JavaScript supports interactive features such as autocomplete, infinite scrolling, and map interactions. A separate React frontend is not part of this architecture.

## 3. Deployment Architecture

The proposed deployment uses two Docker containers managed together through Docker Compose.

### Container 1: Spring Boot Application

This container runs the web application, including the five functional modules, shared components, Thymeleaf templates, Spring Security, and Spring Data JPA / Hibernate.

### Container 2: PostgreSQL Database

This container runs PostgreSQL and stores persistent application data. A Docker persistent volume is planned to preserve database files independently of the database container's lifecycle.

The Spring Boot application connects to PostgreSQL over the configured Docker network using JDBC. **JPA/Hibernate runs inside the Spring Boot application container, not inside the PostgreSQL container.**

## 4. Application Modules

The application contains five functional modules and a shared Common component.

| Module | Main responsibilities |
|---|---|
| **User** | User accounts, profiles, authentication-related workflows, and follow relationships |
| **Craft** | Craft creation and management, images, category associations, browsing/search, comments, and timeline-related craft retrieval |
| **Property** | Reusable property definitions, options and units, category-property associations, and craft property values |
| **Moderation** | User reports, metadata/property requests, and moderation workflows |
| **Admin** | Administrative operations, management screens, and basic dashboard functions |

`Common` is a shared component area rather than a separate business module. It contains cross-cutting functionality such as Spring Security and shared error handling, along with the `ImageStorage` and `EmailService` interfaces.

Module responsibilities should remain clear. For example, the Craft module can call the Property Service when it needs property-related validation or processing. It should not access the Property Repository directly.

## 5. Request and Data Flow

A typical page request follows these steps:

1. A guest, registered user, or administrator requests a page through the browser.
2. A Spring MVC Controller receives the request and invokes the relevant Service.
3. The Service applies business rules and calls its Repository when database access is required.
4. Spring Data JPA / Hibernate uses JDBC to interact with PostgreSQL.
5. The result returns through the Service and Controller.
6. Thymeleaf renders the HTML response, which is returned to the browser.

For interactive functionality, browser-side JavaScript may make additional requests to application endpoints.

## 6. External Integrations

The following integrations are planned options until they are implemented and verified.

### 6.1 Image Storage

The `ImageStorage` interface abstracts image storage operations. A provider-specific implementation, such as Cloudinary, can handle communication with the selected image-storage service. The application manages the upload flow and access rules; stored image URLs can then be used to display images in the browser. Direct browser-to-provider uploads should only be included if that approach is deliberately implemented.

### 6.2 Email Delivery

The `EmailService` interface abstracts email-sending operations. An SMTP-based implementation can support account verification and notifications if these features are included in the implementation.

### 6.3 Map Display

The browser uses Leaflet to display an interactive map when a craft has an origin location. Leaflet requests map tiles directly from the configured OpenStreetMap tile service. Spring Boot supplies the craft's location data; map tiles do not need to pass through the backend.

The selected OpenStreetMap tile service must be used in accordance with its attribution and usage policies.

## 7. Security and Access Control

Spring Security provides authentication and authorization mechanisms. Server-side access rules should reflect the platform's three primary access levels:

| Role | Access |
|---|---|
| Guest | Public craft browsing, searching, and permitted public profile viewing |
| Registered User | Guest capabilities plus craft management, social interactions, and account-specific operations |
| Administrator | Authorized administrative and moderation operations |

Access to restricted functionality is enforced on the server side according to the authenticated user's permissions.

## 8. Build and Deployment Workflow

Source code is maintained in GitHub. The proposed workflow may use GitHub Actions to build the application and run automated tests. If container publishing and automated deployment are configured, the workflow can publish an application image to a container registry and deploy the application and database using Docker Compose on a suitable host.

Flyway is planned for version-controlled database schema migrations, if adopted during implementation. Database backups and persistent storage should be configured according to the chosen deployment environment.

The cloud host, container registry, image-storage provider, SMTP provider, and automation steps should be described as implemented only after they have been configured and tested.

## 9. Architectural Decisions and Trade-offs

| Decision | Rationale |
|---|---|
| Modular Monolith | Separates responsibilities without the operational overhead of microservices. |
| Spring Boot with Thymeleaf | Supports server-side rendered pages without a separate frontend deployment. |
| Controller–Service–Repository layering | Separates HTTP handling, business logic, and persistence. |
| PostgreSQL with JPA/Hibernate | Provides relational storage with object-relational mapping in the application. |
| Separate application and database containers | Allows the application runtime and database to be managed independently. |
| Shared integration interfaces | Reduces coupling between business logic and external providers. |
| Docker Compose | Simplifies local orchestration and deployment of the two containers. |

### Benefits

- One application is simpler to build, test, and deploy than multiple microservices.
- Modules provide clear responsibility boundaries.
- Thymeleaf reduces the complexity of maintaining a separate frontend application.
- The database can be managed independently and stored in a persistent volume.
- Integration interfaces make it easier to change external providers.

### Trade-offs

- All modules share the same runtime and deployment lifecycle.
- Modules cannot be independently deployed or scaled as separate services.
- Module boundaries must be maintained through code organization and service-level communication.
- Two containers require configuration for networking, credentials, environment variables, and persistent storage.

## 10. Summary

The Indigenous Craft Platform uses a **Spring Boot Modular Monolith** with Thymeleaf server-side rendering and PostgreSQL persistence. The application is organized into the `User`, `Craft`, `Property`, `Moderation`, and `Admin` modules, with shared functionality in `Common`.

The Spring Boot application and PostgreSQL database are planned to run in separate Docker containers managed through Docker Compose. Optional external integrations include image storage, SMTP email delivery, and browser-based map display using Leaflet and OpenStreetMap.

This architecture balances implementation simplicity and maintainability while keeping functional responsibilities clearly separated.
