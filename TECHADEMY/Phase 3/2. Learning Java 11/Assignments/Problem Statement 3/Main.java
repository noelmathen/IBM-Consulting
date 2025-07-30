    import java.util.*;

    abstract class Employee implements Comparable<Employee> {
        private String employeeId;
        private String employeeName;
        private String department;

        public Employee(String employeeId, String employeeName, String department) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.department = department;
        }

        // Getters and setters
        public String getEmployeeId() { return employeeId; }
        public String getEmployeeName() { return employeeName; }
        public String getDepartment() { return department; }
        public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
        public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
        public void setDepartment(String department) { this.department = department; }

        // Abstract method to calculate tax
        public abstract double calculateTax();

        @Override
        public int compareTo(Employee other) {
            return this.employeeName.compareToIgnoreCase(other.employeeName);
        }

        @Override
        public String toString() {
            return String.format("%-10s %-20s %-15s", employeeId, employeeName, department);
        }
    }

    class PermanentEmployee extends Employee {
        private double monthlySalary;
        private double pf;   // 15% of monthlySalary
        private double tax;  // 10% of annual salary

        public PermanentEmployee(String employeeId, String employeeName, String department, double monthlySalary) {
            super(employeeId, employeeName, department);
            this.monthlySalary = monthlySalary;
            this.pf = monthlySalary * 0.15;
            this.tax = calculateTax();
        }

        public double getMonthlySalary() { return monthlySalary; }
        public double getPf() { return pf; }
        public double getTax() { return tax; }

        public void setMonthlySalary(double monthlySalary) {
            this.monthlySalary = monthlySalary;
            this.pf = monthlySalary * 0.15;
            this.tax = calculateTax();
        }

        @Override
        public double calculateTax() {
            // 10% of annual salary
            return monthlySalary * 12 * 0.10;
        }

        @Override
        public String toString() {
            return String.format("%-10s %-20s %-15s Monthly Salary: %.2f PF: %.2f Tax: %.2f",
                    getEmployeeId(), getEmployeeName(), getDepartment(),
                    monthlySalary, pf, tax);
        }
    }

    class ContractualEmployee extends Employee {
        private int contractPeriod; // in months
        private double contractAmount;
        private double tax; // 10% of contractAmount

        public ContractualEmployee(String employeeId, String employeeName, String department, int contractPeriod, double contractAmount) {
            super(employeeId, employeeName, department);
            this.contractPeriod = contractPeriod;
            this.contractAmount = contractAmount;
            this.tax = calculateTax();
        }

        public int getContractPeriod() { return contractPeriod; }
        public double getContractAmount() { return contractAmount; }
        public double getTax() { return tax; }

        public void setContractPeriod(int contractPeriod) {
            this.contractPeriod = contractPeriod;
        }

        public void setContractAmount(double contractAmount) {
            this.contractAmount = contractAmount;
            this.tax = calculateTax();
        }

        @Override
        public double calculateTax() {
            return contractAmount * 0.10;
        }

        @Override
        public String toString() {
            return String.format("%-10s %-20s %-15s Contract Period: %d Contract Amount: %.2f Tax: %.2f",
                    getEmployeeId(), getEmployeeName(), getDepartment(),
                    contractPeriod, contractAmount, tax);
        }
    }

    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter number of employees: ");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline

            Employee[] employees = new Employee[n];

            System.out.println("Enter details for each employee in comma separated format:");
            System.out.println("For Permanent Employee: employeeId,employeeName,department,monthlySalary");
            System.out.println("For Contractual Employee: employeeId,employeeName,department,contractPeriod,contractAmount");
            System.out.println("Example: P101,Eric Miller,Finance,35000.00");
            System.out.println("Example: C1002,Roger Steven,Sales,5,750000.00");

            for (int i = 0; i < n; i++) {
                System.out.print("Employee " + (i + 1) + ": ");
                String input = sc.nextLine();
                String[] parts = input.split(",");

                try {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String dept = parts[2].trim();

                    if (id.startsWith("P")) {
                        // Permanent employee, expect 4 parts
                        double monthlySalary = Double.parseDouble(parts[3].trim());
                        employees[i] = new PermanentEmployee(id, name, dept, monthlySalary);
                    } else if (id.startsWith("C")) {
                        // Contractual employee, expect 5 parts
                        int contractPeriod = Integer.parseInt(parts[3].trim());
                        double contractAmount = Double.parseDouble(parts[4].trim());
                        employees[i] = new ContractualEmployee(id, name, dept, contractPeriod, contractAmount);
                    } else {
                        System.out.println("Invalid employee id format. Skipping entry.");
                        i--;
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input format or data. Please enter again.");
                    i--;
                }
            }

            // Sort by employee name
            Arrays.sort(employees);

            System.out.println("\nEmployee details sorted by name:\n");

            for (Employee emp : employees) {
                System.out.println(emp);
            }

            sc.close();
        }
    }
