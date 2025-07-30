import java.io.*;
import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        // 1. Demonstrate ArrayList, LinkedList, HashMap, HashSet
        ArrayList<Student> arrayList = new ArrayList<>();
        LinkedList<Student> linkedList = new LinkedList<>();
        HashMap<Integer, Student> hashMap = new HashMap<>();
        HashSet<Student> hashSet = new HashSet<>();

        try {
            // Adding students
            Student s1 = new Student(1, "Alice");
            Student s2 = new Student(2, "Bob");
            Student s3 = new Student(3, "Charlie");

            arrayList.add(s1);
            arrayList.add(s2);
            arrayList.add(s3);

            linkedList.addAll(arrayList);
            for (Student s : arrayList) {
                hashMap.put(s.getId(), s);
                hashSet.add(s);
            }

            // CRUD Operations in ArrayList
            System.out.println("Initial ArrayList: " + arrayList);

            // Read by index
            System.out.println("Student at index 1: " + arrayList.get(1));

            // Update
            arrayList.get(1).setName("Bobby");
            System.out.println("After update: " + arrayList);

            // Delete
            arrayList.remove(0);
            System.out.println("After deletion: " + arrayList);

            // Exception handling - accessing invalid index
            try {
                System.out.println(arrayList.get(10));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Caught Exception: " + e);
            }

            // NullPointerException example
            Student nullStudent = null;
            try {
                System.out.println(nullStudent.getName());
            } catch (NullPointerException e) {
                System.out.println("Caught Exception: " + e);
            }

            // IllegalArgumentException example
            try {
                Student s4 = new Student(4, "");
            } catch (IllegalArgumentException e) {
                System.out.println("Caught Exception: " + e);
            }

            // Custom Exception usage
            try {
                validateStudentData(new Student(5, null));
            } catch (InvalidStudentDataException e) {
                System.out.println("Caught Custom Exception: " + e.getMessage());
            }

            // Reading data from file and handling exceptions
            System.out.println("\nReading students from file:");
            List<Student> fileStudents = readStudentsFromFile("students.txt");
            for (Student s : fileStudents) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to validate student data using custom exception
    public static void validateStudentData(Student student) throws InvalidStudentDataException {
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new InvalidStudentDataException("Student name is invalid");
        }
    }

    // Read students from file and handle file related exceptions
    public static List<Student> readStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 2) {
                        System.out.println("Skipping invalid line " + lineNum);
                        lineNum++;
                        continue;
                    }
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    students.add(new Student(id, name));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format on line " + lineNum);
                }
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return students;
    }
}
