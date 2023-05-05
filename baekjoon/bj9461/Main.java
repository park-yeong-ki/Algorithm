package bj9461;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            long[] dp = new long[N+1];
            for (int j = 1; j <= N; j++) {
                if (j <= 3) dp[j] = 1;
                else dp[j] = dp[j - 3] + dp[j - 2];
            }
            sb.append(dp[N]+"\n");
        }
        System.out.print(sb);
    }
}
