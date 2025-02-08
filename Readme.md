# Ticket-Booking-Backend

## Overview

Ticket-Booking-Backend is a Java-based backend system for railway ticket booking, inspired by real-world railway reservation systems. This project manages train schedules, user bookings, and ticket generation while utilizing JSON for local database storage.

## Features

- **User Authentication**: Sign up and log in functionality for users.
- **Train Management**: Fetch train details, arrival/departure times, and seat availability.
- **Ticket Booking**: Reserve seats and generate tickets dynamically.
- **Cancellation & Refund**: Cancel booked tickets with refund calculations.
- **Local JSON Database**: Store user and train data using JSON files.

## Tech Stack

- **Java 17+** (Core Backend Logic)
- **Jackson Library** (JSON Parsing & Serialization)
- **JUnit** (Unit Testing)
- **Gradle** (Build Automation)

## Project Structure

```
│── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── App.java (Main Application Entry Point)
│   │   │   ├── entities/
│   │   │   │   ├── User.java (User Entity)
│   │   │   │   ├── Train.java (Train Entity)
│   │   │   │   ├── Ticket.java (Ticket Entity)
│   │   │   ├── services/
│   │   │   │   ├── UserBookingService.java (Handles user bookings)
│   │   │   │   ├── TrainService.java (Manages train data)
│── test/
│   ├── java/org/example/
│   │   ├── AppTest.java (Unit Tests)
│── localdb/
│   ├── users.json (Mock user database)
│   ├── trains.json (Mock train database)
│── build.gradle (Gradle Configuration)
│── README.md (Project Documentation)
```

## Installation & Setup

1. **Clone the repository**:
   ```sh
   git clone https://github.com/nogi2k2/Ticket-Booking-Backend.git
   cd IRCTC-Backend
   ```
2. **Build the project using Gradle**:
   ```sh
   gradlew.bat build
   ```
3. **Run the application**:
   ```sh
   gradle run
   ```

## How to Contribute

1. Fork the repository.
2. Create a new branch (`feature-xyz`).
3. Commit your changes.
4. Push the branch and create a pull request.