package bj2751;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N 입력
        int N = Integer.parseInt(br.readLine());

        //입력받을 배열 생성
        int[] arr = new int[N];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //수의 범위가 -1000000부터 1000000까지인 정수이므로 카운트 배열의 크기를 2000001로 설정
        int[] count = new int[2000001];

        //주어진 조건에서 음의 최대값인 -1000000을 0 인덱스로 설정하도록 값을 더해줌
        for (int i = 0; i < N; i++) {
            count[arr[i] + 1000000]++;
        }

        //카운트가 확인되면 arr 행렬에 순차적으로 값을 대입한다.
        int size = 0;
        for (int i = 0; i < 2000001; i++) {
            if (count[i] != 0) {
                arr[size++] = i - 1000000;
            }
        }

        //출력
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(arr[i]));
            bw.newLine();
        }

        bw.flush();
    }
}
