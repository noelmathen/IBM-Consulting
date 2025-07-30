import java.util.Scanner;

public class Problem3 {
    public static boolean isPalindrome(int num) {
        int original = num;
        int reversed = 0;
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return original == reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number (>=11): ");
        int n = sc.nextInt();

        if (n < 11) {
            System.out.println("Invalid Input");
            return;
        }

        for (int i = 11; i <= n; i++) {
            if (isPalindrome(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        sc.close();
    }
}
