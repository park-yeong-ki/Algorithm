package bj1094;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();

        int cnt = 0;
        for (int i = 0; i < 9; i++) {
            if ((X & (1 << i)) != 0) cnt++;
        }

        System.out.println(cnt);
    }
}
