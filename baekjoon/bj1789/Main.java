package bj1789;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();

        for (long i = 1; i <= N; i++) {
            if (N < 2 * i + 1) {
                System.out.println(i);
                break;
            }

            N -= i;
        }
    }
}
