package bj15651;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int M;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        //N, M입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //재귀함수 사용
        arr = new int[M];
        isSelected = new boolean[N];
        perm(0);

        bw.close();
    }

    //재귀함수 생성
    static void perm(int cnt) throws IOException {
        //기저조건 -> M개가 뽑히면 종료
        if (cnt == M) {
            //출력
            for (int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.newLine();
            return;
        }

        //유도부분 -> 중복순열 구현
        for (int i = 0; i < N; i++) {
            arr[cnt] = i+1;
            perm(cnt + 1);
        }
    }
}
