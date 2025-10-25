# 🏬 Warehouse Management API  
**Spring Boot RESTful Application** for managing warehouses, products, categories, transactions, and users.  
Created with ❤️ by **Murod** 💻  

---

## 🌟 Overview
This project is a **Warehouse Management System** built using **Spring Boot**, **Hibernate (JPA)**, and **PostgreSQL**.  
It provides RESTful endpoints to perform CRUD operations on:
- Categories 📂  
- Products 📦  
- Warehouses 🏢  
- Transactions 💰  
- Users 👤  

The project also includes **Swagger UI** for API documentation and testing.

---

## ⚙️ Tech Stack
| Layer | Technology |
|--------|-------------|
| Backend | Java 17, Spring Boot 3 |
| ORM | Hibernate / JPA |
| Database | PostgreSQL |
| API Docs | SpringDoc OpenAPI (Swagger) |
| Build Tool | Maven |
| Validation | Jakarta Validation (Bean Validation) |
| Logging | SLF4J / Logback |
| Security (future) | Spring Security + JWT |
| Testing (optional) | JUnit, Mockito, Testcontainers |

---

## 🧱 Project Structure
```
warehouse/
 ├── src/main/java/uz/app/
 │   ├── controller/        # REST controllers
 │   ├── service/           # Business logic layer
 │   ├── repository/        # JPA repositories
 │   ├── entity/            # Database entities
 │   ├── enums/             # Enum definitions
 │   └── WarehouseProjectApplication.java
 ├── src/main/resources/
 │   ├── application.yml    # Configuration file
 │   └── static/            # (if needed)
 └── pom.xml
```

---

## 🚀 Features

✅ Full CRUD for all entities  
✅ Swagger UI for testing endpoints  
✅ PostgreSQL database integration  
✅ DTO-ready structure (easily extendable)  
✅ Exception handling with `@ControllerAdvice`  
✅ Supports soft delete fields (`deletedAt`)  
✅ Clean layered architecture  

---

## 🔧 Installation & Setup

### 1️⃣ Clone the project
```bash
git clone https://github.com/yourusername/warehouse.git
cd warehouse
```

### 2️⃣ Configure Database
Create a PostgreSQL database:
```sql
CREATE DATABASE warehouse;
```

### 3️⃣ Edit your `application.yml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/warehouse
    username: postgres
    password: yourpassword
```

### 4️⃣ Run the application
```bash
mvn spring-boot:run
```

Once started, the API will be available at:
```
http://localhost:8080
```

---

## 📘 Swagger API Documentation

Once the application is running, open:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

You can:
- Explore all endpoints  
- Test CRUD operations  
- See live request/response examples  

---

## 📦 Example Endpoints

### 🧩 Categories
| Method | Endpoint | Description |
|--------|-----------|--------------|
| `POST` | `/api/categories` | Create new category |
| `GET` | `/api/categories` | Get all categories |
| `GET` | `/api/categories/{id}` | Get category by ID |
| `PUT` | `/api/categories/{id}` | Update category |
| `DELETE` | `/api/categories/{id}` | Delete category |

---

## 🧠 Future Improvements

🚀 Add DTO and Validation (`@Valid`, `@NotBlank`, `@Email`, etc.)  
🔐 Add Authentication & JWT Security  
💾 Implement Soft Delete with filtering (`deletedAt IS NULL`)  
🧪 Add Unit and Integration Tests  
🐳 Dockerize the application  
🧰 Add Flyway for DB migrations  

---

## 🛠️ Developer Notes

- Default Port: **8080**  
- Database: **PostgreSQL 17+**  
- Recommended IDE: **IntelliJ IDEA Ultimate**  
- Java version: **17 or higher**

---

## 👨‍💻 Author
**👤 Murod**  
💬 *Java Developer | Learning every day | Building strong foundations*

GitHub: [github.com/sdlxnvv](https://github.com/yourusername)  
Email: *sadulloxonovm@yandex.ru*  

---

## 🧾 License
This project is released under the **MIT License** — free to use and modify.  

---

## 💬 Example Swagger Screenshot
*(Optional — you can attach a screenshot of Swagger UI here)*  
