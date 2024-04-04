import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int n;
        int[] dp;
        for (int test_case = 0; test_case < T; test_case++) {
            n = Integer.parseInt(br.readLine());
            dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                dp[i] += dp[i - 1];
                if (i-2 >= 0) dp[i] += dp[i - 2];
                if (i-3 >= 0) dp[i] += dp[i - 3];
            }

            bw.write(dp[n] + "\n");
        }
        bw.close();
    }
}