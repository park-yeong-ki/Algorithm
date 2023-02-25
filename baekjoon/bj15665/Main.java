package bj15665;

import java.io.*;
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

        //N, M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //N개의 수 입력
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //수 오름차순 정렬
        Arrays.sort(arr);

        //순열 사용
        result = new int[M];
        perm(0);

        bw.close();
    }

    static void perm(int idx) throws IOException {
        //기저조건
        if (idx == M) {
            //출력
            for (int i = 0; i < M; i++) {
                bw.write(result[i] + " ");
            }
            bw.newLine();

            return;
        }

        //유도부분 -> 중복순열 구현
        int temp = 0;
        for (int i = 0; i < N; i++) {
            //이미 자리수에 들어간적 있는 숫자면 반복문 생략
            if (temp == arr[i]) continue;
            result[idx] = arr[i];
            perm(idx+1);
            temp = result[idx];
        }
    }
}
