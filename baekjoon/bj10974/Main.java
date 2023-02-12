package bj10974;

import java.io.*;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] isSelected;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        //N입력
        N = Integer.parseInt(br.readLine());

        //1부터 N까지 수를 담을 배열 생성
        arr = new int[N];

        //이미 사용한 값인지 체크할 배열 생성
        isSelected = new boolean[N];

        //재귀함수 사용
        perm(0);
        bw.close();
    }

    //재귀함수 생성 -> 순열 구현
    public static void perm(int cnt) throws IOException {
        //기저조건 -> N개가 다 뽑힌 경우
        if (cnt == N) {
            //순열 출력
            for (int i = 0; i < N; i++) {
                bw.write(arr[i] + " ");
            }
            bw.newLine();
            return;
        }

        //유도부분
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            arr[cnt] = i+1;
            isSelected[i] = true;
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}
