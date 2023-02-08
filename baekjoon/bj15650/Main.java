package bj15650;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //N, M 입력
        int N = sc.nextInt();
        int M = sc.nextInt();

        //길이가 M인 배열 생성
        arr = new int[M];

        //조합 문제
        //재귀함수 호출
        sequence(1, 0, N, M);
    }

    //재귀함수 생성
    static void sequence(int start, int idx, int N, int M){
        //기저조건: M만큼 고른 경우 -> idx가 M과 같은 경우
        if (idx == M) {
            //arr 요소 출력
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        //유도부분
        for (int i = start; i <= N; i++) {
            arr[idx] = i;
            //arr의 idx행에 값을 넣고 다음 idx행에 값을 넣기 위해 재귀호출
            sequence(i+1, idx+1, N, M);
        }
    }
}
