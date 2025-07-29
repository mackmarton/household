# Household Management API

A Spring Boot REST API for managing household items, recipes, and dishwashing schedules. This application provides a comprehensive backend solution for household organization with OpenAPI documentation.

## 🚀 Features

- **Item Management**: Track and manage household items
- **Recipe Management**: Store and organize recipes with ingredients
- **Recipe Ingredients**: Manage ingredients for each recipe
- **Dishwashing Schedule**: Track and manage dishwashing tasks
- **RESTful API**: Clean REST endpoints for all operations
- **OpenAPI Documentation**: Interactive API documentation with Swagger UI
- **Database Integration**: PostgreSQL database with JPA/Hibernate
- **Docker Support**: Containerized deployment ready

## 🛠️ Tech Stack

- **Java 21** - Latest LTS version
- **Spring Boot 3.5.4** - Application framework
- **Spring Data JPA** - Database abstraction
- **PostgreSQL** - Primary database
- **MapStruct** - DTO mapping
- **Lombok** - Boilerplate code reduction
- **SpringDoc OpenAPI** - API documentation
- **Maven** - Dependency management
- **Docker** - Containerization

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL database
- Docker (optional, for containerized deployment)

## 🔧 Configuration

The application uses environment variables for configuration:

| Variable | Description | Example |
|----------|-------------|---------|
| `SERVER_PORT` | Application server port | `8081` |
| `DATASOURCE_URL` | PostgreSQL connection URL | `jdbc:postgresql://localhost:5432/household` |
| `DATASOURCE_PASSWORD` | Database password | `your_password` |
| `UI_URL` | Frontend application URL | `http://localhost:3000` |

## 🚀 Getting Started

### Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd household
   ```

2. **Set up environment variables**
   ```bash
   export SERVER_PORT=8081
   export DATASOURCE_URL=jdbc:postgresql://localhost:5432/household
   export DATASOURCE_PASSWORD=your_password
   export UI_URL=http://localhost:3000
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**
   - Application: `http://localhost:8081`
   - API Documentation: `http://localhost:8081/swagger-ui.html`
   - OpenAPI Spec: `http://localhost:8081/v3/api-docs`

### Docker Deployment

1. **Build the Docker image**
   ```bash
   docker build -t household-app .
   ```

2. **Run with Docker**
   ```bash
   docker run -p 8081:8081 \
     -e SERVER_PORT=8081 \
     -e DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/household \
     -e DATASOURCE_PASSWORD=your_password \
     -e UI_URL=http://localhost:3000 \
     household-app
   ```

3. **Or use Docker Compose**
   ```bash
   # Set your database password
   export DATASOURCE_PASSWORD=your_password
   
   # Run with Docker Compose (includes PostgreSQL)
   docker-compose up --build
   ```

## 📚 API Endpoints

### Items
- `GET /api/items` - Get all items
- `GET /api/items/{id}` - Get item by ID
- `POST /api/items` - Create new item
- `PUT /api/items/{id}` - Update item
- `DELETE /api/items/{id}` - Delete item

### Recipes
- `GET /api/recipes` - Get all recipes
- `GET /api/recipes/{id}` - Get recipe by ID
- `POST /api/recipes` - Create new recipe
- `PUT /api/recipes/{id}` - Update recipe
- `DELETE /api/recipes/{id}` - Delete recipe

### Recipe Ingredients
- `GET /api/recipe-ingredients` - Get all recipe ingredients
- `GET /api/recipe-ingredients/{id}` - Get recipe ingredient by ID
- `POST /api/recipe-ingredients` - Create new recipe ingredient
- `PUT /api/recipe-ingredients/{id}` - Update recipe ingredient
- `DELETE /api/recipe-ingredients/{id}` - Delete recipe ingredient

### Dishwashing
- `GET /api/dishwashing` - Get all dishwashing records
- `GET /api/dishwashing/{id}` - Get dishwashing record by ID
- `POST /api/dishwashing` - Create new dishwashing record
- `PUT /api/dishwashing/{id}` - Update dishwashing record
- `DELETE /api/dishwashing/{id}` - Delete dishwashing record

## 🗄️ Database Schema

The application uses PostgreSQL with the following main entities:
- **Items**: Household items management
- **Recipes**: Recipe storage and organization
- **Recipe Ingredients**: Ingredients linked to recipes
- **Dishwashing**: Dishwashing task tracking

Database schema is automatically managed by Hibernate with `ddl-auto=update`.

## 🔍 API Documentation

Interactive API documentation is available via Swagger UI at:
```
http://localhost:8081/swagger-ui.html
```

The OpenAPI 3.0 specification is available at:
```
http://localhost:8081/v3/api-docs
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/mackmarton/household/
│   │   ├── HouseholdApplication.java     # Main application class
│   │   ├── config/                       # Configuration classes
│   │   ├── controllers/                  # REST controllers
│   │   ├── dto/                         # Data Transfer Objects
│   │   ├── entities/                    # JPA entities
│   │   ├── mappers/                     # MapStruct mappers
│   │   ├── repositories/                # JPA repositories
│   │   └── services/                    # Business logic services
│   └── resources/
│       └── application.properties       # Application configuration
└── test/                               # Test classes
```

## 🧪 Testing

Run the test suite:
```bash
./mvnw test
```

## 📦 Building

Create a production JAR:
```bash
./mvnw clean package
```

The built JAR will be available at `target/household-0.0.1-SNAPSHOT.jar`.

## 🐳 Docker

The project includes:
- `Dockerfile` - Multi-stage Docker build
- `docker-compose.yml` - Complete stack with PostgreSQL
- `.dockerignore` - Optimized build context

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🔗 Related

- Frontend application available at the configured `UI_URL`
- API documentation: [Swagger UI](http://localhost:8081/swagger-ui.html)

---

**Happy household managing! 🏠**
