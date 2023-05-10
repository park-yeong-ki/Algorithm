package bj11052;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] P = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            P[i] = sc.nextInt();
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                P[i] = Math.max(P[i], P[j] + P[i-j]);
            }
        }
        System.out.println(P[N]);
    }
}
