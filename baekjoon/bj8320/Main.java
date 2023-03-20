package bj8320;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //정사각형 n개
        int n = sc.nextInt();

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (i * j <= n) count++;
                else break;
            }
        }

        //출력
        System.out.println(count);
    }
}
