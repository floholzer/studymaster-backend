# StudyMaster Backend

This is the backend service for the StudyMaster application, built with Spring Boot.

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
- `GET /badges/{userId}` - Retrieve all badges of a user.
- `GET /badges/{badgeId}` - Retrieve the badge of the given id.

### Progress

- `GET /progress/{userId}` - Retrieve the progress of a user by ID. Maybe delete later.

### Semester

- `GET /semester` - Body: UserId - Retrieve all semesters of a user.
- `POST /semester` - Create a new semester.
- `PUT /semester/{id}` - Update an existing semester.
- `DELETE /semester/{id}` - Delete a semester.

### Subject

- `GET /subject` - Retrieve all subjects.
- `POST /subject` - Create a new subject.
- `PUT /subject/{id}` - Update an existing subject.
- `DELETE /subject/{id}` - Delete a subject.

### Tasks

- `GET /tasks/{userId}` - Retrieve all tasks of a user by ID.
- `POST /tasks` - Create a new task.
- `PUT /tasks/{id}` - Update an existing task.
- `DELETE /tasks/{id}` - Delete a task.
- `GET /tasks/test` - Test endpoint for tasks.
- `POST /tasks/{id}/complete` - Mark a task as completed.

```