# StudyMaster Backend

This is the backend service for the StudyMaster application, built with Spring Boot.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/RobertTechnikum/studymaster-backend.git
    cd studymaster-backend
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

The backend service runs on `http://localhost:8080` by default. You can use tools like Postman or curl to interact with the API.

## Endpoints

### Authentication

- `POST /auth/login` - Authenticate a user and return a JWT token.
- `POST /auth/register` - Register a new user.

### Tasks

- `GET /api/tasks` - Retrieve all tasks.
- `GET /api/tasks/{id}` - Retrieve a specific task by ID.
- `POST /api/tasks` - Create a new task.
- `PUT /api/tasks/{id}` - Update an existing task.
- `DELETE /api/tasks/{id}` - Delete a task.

## Configuration

### Security

The security configuration is located in `at.technikum.studymasterbackend.config.SecurityConfig`. 
It uses JWT for authentication and authorization.

### Database

The application uses a docker container with a PostgreSQL database by default.
You can configure a different database in `src/main/resources/application.properties`.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

## License

This project has no license. You are free to use, modify, and distribute it as you wish.
```