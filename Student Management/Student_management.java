package project1;

import java.util.*;
import java.io.*;

class Student {
    int id;
    String name;
    int marks;

    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 50) return "C";
        else return "Fail";
    }
}

public class Student_management {

    static ArrayList<Student> list = new ArrayList<>();
    static final String FILE = "students.txt";

    static void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2])
                ));
            }
            br.close();
        } catch (Exception e) {
        }
    }

    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (Student s : list) {
                bw.write(s.id + "," + s.name + "," + s.marks);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    static boolean idExists(int id) {
        for (Student s : list) {
            if (s.id == id) return true;
        }
        return false;
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (idExists(id)) {
            System.out.println("ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        list.add(new Student(id, name, marks));
        saveToFile();

        System.out.println("Student added!");
    }

    static void updateStudent(Scanner sc) {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : list) {
            if (s.id == id) {
                System.out.print("Enter new name: ");
                s.name = sc.nextLine();

                System.out.print("Enter new marks: ");
                s.marks = sc.nextInt();
                sc.nextLine();

                saveToFile();
                System.out.println("Updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = list.removeIf(s -> s.id == id);

        if (removed) {
            saveToFile();
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : list) {
            if (s.id == id) {
                System.out.println(s.id + " | " + s.name + " | " + s.marks + " | Grade: " + s.getGrade());
                return;
            }
        }

        System.out.println("Student not found.");
    }

    static void viewStudents() {
        if (list.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student s : list) {
            System.out.println(s.id + " | " + s.name + " | " + s.marks + " | " + s.getGrade());
        }
    }

    static void sortStudents() {
        list.sort((a, b) -> {
            if (b.marks != a.marks) return b.marks - a.marks;
            return a.name.compareToIgnoreCase(b.name);
        });

        System.out.println("\nSorted List:");
        viewStudents();
    }

    static void showTopper() {
        if (list.isEmpty()) {
            System.out.println("No data.");
            return;
        }

        Student top = Collections.max(list, Comparator.comparingInt(s -> s.marks));
        System.out.println("Topper: " + top.name + " (" + top.marks + ")");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        loadFromFile();

        while (true) {
            System.out.println("\n===== STUDENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. View Students");
            System.out.println("6. Sort Students");
            System.out.println("7. Show Topper");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input!");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: updateStudent(sc); break;
                case 3: deleteStudent(sc); break;
                case 4: searchStudent(sc); break;
                case 5: viewStudents(); break;
                case 6: sortStudents(); break;
                case 7: showTopper(); break;
                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}