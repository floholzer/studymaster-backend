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

WIP

## Usage

The backend service runs on `http://<hostname>:8080` by default.

## API-Endpoints

### Authentication

- `POST /auth/login` - Authenticate a user and return a JWT token.

### User

- `POST /user/register` - Register a new user.
- `PUT /user/{id}` - Update a user by ID.
- `GET /user/getAll` - Retrieve all users.

### Badges

- `GET /badges` - Retrieve all badges.

### Progress

- `GET /progress/{userId}` - Retrieve the progress of a user by ID. Maybe delete later.

### Semester -> Maybe rework?

- `GET /semester` - Body: UserId - Retrieve all semesters of a user.
- `POST /semester` - Create a new semester.

### Subject -> Maybe rework?

- `GET /subject` - Retrieve all subjects.
- `POST /subject` - Create a new subject.

### Tasks

- `GET /tasks/{userId}` - Retrieve all tasks of a user by ID.
- `POST /tasks` - Create a new task.
- `PUT /tasks/{id}` - Update an existing task.
- `DELETE /tasks/{id}` - Delete a task.
- `GET /tasks/test` - Test endpoint for tasks.
- `POST /tasks/{id}/complete` - Mark a task as completed.

```