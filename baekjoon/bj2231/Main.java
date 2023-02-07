package bj2231;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //자연수 N입력
        int N = sc.nextInt();

        //최소값을 저장할 변수 선언
        int min = Integer.MAX_VALUE;

        //반복문을 통해 1부터 N까지 수를 전부 확인한다.
        for (int i = 1; i < N; i++) {
            String str = String.valueOf(i);
            //반복문을 통해서 분해합을 구한다.
            int sum = i;
            for (int j = 0; j < str.length(); j++) {
                sum += Character.getNumericValue(str.charAt(j));
            }

            //생성자인게 확인될 경우 가장 작은 생성자를 찾는다.
            if (sum == N) {
                min = Math.min(min, i);
            }
        }

        //출력
        if (min != Integer.MAX_VALUE) {
            System.out.println(min);
        } else {
            System.out.println(0);
        }

    }
}
