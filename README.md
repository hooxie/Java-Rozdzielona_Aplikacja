## About The Project

**Java-Rozdzielona_Aplikacja** is a distributed backend application built with Spring Boot, demonstrating a microservices architecture designed for a VOD and cinema management platform. The system separates distinct domain logics into independent services to ensure high scalability and modular deployment.

### Key Features:
* **Microservices Architecture:** Split into decoupled services including `ms-movies` (managing movies, directors, and catalog data) and `ms-cinemas` (handling cinema locations and showtimes).
* **Data Persistence & REST API:** Uses Spring Data JPA repositories (`MovieRepository`, `CinemaRepository`) to map domain models directly to a MySQL database initialized automatically via custom SQL scripts.
* **Secured Control:** Includes a dedicated security module (`VodSecurityConfig`) to safeguard data and manage authorized requests across endpoints.
* **Containerization & Orchestration:** Fully containerized using Docker with a multi-container environment defined in `docker-compose.yml`, alongside Kubernetes manifests (`ms-movies.yml`) for cloud-native orchestration.
