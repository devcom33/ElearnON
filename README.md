# 🏫 ElearnON - E-Learning REST API  

![Java](https://img.shields.io/badge/Java-21-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-✔-blue) ![JWT](https://img.shields.io/badge/JWT-Security-orange)  

## 📌 Project Overview  

**ElearnON** is a REST API built using **Spring Boot**, **PostgreSQL**, and **JWT authentication** for an **E-Learning System**. It allows students to enroll in courses, instructors to manage content, and provides authentication and role-based access control.  

## 🚀 Features  

- ✅ **User Authentication & Authorization** (Spring Security + JWT)  
- ✅ **Role-Based Access Control** (Admin, Instructor, Student)  
- ✅ **Course & Lesson Management**  
- ❌ **Enrollment & Progress Tracking** *(In Progress ⏳)*  
- ❌ **Certificate Generation** *(Not Started 🚧)*  
- ❌ **Reviews & Ratings** *(Not Started 🚧)*  
- ✅ **OpenAPI Documentation (Swagger UI)**  

## 🏗 Tech Stack  

| Technology | Description |
|------------|-------------|
| **Java 21** | Backend language |
| **Spring Boot 3.4.3** | Application framework |
| **Spring Security & JWT** | Authentication & Authorization |
| **Spring Data JPA** | ORM for database interaction |
| **PostgreSQL** | Relational database |
| **MapStruct** | DTO mapping |
| **Lombok** | Reduces boilerplate code |
| **SpringDoc OpenAPI** | API Documentation |

## 📂 Project Structure  

```
📦 src/main/java/com/system/training  
 ┣ 📂 config         # Security & configurations  
 ┣ 📂 controller     # REST controllers  
 ┣ 📂 DTO           # Data Transfer Objects  
 ┣ 📂 enums         # Enum types  
 ┣ 📂 exception     # Custom exception handling  
 ┣ 📂 mappers       # DTO to entity mappers  
 ┣ 📂 model         # Entity models  
 ┣ 📂 repository    # Database repositories  
 ┣ 📂 service       # Business logic layer  
 ┗ 📜 TrainingApplication.java  # Main Spring Boot Application  
```

## ⚙️ Setup & Installation  

### 1️⃣ Clone the Repository  

```bash
git clone https://github.com/devcom33/ElearnON.git  
cd ElearnON  
```

### 2️⃣ Configure Database  

Update `application.properties` with your **PostgreSQL** credentials:  

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/elearnon  
spring.datasource.username=your_db_user  
spring.datasource.password=your_db_password  
spring.jpa.hibernate.ddl-auto=update  
spring.security.jwt.secret=your-secret-key  
```

### 3️⃣ Build & Run the Project  

```bash
mvn clean install  
mvn spring-boot:run  
```

### 4️⃣ API Documentation  

After running the app, visit:  

🔗 **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  

## 🔐 Authentication  

To access secured endpoints, generate a **JWT token** by logging in:  

**POST** `/api/auth/login`  

```json
{
  "email": "mouad@gmail.com",
  "password": "password"
}
```

Use the received **JWT token** in headers:  

```
Authorization: Bearer <your-token>
```

## 🛠 API Endpoints  

| Endpoint | Description | Access |
|----------|------------|--------|
| `POST /api/auth/register` | Register new user | Public |
| `POST /api/auth/login` | Authenticate & get JWT | Public |
| `GET /api/courses` | Get all courses | Public |
| `POST /api/courses` | Create a new course | Instructor/Admin |
| `POST /api/enrollments` | Enroll a student *(In Progress ⏳)* | Student |
| `GET /api/progress` | Get user progress *(In Progress ⏳)* | Student |
| `POST /api/reviews` | Add course review *(Not Started 🚧)* | Student |

## 📜 License  

This project is **open-source** and available under the [MIT License](LICENSE).  

---

💡 **Contributions & Issues**  
Feel free to open issues or submit pull requests! 🚀  

🔗 **Author:** [Devcom33](https://github.com/devcom33)  
