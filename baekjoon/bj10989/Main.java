package bj10989;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N 입력
        int N = Integer.parseInt(br.readLine());

        //입력 받을 배열 생성
        int[] arr = new int[N];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //카운트 배열 생성
        int[] count = new int[10001];

        //카운트 배열 인덱스에 arr 요소 값을 넣음
        for (int i = 0; i < N; i++) {
            count[arr[i]]++;
        }

        //카운트된 숫자를 arr에 순서대로 대입
        int size = 0;
        for (int i = 1; i < 10001; i++) {
            if (count[i] != 0) {
                //중복된 숫자도 중복된 횟수만큼 출력될 수 있도록 반복문 추가
                for (int j = 0; j < count[i]; j++) {
                    arr[size++] = i;
                }
            }
        }

        //출력
        for (int i = 0; i < size; i++) {
            bw.write(String.valueOf(arr[i]));
            bw.newLine();
        }
        bw.flush();

    }
}
