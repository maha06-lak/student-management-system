# Student Record Management System (Console App)

A beginner-friendly Java console project to manage student records with data
saved to a file, so nothing is lost when you close the program.

## What it demonstrates
- Object-Oriented Programming (classes, encapsulation)
- Collections (`ArrayList`)
- File I/O (reading/writing a text file for persistence)
- Exception handling
- A menu-driven CLI using `Scanner`

## Project structure
```
student-record-system/
└── src/
    └── com/app/studentmanager/
        ├── Student.java         (model class)
        ├── StudentManager.java  (business logic + file save/load)
        └── Main.java            (console menu / entry point)
```

## How to run

### Option 1: IntelliJ IDEA / Eclipse
1. Open IntelliJ or Eclipse.
2. Choose "Open" and select the `student-record-system` folder.
3. Mark `src` as the Sources Root (IntelliJ usually detects this automatically).
4. Right-click `Main.java` → Run.

### Option 2: Command line
```bash
cd student-record-system
javac -d out src/com/app/studentmanager/*.java
cd out
java com.app.studentmanager.Main
```

A file called `students.txt` will be created automatically in the folder
you run the program from — that's where your data is saved.

## Menu options
1. Add Student
2. View All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Exit

## Ideas to extend it (once you're comfortable)
- Add input validation (e.g., reject empty names, negative ages)
- Sort students by name or age before displaying
- Add a "search by name" feature (partial match)
- Switch file storage to CSV format, or later to a real database (H2/MySQL)
- Add JUnit tests for `StudentManager`

This project is intentionally simple so you can read every line and
understand exactly what it does — a great foundation before moving on to
frameworks like Spring Boot.
