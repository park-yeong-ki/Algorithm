package bj2581;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 선언
        Scanner sc = new Scanner(System.in);

        //N,M 입력
        int M = sc.nextInt();
        int N = sc.nextInt();

        //N+1 크기의 카운트 배열 생성
        int[] count = new int[N+1];
        for (int i = M; i <= N; i++) {
            // i의 제곱근까지 반복하여 i의 약수를 찾는다.
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0 && i != 1) {
                    count[i]++;
                }
            }
        }


        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            // i가 소수일 경우 합과 최소값을 구함
            if (count[i] == 1) {
                sum += i;
                min = Math.min(min, i);
            }
        }

        //소수가 없을 경우는 -1 출력
        if (sum == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }
}
