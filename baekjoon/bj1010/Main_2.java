package bj1010;

import java.util.Scanner;

public class Main_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[31][31];
        for (int i = 1; i <= 30; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            sb.append(dp[M][N] + "\n");
        }
        System.out.print(sb);
    }
}
