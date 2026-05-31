# Pharmacy Management System

A full-stack pharmacy management system built using Spring Boot, Thymeleaf, Bootstrap, and H2 Database. The system helps manage medicines, sales, prescriptions, suppliers, customers, and inventory operations with secure role-based authentication.

---

## Features

* Secure Authentication & Authorization

  * Role-based login system (ADMIN / STAFF)
  * Password validation with security rules
  * Password visibility toggle

* Inventory Management

  * Add, update, and delete medicines
  * Track stock quantity and expiry dates
  * Expiry status highlighting

* Sales & Billing

  * Generate and manage medicine sales
  * Multiple payment methods support

* Prescription Management

  * Store and verify prescriptions

* Customer Management

  * Manage customer profiles and purchase history

* Supplier Management

  * Store supplier details and medicine records

* Alerts & Notifications

  * Automatic alerts for low stock and expiry medicines

* Responsive User Interface

  * Built using Bootstrap and Thymeleaf templates

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Thymeleaf
* Bootstrap
* H2 Database
* Maven

---

## Project Setup

1. Clone the repository

```bash
git clone https://github.com/MAYURIBHOR/Pharmacy-Management-System.git
```

2. Navigate to the project folder

```bash
cd Pharmacy-Management-System
```

3. Run the application

```bash
mvn spring-boot:run
```

4. Open in browser

```bash
http://localhost:8080
```

---

## Authentication

* Users can register as:

  * ADMIN
  * STAFF

Password requirements:

* Minimum 8 characters
* One uppercase letter
* One lowercase letter
* One special character

---

## Database

This project uses the H2 Database.

H2 Console:

```bash
http://localhost:8080/h2-console
```

JDBC URL:

```bash
jdbc:h2:file:~/pharmacydb
```


## Screenshots

### Login Page

<img width="1599" height="848" alt="WhatsApp Image 2026-05-31 at 11 15 02 PM" src="https://github.com/user-attachments/assets/af45a53e-ec91-47b4-886f-04bb057b8bd8" />

### Dashboard

<img width="1600" height="846" alt="WhatsApp Image 2026-05-31 at 11 19 22 PM (1)" src="https://github.com/user-attachments/assets/56f072a6-2421-4f4d-ae81-826f7ec4f6a0" />


### Inventory Management

<img width="1600" height="846" alt="WhatsApp Image 2026-05-31 at 11 19 22 PM" src="https://github.com/user-attachments/assets/fd99324c-8594-40c2-8b88-245768b1f60e" />


### Sales Management

<img width="1599" height="848" alt="WhatsApp Image 2026-05-31 at 11 40 46 PM" src="https://github.com/user-attachments/assets/a2af6afd-5af6-4f4b-9437-1f4338eebbe6" />


### Prescription Management

<img width="1600" height="857" alt="WhatsApp Image 2026-05-31 at 11 43 07 PM" src="https://github.com/user-attachments/assets/b17cdbee-2998-4315-95e7-a17e2f79fcf6" />


---

## Future Improvements

* MySQL/PostgreSQL Integration
* Cloud Deployment
* Email Notifications
* Barcode Scanner Integration
* Sales Analytics Dashboard

---

## Author

Mayuri Bhor

GitHub:
https://github.com/MAYURIBHOR
