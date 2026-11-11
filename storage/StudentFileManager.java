
package StudentManagementSystem.storage;

import StudentManagementSystem.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileManager {

    private static final String FILE_NAME = "student_records.txt";

    // Save all student records to file
    public static void saveStudents(List<Student> students) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student student : students) {

                writer.write(
                        student.getStudentId() + "|" +
                                student.getName() + "|" +
                                student.getAge() + "|" +
                                student.getCourse() + "|" +
                                student.getMarks()
                );

                writer.newLine();
            }

            System.out.println("Student records saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving student records: "
                    + e.getMessage());
        }
    }

    // Load student records from file
    public static ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No previous student records found.");
            return students;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] data = line.split("\\|", -1);

                    if (data.length != 5) {
                        System.out.println("Skipping invalid record: " + line);
                        continue;
                    }

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String course = data[3];
                    double marks = Double.parseDouble(data[4]);

                    if (id <= 0 || age <= 0 ||
                            marks < 0 || marks > 100 ||
                            name.isBlank() || course.isBlank()) {
                        System.out.println("Skipping invalid record: " + line);
                        continue;
                    }

                    students.add(
                            new Student(id, name, age, course, marks)
                    );

                } catch (NumberFormatException e) {
                    System.out.println("Skipping corrupted record: " + line);
                }
            }

            System.out.println("Student records loaded successfully!");

        } catch (IOException e) {
            System.out.println("Error loading student records: "
                    + e.getMessage());
        }

        return students;
    }
}
