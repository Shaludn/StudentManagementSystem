
package StudentManagementSystem.service;
import StudentManagementSystem.storage.StudentFileManager;
import StudentManagementSystem.threads.BackUpTask;

import StudentManagementSystem.model.Student;

import java.util.*;
import java.util.Scanner;

public class StudentService {

    // ArrayList to store Student objects
    private ArrayList<Student> students = new ArrayList<>();
    private HashSet<Integer> studentIds = new HashSet<>();

    private HashMap<Integer, Student> studentMap = new HashMap<>();
    public StudentService() {

        students = StudentFileManager.loadStudents();

        // Rebuild HashSet and HashMap from loaded records
        for (Student student : students) {

            int id = student.getStudentId();

            if (studentIds.add(id)) {
                studentMap.put(id, student);
            }
        }
    }

    // 1. Add Student
    public void addStudent(Scanner sc) {

        try {
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(sc.nextLine());

            // Check for duplicate ID
            if (studentIds.contains(id)) {
                System.out.println("Student ID already exists!");
                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine());

            if (id <= 0 || age <= 0 || marks < 0 || marks > 100
                    || name.isBlank() || course.isBlank()) {
                System.out.println("Invalid student details!");
                return;
            }

            Student student = new Student(
                    id, name, age, course, marks
            );

            students.add(student);
            studentIds.add(id);
            studentMap.put(id, student);
            StudentFileManager.saveStudents(students);

            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values!");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid student details: " + e.getMessage());
        }
    }

    // 2. View All Students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");

        for (Student student : students) {
            student.displayDetails();
        }
    }

    // Helper method to search by ID
    private Student searchById(int id) {

        return studentMap.get(id);
    }
    // 3. Search Student
    public void searchStudent(Scanner sc) {

        try {
            System.out.print("Enter Student ID to search: ");
            int id = Integer.parseInt(sc.nextLine());

            Student student = searchById(id);

            if (student != null) {
                System.out.println("Student found!");
                student.displayDetails();
            } else {
                System.out.println("Student not found!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID!");
        }
    }

    // 4. Update Student
    public void updateStudent(Scanner sc) {

        try {
            System.out.print("Enter Student ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            Student student = searchById(id);

            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            System.out.print("Enter new Name: ");
            String name = sc.nextLine();

            System.out.print("Enter new Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new Course: ");
            String course = sc.nextLine();

            System.out.print("Enter new Marks: ");
            double marks = Double.parseDouble(sc.nextLine());

            if (name.isBlank() || age <= 0 || course.isBlank()
                    || marks < 0 || marks > 100) {
                System.out.println("Invalid student details!");
                return;
            }

            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            student.setMarks(marks);
            StudentFileManager.saveStudents(students);

            System.out.println("Student updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values!");
        }
    }

    // 5. Delete Student
    public void deleteStudent(Scanner sc) {

        try {
            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            Student student = searchById(id);

            if (student != null) {
                students.remove(student);
                studentIds.remove(id);
                studentMap.remove(id);

                StudentFileManager.saveStudents(students);

                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID!");
        }
    }
    // 6. Performance Summary
    public void performanceSummary() {

        if (students.isEmpty()) {
            System.out.println("No student records available!");
            return;
        }

        double totalMarks = 0;
        double highestMarks = students.get(0).getMarks();
        double lowestMarks = students.get(0).getMarks();

        int passedStudents = 0;

        for (Student student : students) {

            double marks = student.getMarks();

            totalMarks += marks;

            if (marks > highestMarks) {
                highestMarks = marks;
            }

            if (marks < lowestMarks) {
                lowestMarks = marks;
            }

            if (marks >= 40) {
                passedStudents++;
            }
        }

        double average = totalMarks / students.size();

        System.out.println("\n===== PERFORMANCE SUMMARY =====");

        System.out.println("Total Students: " + students.size());
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highestMarks);
        System.out.println("Lowest Marks: " + lowestMarks);
        System.out.println("Passed Students: " + passedStudents);
        System.out.println("Failed Students: "
                + (students.size() - passedStudents));
    }
    // 7. Create Backup using Multithreading
    public void createBackup() {

        BackUpTask backupTask = new BackUpTask();

        Thread backupThread = new Thread(backupTask);

        backupThread.start();

        System.out.println("Backup process started in a separate thread...");
    }
}
