package bj2742;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 선언
        Scanner sc = new Scanner(System.in);

        //N입력
        int N = sc.nextInt();

        //출력
        for (int i = N; i > 0; i--) {
            System.out.println(i);
        }
    }
}
