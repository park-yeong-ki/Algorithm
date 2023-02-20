package bj1654;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //K, N 입력
        int K = sc.nextInt();
        int N = sc.nextInt();

        //K개의 랜선 입력
        int[] arr = new int[K];
        //최대값, 최소값 구하기
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
        }

        //최대값이 정답인 경우를 생각해서 처음 맥스값에 1을 더해준다
        max++;
        long min = 1;
        while (min < max) {
            //이분탐색으로 값을 구한다.
            long middle = (max + min)/2;

            //랜선 개수의 합을 구한다.
            long sum = 0;
            for (int i = 0; i < K; i++) {
                sum += arr[i]/middle;
            }

            //합을 랜선 개수의 크기와 비교하여 각각의 경우를 설정한다.
            if (sum >= N) {
                min = middle+1;
            } else {
                max = middle;
            }
        }

        //출력
        System.out.println(min-1);
    }
}