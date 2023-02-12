package bj1182;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int S;
    static int[] arr;
    static int[] part;
    static int count;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N, S 입력
        N = sc.nextInt();
        S = sc.nextInt();

        //N개의 정수를 담을 배열 생성 및 요소 입력
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        //1개부터 N개를 뽑는 조합을 반복문을 통해 구현 -> 부분집합과 같음
        for (int i = 1; i <= N; i++) {
            part = new int[i];
            comb(0, 0);
        }

        //출력
        System.out.println(count);
    }

    //재귀함수 생성
    public static void comb(int start, int idx) {
        //기저조건
        if (idx == part.length) {
            //조합의 합 구하기
            int sum =0;
            for (int i = 0; i < part.length; i++) {
                sum += part[i];
            }

            //S와 일치하는 합인지 체크
            if (sum == S) {
                count++;
            }

            return;
        }

        //유도부분
        for (int i = start; i < N; i++) {
            part[idx] = arr[i];
            //재귀호출
            comb(i+1, idx+1);
        }
    }
}
