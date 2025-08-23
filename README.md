# Event Management System

A full-stack event management platform where organizers publish and run events, attendees discover and register for them, and administrators oversee the whole system. The backend is a Spring Boot REST API secured with JWT and backed by MongoDB; the frontend is a React and TypeScript single-page app built with Vite and Tailwind CSS.

## Features

- **Authentication and authorization.** Registration, login, token refresh, logout and password reset, with JWT-based sessions and role-based access control.
- **Three user roles.**
  - *Organizer*: create, edit and publish events, and track who has registered.
  - *Attendee*: browse and search events, register, and manage their own registrations.
  - *Admin*: manage users and events across the platform.
- **Event management.** Categories (conference, workshop, meetup, seminar, webinar, other), physical, online or hybrid locations, capacity limits, registration deadlines and a status lifecycle from draft to completed.
- **Registrations.** Confirmed, waitlisted and cancelled states, plus attendance tracking.
- **Role-specific dashboards** and an **event calendar** view.
- **Light and dark themes** in the UI.
- **Interactive API documentation** through Swagger UI.

## Tech stack

| Layer | Technologies |
| --- | --- |
| Backend | Java 17, Spring Boot 3.2, Spring Security, Spring Data MongoDB, JJWT, Lombok, SpringDoc OpenAPI, Spring Mail, Micrometer |
| Frontend | React 18, TypeScript, Vite, React Router, Tailwind CSS, Framer Motion, Lucide icons, React Hot Toast, React DatePicker |
| Database | MongoDB |
| Testing | JUnit 5, Mockito |

## Project structure

```
Event-Management-System/
├── backend/                      Spring Boot REST API
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/eventmanagement/
│       │   ├── config/           Security, CORS and OpenAPI configuration
│       │   ├── controller/       REST controllers
│       │   ├── dto/              Request and response objects
│       │   ├── exception/        Global error handling
│       │   ├── model/            MongoDB documents and enums
│       │   ├── repository/       Spring Data repositories
│       │   ├── security/         JWT utilities and filters
│       │   └── service/          Business logic
│       ├── main/resources/
│       │   └── application.properties
│       └── test/                 Unit tests
└── frontend/                     React single-page app
    ├── package.json
    └── src/
        ├── components/           Auth, Dashboard, Events, Calendar, Layout and UI components
        ├── contexts/             Auth and theme state
        ├── services/             API clients
        └── types/                Shared TypeScript types
```

## Getting started

### Prerequisites

- Java 17 or later
- Maven 3.9 or later
- Node.js 18 or later
- A running MongoDB instance (local or hosted)

### 1. Clone the repository

```bash
git clone https://github.com/rabeyanoor/Event-Management-System.git
cd Event-Management-System
```

### 2. Run the backend

Set the required environment variables, then start the API:

```bash
cd backend
export MONGODB_URI="mongodb://localhost:27017/Event_Management_DB"
export JWT_SECRET="<base64-encoded secret, at least 256 bits>"
export MAIL_USERNAME="<smtp username>"
export MAIL_PASSWORD="<smtp password>"
mvn spring-boot:run
```

The API starts on `http://localhost:8080`, and the Swagger UI is available at `http://localhost:8080/swagger-ui/index.html`.

To generate a suitable JWT secret:

```bash
openssl rand -base64 32
```

### 3. Run the frontend

```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```

The app opens on `http://localhost:5173`. The backend only accepts browser requests from this origin, so keep the default port or update the CORS settings in `WebConfig` and `SecurityConfig`.

## Configuration

### Backend

| Variable | Required | Description |
| --- | --- | --- |
| `MONGODB_URI` | No | MongoDB connection string. Defaults to `mongodb://localhost:27017/Event_Management_DB`. |
| `JWT_SECRET` | Yes | Base64-encoded key used to sign access and refresh tokens. |
| `MAIL_USERNAME` | Yes | SMTP username for outgoing email, such as password resets. |
| `MAIL_PASSWORD` | Yes | SMTP password. |

Token lifetimes, the SMTP host and logging levels are set in `backend/src/main/resources/application.properties`.

### Frontend

| Variable | Description |
| --- | --- |
| `VITE_API_BASE_URL` | Base URL of the backend API, for example `http://localhost:8080/api`. |

## API overview

All endpoints are prefixed with `/api`. Full request and response schemas are in the Swagger UI.

| Area | Endpoints |
| --- | --- |
| Auth | `POST /auth/register`, `POST /auth/login`, `POST /auth/refresh`, `POST /auth/logout`, `POST /auth/forgot-password`, `POST /auth/reset-password` |
| Events | `GET /events`, `POST /events`, `GET /events/{id}`, `PUT /events/{id}`, `DELETE /events/{id}`, `GET /events/search`, `GET /events/categories`, `GET /events/organizer/{organizerId}`, `PUT /events/{id}/status` |
| Registrations | `POST /registrations`, `PUT /registrations/{id}`, `DELETE /registrations/{id}`, `PUT /registrations/{id}/attendance`, `GET /registrations/user/{userId}`, `GET /registrations/event/{eventId}` |
| Users | `GET /users/profile`, `PUT /users/profile`, `GET /users`, `GET /users/{id}`, `DELETE /users/{id}` |
| Dashboards | `GET /dashboard/organizer`, `GET /dashboard/attendee`, `GET /dashboard/admin` |

Write operations on events are limited to their organizer or an admin, and user management endpoints are admin-only.

## Building and testing

```bash
# Backend: run unit tests and build the JAR
cd backend
mvn clean package

# Frontend: production build
cd frontend
npm run build
```

## Author

**Rabeya Noor** · [GitHub](https://github.com/rabeyanoor)
