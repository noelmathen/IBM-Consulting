import java.util.Arrays;
import java.util.Scanner;

class Applicant implements Comparable<Applicant> {
    private String name;
    private int subject1;
    private int subject2;
    private int subject3;
    private int total;
    private double percentage;

    public Applicant(String name, int subject1, int subject2, int subject3) {
        this.name = name;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.total = totalCalculation();
        this.percentage = percentageCalculation();
    }

    public String getName() { return name; }
    public int getSubject1() { return subject1; }
    public int getSubject2() { return subject2; }
    public int getSubject3() { return subject3; }
    public int getTotal() { return total; }
    public double getPercentage() { return percentage; }

    public void setName(String name) { this.name = name; }
    public void setSubject1(int subject1) { this.subject1 = subject1; }
    public void setSubject2(int subject2) { this.subject2 = subject2; }
    public void setSubject3(int subject3) { this.subject3 = subject3; }

    public int totalCalculation() {
        int sum = subject1 + subject2 + subject3;
        // Return 0 if any subject < 50 (fail)
        if (subject1 < 50 || subject2 < 50 || subject3 < 50) {
            return 0;
        }
        return sum;
    }

    public double percentageCalculation() {
        if (total == 0) return 0;
        return (total / 300.0) * 100;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-5d %-5d %-5d %-10d %-10.2f",
                name, subject1, subject2, subject3, total, percentage);
    }

    // For sorting by name ascending
    @Override
    public int compareTo(Applicant other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of applicants: ");
        int n = sc.nextInt();
        sc.nextLine();  // consume leftover newline

        Applicant[] applicants = new Applicant[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter details for applicant " + (i + 1) + " (Name,subj1,subj2,subj3): ");
            String input = sc.nextLine();
            String[] parts = input.split(",");

            if (parts.length != 4) {
                System.out.println("Invalid input format, please enter again.");
                i--;
                continue;
            }

            String name = parts[0];
            try {
                int sub1 = Integer.parseInt(parts[1]);
                int sub2 = Integer.parseInt(parts[2]);
                int sub3 = Integer.parseInt(parts[3]);
                applicants[i] = new Applicant(name, sub1, sub2, sub3);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks input, please enter again.");
                i--;
            }
        }

        // Filter applicants who passed (total > 0)
        Applicant[] passedApplicants = Arrays.stream(applicants)
                .filter(a -> a.getTotal() > 0)
                .toArray(Applicant[]::new);

        // Sort by name ascending
        Arrays.sort(passedApplicants);

        // Print header
        System.out.printf("%-10s %-5s %-5s %-5s %-10s %-10s\n",
                "Name", "S1", "S2", "S3", "Total", "Percentage");

        // Print passed applicants
        for (Applicant a : passedApplicants) {
            System.out.println(a);
        }

        sc.close();
    }
}
