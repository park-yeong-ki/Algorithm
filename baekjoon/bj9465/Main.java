package bj9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[3][n + 1];
            StringTokenizer st;
            for (int i = 1; i <= 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 2; i <= n; i++) {
                dp[1][i] += Math.max(dp[2][i - 1], dp[2][i - 2]);
                dp[2][i] += Math.max(dp[1][i - 1], dp[1][i - 2]);
            }

            sb.append(Math.max(dp[1][n], dp[2][n]) + "\n");
        }
        System.out.print(sb);
    }
}
