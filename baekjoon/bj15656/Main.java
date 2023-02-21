package bj15656;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int M;
    static int[] arr;
    static int[] result;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //수열 입력
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //오름차순 정렬
        Arrays.sort(arr);

        //재귀함수 사용
        result = new int[N];
        perm(0);

        bw.close();
    }

    private static void perm(int idx) throws IOException {
        //기저조건
        if (idx == M) {
            //출력
            for (int i = 0; i < M; i++) {
                bw.write(result[i] + " ");
            }
            bw.newLine();

            return;
        }

        //유도부분 -> 중복 순열
        for (int i = 0; i < N; i++) {
            result[idx] = arr[i];
            perm(idx+1);
        }
    }
}