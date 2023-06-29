package bj9184;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int a, b, c;
        int[][][] dp = new int[21][21][21];
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 20; k++) {
                    if (i == 0 || j == 0 || k == 0)
                        dp[i][j][k] = 1;
                    else if (i < j && j < k)
                        dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1] - dp[i][j - 1][k];
                    else
                        dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k] + dp[i-1][j][k-1] - dp[i-1][j-1][k-1];
                }
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == b && b == c && c == -1) break;

            int ans = 0;
            if (a <= 0 || b <= 0 || c <= 0) ans = 1;
            else if (a > 20 || b > 20 || c > 20) ans = dp[20][20][20];
            else ans = dp[a][b][c];

            sb.append("w(" + a + ", " + b + ", " + c + ") = " + ans + "\n");
        }
        System.out.print(sb);
    }
}
