
package StudentManagementSystem;

import StudentManagementSystem.service.StudentService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create StudentService object
        StudentService service = new StudentService();

        boolean running = true;

        System.out.println("==================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        while (running) {

            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Performance Summary");
            System.out.println("7. Create Backup");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        service.addStudent(sc);
                        break;

                    case 2:
                        service.viewStudents();
                        break;

                    case 3:
                        service.searchStudent(sc);
                        break;

                    case 4:
                        service.updateStudent(sc);
                        break;

                    case 5:
                        service.deleteStudent(sc);
                        break;

                    case 6:
                        service.performanceSummary();
                        break;

                    case 7:
                        service.createBackup();
                        break;

                    case 0:
                        running = false;
                        System.out.println("Thank you for using Student Management System!");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid menu option!");
            }
        }

        sc.close();
    }
}
