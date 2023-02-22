package bj15663;

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
    static boolean[] isSelected;
    static int[] pArr;
    static int[] arr;
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

        //오름차순 정렬
        Arrays.sort(arr);

        //재귀함수 사용
        isSelected = new boolean[N];
        pArr = new int[M];
        perm(0);

        bw.close();
    }

    //순열구현에서 중복된 경우를 제거
    static void perm(int idx) throws IOException {
        //기저조건
        if (idx == M) {
            //출력
            for (int i = 0; i < M; i++) {
                bw.write(pArr[i] + " ");
            }
            bw.newLine();

            return;
        }

        //유도부분 --> temp 변수를 통해서 중복값을 제거한다.
        int temp = 0;
        for (int i = 0; i < N; i++) {
            if(isSelected[i]) continue;
            if(temp == arr[i]) continue;
            pArr[idx] = arr[i];
            temp = arr[i];
            isSelected[i] = true;
            perm(idx+1);
            isSelected[i] = false;
        }
    }
}