# EMI Calculator

A full-stack application for calculating Equated Monthly Installment (EMI) with a Spring Boot backend and Angular frontend.

## Project Overview

The EMI Calculator is a web application that helps users calculate their monthly loan payments based on:
- Loan Amount
- Yearly Interest Rate
- Loan Term (in years)

It displays:
- **Monthly EMI** - The fixed monthly payment amount
- **Total Interest** - Total interest paid over the loan period
- **Total Payable Amount** - Total amount to be paid (principal + interest)

## Project Structure

```
EMICalculator/
├── Backend/                    # Spring Boot REST API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/EmiCalculator/demo/
│   │   │   │       ├── DemoApplication.java
│   │   │   │       ├── Controller/
│   │   │   │       │   └── EmiCalculatorController.java
│   │   │   │       └── Service/
│   │   │   │           └── EmiCalculatorService.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── Frontend/                   # Angular Web Application
│   ├── src/
│   │   ├── app/
│   │   │   ├── app.component.ts
│   │   │   ├── app.component.html
│   │   │   ├── app.component.css
│   │   │   ├── app.component.spec.ts
│   │   │   ├── app.module.ts
│   │   │   └── app-routing.module.ts
│   │   ├── index.html
│   │   ├── main.ts
│   │   └── styles.css
│   ├── angular.json
│   ├── package.json
│   ├── tsconfig.json
│   └── README.md
│
└── README.md
```

## Technology Stack

### Backend
- **Framework**: Spring Boot 4.0.0
- **Language**: Java 17
- **Build Tool**: Maven
- **Dependencies**:
  - Spring Boot Starter Actuator
  - Spring Boot Starter Web MVC
  - Lombok
  - Spring Boot DevTools

### Frontend
- **Framework**: Angular 18+
- **Language**: TypeScript
- **Build Tool**: Angular CLI / Vite
- **Styling**: CSS
- **HTTP Client**: Angular HttpClient

## Features

### Backend Features
- RESTful API endpoints for EMI calculation
- Input validation (loan amount, interest rate, loan term)
- CORS support for frontend-backend communication
- Mathematical calculation of EMI using the standard formula

### Frontend Features
- User-friendly input form
- Real-time EMI calculation
- Display of:
  - Monthly EMI amount
  - Total Interest paid
  - Total Payable amount
- Responsive design
- Input validation with error messages

## Installation

### Prerequisites
- Java Development Kit (JDK) 17+
- Node.js 18+ and npm
- Maven 3.6+
- Git

### Backend Setup

1. Navigate to the Backend directory:
```bash
cd Backend
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The backend server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the Frontend directory:
```bash
cd Frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

or

```bash
ng serve
```

The frontend will be available at `http://localhost:4200`

## API Endpoints

### EMI Calculation
- **Endpoint**: `GET /emi`
- **Parameters**:
  - `loanAmount` (double): The loan amount in currency units
  - `yearlyInterestRate` (double): The annual interest rate (0-100)
  - `loanTermYears` (int): The loan duration in years (1-30)
- **Response**: Double value representing the monthly EMI

**Example Request**:
```
GET http://localhost:8080/emi?loanAmount=100000&yearlyInterestRate=10&loanTermYears=5
```

**Example Response**:
```
2123.45
```

### Health Check
- **Endpoint**: `GET /hello`
- **Response**: "Hello" (used for testing if the server is running)

## Usage

1. **Start both backend and frontend servers** (see Installation section)

2. **Open the application** in your browser at `http://localhost:4200`

3. **Enter the loan details**:
   - Loan Amount (must be positive)
   - Yearly Interest Rate (0-100%)
   - Loan Term in Years (1-30 years)

4. **Click Calculate** to see the results:
   - Monthly EMI
   - Total Interest
   - Total Payable Amount

## Validation Rules

### Backend Validation
- **Loan Amount**: Must be greater than 0
- **Yearly Interest Rate**: Must be between 0 and 100
- **Loan Term**: Must be between 1 and 30 years

### Frontend Validation
- All input fields are required
- Valid number format required
- Real-time calculation on input change

## EMI Formula

The monthly EMI is calculated using the formula:

```
EMI = P × r × (1 + r)^n / ((1 + r)^n - 1)
```

Where:
- **P** = Principal Loan Amount
- **r** = Monthly Interest Rate (Annual Interest Rate / 12 / 100)
- **n** = Number of Months (Loan Term in Years × 12)

**Additional Calculations**:
- **Total Payable Amount** = EMI × n
- **Total Interest** = Total Payable Amount - Principal

## CORS Configuration

The backend has CORS enabled for `http://localhost:4200` to allow frontend-backend communication.

To modify CORS settings, edit the `@CrossOrigin` annotation in `EmiCalculatorController.java`.

## Error Handling

### Backend Error Responses
The backend returns `IllegalArgumentException` messages for invalid inputs:
- "Loan Amount must be a positive number"
- "Yearly Interest rate must be between 0 and 100"
- "Loan Term years should be between 0 and 30"

### Frontend Error Handling
The frontend displays user-friendly error messages and validates inputs before sending requests to the backend.


## Project Structure Details

### Backend Components

**DemoApplication.java**: Spring Boot main application class

**EmiCalculatorController.java**: 
- Handles HTTP requests
- Maps `/hello` and `/emi` endpoints
- Manages CORS

**EmiCalculatorService.java**:
- Contains business logic
- Performs EMI calculation
- Validates inputs
- Returns calculated EMI

### Frontend Components

**app.component.ts**:
- Component logic
- Handles form submission
- Calls backend API
- Calculates interest and total payable amount

**app.component.html**:
- Input form for loan details
- Display area for results
- Button to trigger calculation

**app.component.css**:
- Styling for the application
- Form and result box styling

## Browser Compatibility

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

