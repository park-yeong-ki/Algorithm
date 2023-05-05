package bj1309;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[N + 2];
        dp[1] = 3;
        dp[2] = 7;
        for (int i = 3; i <= N; i++) {
            dp[i] = 2 * dp[i - 1] + dp[i - 2];
            dp[i] %= 9901;
        }
        System.out.println(dp[N]);
    }
}