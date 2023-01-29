package bj11650;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader, BufferedWriter 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //점의 개수 입력
        int N = Integer.parseInt(br.readLine());

        //Nx2 배열생성
        int[][] arr = new int[N][2];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //x좌표를 기준으로 정렬 후 같은 경우 y좌표가 작은 값을 앞으로 정렬
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIdx][0] > arr[j][0]) {
                    minIdx = j;
                } else if (arr[minIdx][0] == arr[j][0]) {
                    if (arr[minIdx][1] > arr[j][1]) {
                        minIdx = j;
                    }
                }
            }
            //정렬 순서 변경
            int[] temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }

        //출력
        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i][0] + " " + arr[i][1] + "\n");
        }
        bw.flush();

    }
}
