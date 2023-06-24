package bj24416;

import java.util.Scanner;

public class Main {
    static int cnt1, cnt2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;

        fib(n);
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            cnt2++;
        }

        System.out.println(cnt1 + " " + cnt2);
    }

    static int fib(int num){
        if (num == 1 || num == 2) {
            cnt1++;
            return 1;
        }
        return fib(num - 1) + fib(num - 2);
    }
}
