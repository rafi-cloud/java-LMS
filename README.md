# 📚 Library Management System (LMS) 
## OODP101 Assesment 3 - Group 3 
### Developed by: 
    • Rafi Miazi (K250249) - Team Leader
    • Utsav Paudel (K250066)
    • Thi Phuong Thanh bui (K250200)

## 📝 Project Overview

The **Library Management System (LMS)** is a console-based Java application designed to manage a library's collection of books, its members, and borrowing transactions. This project demonstrates the principles of **Object-Oriented Design and Programming (OODP)** and applies real-world concepts such as encapsulation, inheritance, and polymorphism.

---

## 📦 Features

- 📘 Manage a catalogue of books (add, search, remove)
- 👥 Register and manage members
- 📚 Borrow and return books with rules based on membership types
- ⏱️ Calculate due dates and late return fees
- 🧾 Generate transaction summaries
- 🖥️ Console-based interaction

---

## 👤 Member Types & Rules

| Membership Type | Max Books | Borrow Period | Late Fee Policy |
|-----------------|-----------|----------------|------------------|
| Regular         | 3         | 2 weeks        | Charged          |
| Premium         | 5         | 4 weeks        | Charged          |
| Guest           | 1         | 1 week         | Charged (No renewals) |

---

## 🧱 Class Structure

- **`Book.java`** – Represents a book in the library
- **`Member.java`** – Represents a member (Regular, Premium, Guest)
- **`Library.java`** – Manages collections of books and members
- **`Transaction.java`** – Records borrowing activities and calculates due dates
- **`LibraryDriver.java`** – Entry point (main method) for running the system

---

## 🚀 How to Run

### ✅ Prerequisites:
- Java JDK 17 or higher
- IDE or terminal with Java support (IntelliJ, VS Code, or Terminal)
