package bj1149;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0] = arr[0].clone();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k) continue;
                    dp[i][j] = Math.min(dp[i][j], arr[i][j] + dp[i-1][k]);
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            minCost = Math.min(minCost, dp[N - 1][i]);
        }
        System.out.println(minCost);
    }
}
