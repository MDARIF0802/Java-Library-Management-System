## 📚 Library Management System (Java + MySQL)

A simple console-based Library Management System built in Java using JDBC to connect to a MySQL database.
It allows users to add, view, search, and delete books from a library database.

## 🧩 Features

➕ Add Book: Insert new books into the database with title, author, and year.

📖 View All Books: Display all books currently stored in the database.

🔍 Search Book by ID: Find and display a specific book by its unique ID.

❌ Delete Book: Remove a book entry from the database using its ID.

🚪 Exit Option: Gracefully close the program.

## ⚙️ Requirements

Java JDK 8 or higher

MySQL Server

MySQL JDBC Connector (add mysql-connector-j.jar to your classpath)

## 🗄️ Database Setup
Open MySQL Command Line or phpMyAdmin.

Run the following commands:

``` CREATE DATABASE library_db;

USE library_db;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    year INT NOT NULL
);

```
Update your database credentials in the code:

```
static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
static final String USER = "root";
static final String PASS = "sqlpassword";
```
(Change USER and PASS according to your local MySQL setup.)

### ▶️ How to Run

Compile the Java file:
```
javac LibraryManagementSystem.java
```

Run the program:
```
java LibraryManagementSystem
```

Follow the on-screen menu:
```
===== Library Management System =====
1. Add Book
2. View All Books
3. Search Book by ID
4. Delete Book
5. Exit
```
## 🧠 Example Usage

Input:
```
1
Enter Book Title: Clean Code
Enter author name: Robert C. Martin
Enter year: 2008
```

Output:
```
Successfully added BOOK!!!
```
💡 Future Improvements

Update book details

Add user authentication

GUI-based interface using JavaFX or Swing

Search by title or author
