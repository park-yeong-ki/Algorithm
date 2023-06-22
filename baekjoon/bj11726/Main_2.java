package bj11726;

import java.util.Scanner;

public class Main_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int sqrt = (int) Math.sqrt(i);
            dp[i] = 1 + dp[i - sqrt * sqrt];
            for (int j = sqrt - 1; j >= 1; j--) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }

        System.out.println(dp[n]);
    }
}
