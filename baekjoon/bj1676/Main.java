package bj1676;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int num_2 = 0;
        int num_5 = 0;
        for (int i = 1; i <= N; i++) {
            int num2 = i;
            while (num2 % 2 == 0) {
                num2 /= 2;
                num_2++;
            }

            int num5 = i;
            while (num5 % 5 == 0) {
                num5 /= 5;
                num_5++;
            }
        }

        System.out.println(Math.min(num_2, num_5));
    }
}
