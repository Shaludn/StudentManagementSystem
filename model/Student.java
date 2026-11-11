
package StudentManagementSystem.model;

public class Student extends Person {

    private int studentId;
    private String course;
    private double marks;

    private static int studentCount = 0;


    public Student(int studentId, String name, int age,
                   String course, double marks) {

        super(name, age);

        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid Student ID!");
        }

        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100!");
        }

        this.studentId = studentId;
        this.course = course;
        this.marks = marks;

        studentCount++;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        } else {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100!"
            );
        }
    }

    public static int getStudentCount() {
        return studentCount;
    }

    @Override
    public void displayDetails() {

        System.out.println("\nStudent ID: " + studentId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
    }
}
