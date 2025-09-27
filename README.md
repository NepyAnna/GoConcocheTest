# GoConcoche
The GoConCoche project proposes the development of an intelligent backend system for car
sharing, designed to simulate real-world mobility services. The
system will enable users to search for, book, and manage car-sharing reservations at specific
locations; allow car owners to manage their rental offers; and provide administrators with tools to
oversee vehicle fleets and booking rules. It will also include advanced features such as secure
authentication using JWT. The project will be implemented using Java 21, Maven, and MySQL,
with a strong focus on modularity, security, and eﬃciency.

## Main Feature

## Technologies Used
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![draw.io](https://img.shields.io/badge/draw.io-F08705?style=for-the-badge&logo=diagramsdotnet&logoColor=white)
![Cloudinary](https://img.shields.io/badge/cloudinary-3448C5?style=for-the-badge&logo=cloudinary&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-%2385EA2D.svg?style=for-the-badge&logo=swagger&logoColor=black)

### Clone the Repository

### Run

## API Endpoints

### Registration / Login
- POST http://localhost:8080/api/registar registration of users
- POST http://localhost:8080/api/login for login
- POST http://localhost:8080/api/refresh for refresh token
- POST http://localhost:8080/api/logout for logout

### User
- GET http://localhost:8080/api/users to get all users (only for ADMIN)
- GET http://localhost:8080/api/users/{id} to get user by ID
- GET http://localhost:8080/api/users/profile to get user own profile
- PUT http://localhost:8080/api/users/{id} to update user by ID
- DELETE http://localhost:8080/api/users/{id} to delete user by ID

### Vehicle
- GET http://localhost:8080/api/vehicles to get all vehicles (only for ADMIN)
- GET http://localhost:8080/api/vehicles/my get own vehicles
- GET http://localhost:8080/api/vehicles/{id} to get vehicle by ID
- GET http://localhost:8080/api/vehicles/search?fuelTypeCar=PETROL&seater=SEDAN to get vehicle by ID
- POST http://localhost:8080/api/vehicles to create vehicle
- PUT http://localhost:8080/api/vehicles/{id} to update vehicle by ID
- DELETE http://localhost:8080/api/vehicles/{id} to delete vehicle by ID

### Rental offers
- GET http://localhost:8080/api/rental-offers to get all rental offers (only for ADMIN)
- GET http://localhost:8080/api/rental-offers/search?status=TRUE&locationId=1 to get available rental offers
- GET http://localhost:8080/api/rental-offers/{id} to get rental offer by ID
- POST http://localhost:8080/api/rental-offers to create rental offer
- PUT http://localhost:8080/api/rental-offers/{id} to update rental offers by ID
- DELETE http://localhost:8080/api/rental-offers/{id} to delete rental offer by ID

### Reservation (of Rental offers) 
- GET http://localhost:8080/api/reservations to get all reservations (only for ADMIN)
- GET http://localhost:8080/api/reservations/my to get own reservations
- GET http://localhost:8080/api/reservations/{id} to get reservation by ID
- POST http://localhost:8080/api/reservations to create reservation
- PUT http://localhost:8080/api/reservations/{id} to update reservation date by ID
- DELETE http://localhost:8080/api/reservations/{id} to delete reservation by ID

## Running Tests

## ER physical Diagram

[![temp-Imagemxv-S8-B.avif](https://i.postimg.cc/JhvdWdQt/temp-Imagemxv-S8-B.avif)](https://postimg.cc/CZC4G7XV)

## 🔄 Workflow - Pipelines

The project uses **GitHub Actions** for continuous integration.

This project implements a robust CI/CD pipeline using GitHub Actions to automate testing, Docker image building, and release management.

### 🔧 Build Pipeline (build.yml)
[![Build Pipeline](https://github.com/More-ThanCode/GoConcoche/actions/workflows/build.yml/badge.svg)](https://github.com/More-ThanCode/GoConcoche/actions/workflows/build.yml)

**Trigger:** Automatically executes on every push to main branch

**Purpose:** Build and publish development images

**Activities:**

- Docker image building and optimization
- Pushing images to Docker Hub registry
- Tagging images with commit SHAs
- Generating build artifacts and reports

### 🎯 Release Pipeline (release.yml)
[![Release Pipeline](https://github.com/More-ThanCode/GoConcoche/actions/workflows/release.yml/badge.svg)](https://github.com/More-ThanCode/GoConcoche/actions/workflows/release.yml)

**Trigger:** Automatically activates when version tags are pushed (format: v*, e.g., v1.0.0)

**Purpose:** Production-ready deployments

### 🔍 Test Pipeline (test.yml)
[![Test Pipeline](https://github.com/More-ThanCode/GoConcoche/actions/workflows/test.yml/badge.svg)](https://github.com/More-ThanCode/GoConcoche/actions/workflows/test.yml)

**Trigger:** Automatically runs on every Pull Request targeting main

**Purpose:** Quality assurance before code merging

**Activities:**

- Runs comprehensive test suite in Docker containers
- Executes security scans and code analysis
- Generates test coverage reports
- Uses docker-compose-test.yml for test environment
- Automatic cleanup after execution

## Contributors

Morena Peralta Almada
    <a href="https://github.com/morenaperalta">
        <picture>
            <source srcset="https://img.icons8.com/ios-glyphs/30/ffffff/github.png" media="(prefers-color-scheme: dark)">
            <source srcset="https://img.icons8.com/ios-glyphs/30/000000/github.png" media="(prefers-color-scheme: light)">
            <img src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="GitHub icon"/>
        </picture>
    </a>

Nia
    <a href="https://github.com/niaofnarnia">
        <picture>
            <source srcset="https://img.icons8.com/ios-glyphs/30/ffffff/github.png" media="(prefers-color-scheme: dark)">
            <source srcset="https://img.icons8.com/ios-glyphs/30/000000/github.png" media="(prefers-color-scheme: light)">
            <img src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="GitHub icon"/>
        </picture>
    </a>

Mary Kenny
    <a href="https://github.com/marykenny123">
        <picture>
            <source srcset="https://img.icons8.com/ios-glyphs/30/ffffff/github.png" media="(prefers-color-scheme: dark)">
            <source srcset="https://img.icons8.com/ios-glyphs/30/000000/github.png" media="(prefers-color-scheme: light)">
            <img src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="GitHub icon"/>
        </picture>
    </a>

Sofía Santos
<a href="https://github.com/sofianutria">
    <picture>
            <source srcset="https://img.icons8.com/ios-glyphs/30/ffffff/github.png" media="(prefers-color-scheme: dark)">
            <source srcset="https://img.icons8.com/ios-glyphs/30/000000/github.png" media="(prefers-color-scheme: light)">
            <img src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="GitHub icon"/>
        </picture>
    </a>

Anna Nepyivoda
    <a href="https://github.com/NepyAnna">
        <picture>
            <source srcset="https://img.icons8.com/ios-glyphs/30/ffffff/github.png" media="(prefers-color-scheme: dark)">
            <source srcset="https://img.icons8.com/ios-glyphs/30/000000/github.png" media="(prefers-color-scheme: light)">
            <img src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="GitHub icon"/>
        </picture>
    </a># Test trigger
