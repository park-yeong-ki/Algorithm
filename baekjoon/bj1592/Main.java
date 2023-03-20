package bj1592;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N, M, L
        int N = sc.nextInt();
        int M = sc.nextInt();
        int L = sc.nextInt();

        //공을 받은 횟수 저장
        int[] count = new int[N];

        int i = 0;
        count[i]++;

        //한 사람이 공을 M번 받을 때까지 반복
        int result = 0;
        while (count[i] < M) {
            //짝수 홀수 구분하여 조건설정
            if (count[i] % 2 == 1) {
                i = (i + L) % N;
            } else {
                i = (N + i - L) % N;
            }

            count[i]++;
            result++;
        }

        //결과 출력
        System.out.println(result);
    }
}
