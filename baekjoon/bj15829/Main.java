package bj15829;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //문자열길이, 문자열 입력
        int L = sc.nextInt();
        String str = sc.next();
        int M = 1234567891;

        long sum = 0;
        long r = 1;
        //문자에 대한 아스키 코드를 이용한 후 주어진 식 사용 -> 자료형 범위를 고려해야한다.
        for (int i = 0; i < L; i++) {
            sum += (((int) str.charAt(i) - 96) * r) % M;
            r = (r * 31) % M;
        }

        //출력
        System.out.println(sum % M);
    }
}
