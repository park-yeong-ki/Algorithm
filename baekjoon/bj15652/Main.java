package bj15652;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //재귀함수 사용
        arr = new int[M];
        comb(1, 0);

        bw.close();
    }
    //재귀함수 생성
    static void comb(int start, int idx) throws IOException {
        //기저조건 -> M개를 뽑으면 종료
        if (idx == M) {
            //출력
            for (int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.newLine();

            return;
        }

        //유도부분 -> 중복조합 구현
        for (int i = start; i <= N; i++) {
            arr[idx] = i;
            comb(i, idx + 1);
        }
    }
}
