package bj15655;

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
    static int[] cArr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N,M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //N개의 수를 담을 배열 생성 및 요소 입력
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //수열 정렬
        Arrays.sort(arr);

        //재귀함수 사용
        cArr = new int[M];
        comb(0, 0);

        bw.close();
    }

    //재귀함수 생성
    static void comb(int start, int cnt) throws IOException {
        //기저조건 -> M개를 뽑으면 종료
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                bw.write(cArr[i] + " ");
            }
            bw.newLine();

            return;
        }

        //유도부분 -> 조합 구현
        for (int i = start; i < N; i++) {
            cArr[cnt] = arr[i];
            comb(i+1, cnt+1);
        }
    }
}