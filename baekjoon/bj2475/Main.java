package bj2475;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 선언
        Scanner sc = new Scanner(System.in);

        //입력받은 각 숫자를 제곱한 수의 합
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int n = sc.nextInt();
            sum += n*n;
        }

        //10으로 나눈 나머지를 출력
        System.out.println(sum%10);
    }
}
