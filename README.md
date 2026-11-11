# Student Management System

A console-based Java application developed as the final project for a six-week Java development internship. The application manages student records through a menu-driven interface and demonstrates core Java and object-oriented programming concepts.

## Features

- Add a student with ID, name, age, course, and marks
- View all student records
- Search for a student using their ID
- Update an existing student's details
- Delete a student record
- Display an academic performance summary
- Save student records to a text file and load them when the application starts
- Create timestamped backups using a separate Java thread
- Validate student details and handle common input and file errors

## Technologies and Concepts

- **Language:** Java
- **IDE:** IntelliJ IDEA
- **JDK:** Use the JDK configured for the project
- **OOP:** Classes and objects, encapsulation, abstraction, inheritance, polymorphism, constructors, `this`, `super`, and `static`
- **Collections:** `ArrayList`, `HashSet`, and `HashMap`
- **File handling:** `BufferedReader`, `BufferedWriter`, and try-with-resources
- **Exception handling:** `NumberFormatException`, `IllegalArgumentException`, and `IOException`
- **Multithreading:** `Runnable` and `Thread`

## Project Structure

The structure below reflects the package layout used in the project. Keep generated data files in the location configured by the application's relative file paths.

```text
StudentManagementSystem/
├── src/
│   └── StudentManagementSystem/
│       ├── Main.java
│       ├── model/
│       │   ├── Person.java
│       │   └── Student.java
│       ├── service/
│       │   └── StudentService.java
│       ├── storage/
│       │   └── StudentFileManager.java
│       └── threads/
│           └── BackupTask.java
├── student_records.txt       # created/used at runtime
└── backups/                  # created when a backup is made
```

> IntelliJ IDEA may show the source root and package hierarchy differently depending on how the project was created. Preserve the existing package declarations and folder structure.

## How to Run in IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Confirm that a JDK is configured under **File → Project Structure → Project**.
3. Open `src/StudentManagementSystem/Main.java`.
4. Run the `main()` method.
5. Follow the numbered menu in the Run console.

## How to Run from a Terminal

Run these commands from the project root. If your source folder or package name differs, adjust the paths to match your project.

```bash
javac -d out src/StudentManagementSystem/model/Person.java src/StudentManagementSystem/model/Student.java src/StudentManagementSystem/storage/StudentFileManager.java src/StudentManagementSystem/threads/BackupTask.java src/StudentManagementSystem/service/StudentService.java src/StudentManagementSystem/Main.java
java -cp out StudentManagementSystem.Main
```

If your `Main.java` has a different package declaration, use that fully qualified class name in the final command.

## Data Storage

Student records are stored in `student_records.txt`, with one record per line. The fields are separated by a vertical bar (`|`).

Example:

```text
101|Rahul|20|ISE|89.5
102|Priya|21|CSE|95.0
```

The example records are illustrative. The application creates or updates the data file when records are saved. Do not commit real or sensitive student information to a public repository.

## Backup

Choose **Create Backup** from the application menu. The backup task runs on a separate thread and copies the saved records file into a `backups` directory with a timestamped filename.

## Testing Checklist

Before submitting, verify these cases in your own run:

- [ ] Add a student with valid details.
- [ ] Reject a duplicate student ID.
- [ ] Reject invalid age, blank name/course, and marks outside the accepted range.
- [ ] View all students.
- [ ] Search for an existing and a nonexistent student ID.
- [ ] Update an existing student and verify the saved file changes.
- [ ] Delete an existing student and verify the saved file changes.
- [ ] Restart the application and confirm saved records are loaded.
- [ ] Create a backup and confirm a timestamped file appears in `backups`.
- [ ] Test the performance summary with known sample values.

Update this checklist based on the tests you actually execute.

## Screenshots

<img width="1366" height="948" alt="Screenshot 2026-11-11 103735" src="https://github.com/user-attachments/assets/bef1439e-2458-447c-92ef-bc70e48ee1d1" />

<img width="1366" height="768" alt="Screenshot 2026-11-11 103800" src="https://github.com/user-attachments/assets/b625c44e-823c-4e18-b46b-a5d1ac13109b" />

<img width="1366" height="1278" alt="Screenshot 2026-11-11 103750" src="https://github.com/user-attachments/assets/06c02565-3eb3-4d58-852c-5eff440c3e7c" />





1. Application main menu
2. Adding and viewing student records
3. Search, update, and delete operations
4. Performance summary
5. `student_records.txt` contents using dummy data
6. Backup creation output and the `backups` folder
7. IntelliJ IDEA project structure


## Limitations and Future Enhancements

- The current application is a local, single-user console program.
- Records are stored in a text file rather than a database.
- The backup is user-triggered rather than scheduled.
- A delimiter-based file format requires care if user-entered fields contain the delimiter.

Possible future improvements include database integration, a graphical or web interface, authentication, sorting and filtering, automated unit tests, structured logging, and scheduled backups.

## Author

**Shalu Kumari D N**

Java Development Internship — Final Project
