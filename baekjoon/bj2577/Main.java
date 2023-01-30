package bj2577;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //자연수 입력
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        //곱 구하기
        String multiply = String.valueOf(A*B*C);

        //카운트 배열 생성
        int[] count = new int[10];

        //카운트 배열 요소 입력
        for (int i = 0; i < multiply.length(); i++) {
            count[Character.getNumericValue(multiply.charAt(i))]++;
        }

        //출력
        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
    }
}
