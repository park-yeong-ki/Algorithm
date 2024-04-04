import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 2];
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i%2 == 0) dp[i] = Math.min(dp[i], dp[i / 2]);
            if (i%3 == 0) dp[i] = Math.min(dp[i], dp[i / 3]);
            dp[i] += 1;
        }

        System.out.println(dp[n]);
        int min;
        while (true) {
            System.out.print(n + " ");
            if (n == 1) break;

            min = n - 1;
            if (n % 2 == 0 && dp[n / 2] < dp[min]) {
                min = n / 2;
            }
            if (n % 3 == 0 && dp[n / 3] < dp[min]) {
                min = n / 3;
            }
            n = min;
        }
    }
}