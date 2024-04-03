import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 2];
        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= n; i+=2) {
            dp[i] = 4 * dp[i - 2] - dp[i - 4];
        }

        System.out.println(dp[n]);
    }
}