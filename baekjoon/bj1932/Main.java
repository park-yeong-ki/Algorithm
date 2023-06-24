package bj1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
                if (!st.hasMoreTokens()) break;
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i][j] + dp[i + 1][j], dp[i][j] + dp[i + 1][j + 1]);
            }
        }

        System.out.println(dp[1][1]);
    }
}
