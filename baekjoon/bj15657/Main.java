package bj15657;

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
    static int[] pArr;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //N개의 배열 입력
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //오름차순 정렬
        Arrays.sort(arr);

        //재귀함수 사용
        pArr = new int[N];
        comb(0, 0);

        bw.close();
    }

    //중복조합 구현
    static void comb(int start, int cnt) throws IOException {
        //기저조건
        if (cnt == M) {
            //출력
            for (int i = 0; i < M; i++) {
                bw.write(pArr[i] + " ");
            }
            bw.newLine();

            return;
        }

        //유도부분
        for (int i = start; i < N; i++) {
            pArr[cnt] = arr[i];
            comb(i, cnt+1);
        }
    }
}