package bj11651;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //점 개수 N 입력
        int N = Integer.parseInt(br.readLine());

        //입력받을 배열 생성
        int[][] arr = new int[N][2];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //Arrays.sort, Comparator 사용
        Arrays.sort(arr, Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt((int[] o) -> o[0]));

        //출력
        for (int i = 0; i < N; i++) {
            bw.write(arr[i][0] + " " + arr[i][1] + "\n");
        }
        bw.flush();
    }
}
