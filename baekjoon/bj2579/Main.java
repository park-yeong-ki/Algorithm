package bj2579;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        int[][] dp = new int[n + 1][3];
        dp[1][1] = arr[1];
        dp[1][2] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + arr[i];
            dp[i][2] = dp[i - 1][1] + arr[i];
        }

        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}
