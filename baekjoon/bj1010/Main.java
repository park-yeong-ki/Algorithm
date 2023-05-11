package bj1010;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            long result = 1;
            for (int j = 0; j < N; j++) {
                result *= M - j;
                result /= j+1;
            }

            sb.append(result + "\n");
        }
        System.out.print(sb);
    }
}
