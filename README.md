# KSFE Transfer Queue Management System

This project is a Spring Boot application designed to manage and generate transfer queues for employees based on their preferences and allocations. It provides an API endpoint to retrieve a list of employees who have opted for a specific office, excluding those who have already been allocated to higher-priority offices.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features
- Retrieve a list of employees who have opted for a specific office.
- Exclude employees who have already been allocated to higher-priority offices.
- Sort the results by priority value, preference order, and employee code.
- Provide vacancy status for the office.

## Technologies Used
- **Backend:** Spring Boot, Java
- **Database:** MySQL (or any relational database supported by Spring Data JPA)
- **API Testing:** Postman

## Setup and Installation

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven (for dependency management)
- MySQL (or any relational database)
- Postman (for API testing)

### Steps to Run the Project

#### Clone the Repository:
```bash
git clone https://github.com/your-username/ksfe-transfer-queue.git
cd ksfe-transfer-queue
```

#### Configure the Database:
Update the `application.properties` file with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ksfe_transfer
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

#### Build the Project:
```bash
mvn clean install
```

#### Run the Application:
```bash
mvn spring-boot:run
```

#### Access the API:
The application will start at `http://localhost:8080`.
Use Postman or any API testing tool to test the API.

## API Documentation

### Endpoint: Get Transfer Queue
- **URL:** `http://localhost:8080/transfer/queue?officeId=369`
- **Method:** `GET`
- **Description:** Retrieves a list of employees who have opted for the specified office (`officeId`), excluding those who have already been allocated to higher-priority offices.

#### Parameters:
| Parameter  | Type   | Required | Description                          |
|-----------|--------|----------|--------------------------------------|
| officeId  | `int`  | Yes      | The ID of the office to retrieve the transfer queue for. |

### Example Request:
```http
GET http://localhost:8080/transfer/queue?officeId=369
```

### Example Response:
```json
[
    {
        "employeeCode": "2507",
        "employeeName": "SANALKUMAR VS",
        "preferredOffice": 369,
        "preferenceOrder": 4,
        "priorityValue": 10,
        "vacancyStatus": "Vacancy Available"
    },
    {
        "employeeCode": "4145",
        "employeeName": "MANOJ T N",
        "preferredOffice": 369,
        "preferenceOrder": 4,
        "priorityValue": 10,
        "vacancyStatus": "No Vacancy"
    }
]
```

## Usage
1. **Start the Application:** Follow the setup instructions to run the application.
2. **Test the API:** Use Postman or any API testing tool to send a `GET` request to `http://localhost:8080/transfer/queue?officeId=369`.
3. **View Results:** The API will return a JSON response containing the list of employees eligible for the specified office.

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push to the branch.
4. Submit a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact
For any questions or feedback, please reach out to:
- **Your Name:** arjun@techpool.co.in
- **GitHub:** [arjuntechpool](https://github.com/arjuntechpool)

