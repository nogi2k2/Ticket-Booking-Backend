# Ticket-Booking-Backend

## Overview

Ticket-Booking-Backend is a Java-based backend system for railway ticket booking, inspired by real-world railway reservation systems. This project manages train schedules, user bookings, and ticket generation while utilizing JSON for local database storage.

## Features

- **User Authentication**: Sign up and log in functionality for users.
- **Train Management**: Fetches train details, arrival/departure times, and seat availability.
- **Ticket Booking**: Reserves seats and generates tickets dynamically.
- **Cancellation & Refund**: Cancels booked tickets.
- **Local JSON Database**: Stores user and train data using JSON files as local db.

## Tech Stack

- **Java 17+** (Core Backend Logic)
- **Jackson Library** (JSON Parsing & Serialization)
- **JUnit** (Unit Testing)
- **Gradle** (Build Automation)
- **Mockito** (Unit Testing)

## Project Structure

```
│── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── App.java (Main Application Entry Point)
│   │   │   ├── entities/
│   │   │   │   ├── User.java 
│   │   │   │   ├── Train.java 
│   │   │   │   ├── Ticket.java 
│   │   │   ├── localdb/
│   │   │   │   ├── users.json 
│   │   │   │   ├── trains.json 
│   │   │   ├── services/
│   │   │   │   ├── UserBookingService.java 
│   │   │   │   ├── TrainService.java 
│   │   │   ├── util/
│   │   │   │   ├── UserServiceUtil.java
│   ├── test/
│   │   ├── java/org/example/
│   │   │   ├── entities/
│   │   │   │   ├── UserTest.java 
│   │   │   │   ├── TrainTest.java 
│   │   │   │   ├── TicketTest.java
│   │   │   ├── services/
│   │   │   │   ├── UserBookingServiceTest.java 
│   │   │   │   ├── TrainServiceTest.java 
```

## Installation & Setup

1. **Clone the repository**:
   ```sh
   git clone https://github.com/nogi2k2/Ticket-Booking-Backend.git
   ```
2. **Build the project using Gradle**:
   ```sh
   gradlew.bat build 
         (or)
   gradle build
   ```
3. **Run the application**:
   ```sh
   gradle run --console=plain (forces input through terminal)
   ```

## How to Contribute

1. Fork the repository.
2. Create a new branch (`feature-x`).
3. Commit your changes.
4. Push the branch and create a pull request.