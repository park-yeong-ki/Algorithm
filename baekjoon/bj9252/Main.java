package bj9252;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        bw.write(dp[a.length][b.length] + "\n");

        int tA = a.length;
        int tB = b.length;

        Stack<Character> stack = new Stack<>();
        while (tA > 0 && tB > 0){
            if (a[tA-1] == b[tB-1]) {
                stack.push(a[tA - 1]);
                tA--;
                tB--;
            } else if (dp[tA][tB] == dp[tA-1][tB]) {
                tA--;
            } else if (dp[tA][tB] == dp[tA][tB - 1]) {
                tB--;
            }
        }

        while (!stack.isEmpty()) {
            bw.write(stack.pop());
        }
        bw.newLine();
        bw.close();
    }
}