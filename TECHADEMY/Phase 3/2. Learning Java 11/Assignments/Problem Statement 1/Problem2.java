import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a positive number n: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Wrong input");
            return;
        }

        for (int i = 1; i <= n; i++) {
            int value = i * (i + 1);  // 1*2=2, 2*3=6, 3*4=12, 4*5=20, ...
            System.out.print(value + " ");
        }
        System.out.println();
        sc.close();
    }
}
