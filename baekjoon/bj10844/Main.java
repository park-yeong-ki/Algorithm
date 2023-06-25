package bj10844;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] dp = new int[n + 1][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        int modNum = 1000000000;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][j+1];
                else if (j == 9) dp[i][j] = dp[i - 1][j-1];
                else dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1];

                dp[i][j] %= modNum;
            }
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += dp[n][i];
            ans %= modNum;
        }

        System.out.println(ans);
    }
}
