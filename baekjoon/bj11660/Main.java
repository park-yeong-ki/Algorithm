package bj11660;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //N, M입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //N+1xN+1 배열 생성
        int[][] nArr = new int[N+1][N+1];

        //nArr 요소 입력 -> 행별 누적합을 요소로 입력함
        for (int i = 1; i < nArr.length; i++) {
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < nArr.length; j++) {
                sum += Integer.parseInt(st.nextToken());
                nArr[i][j] = sum;
            }
        }

        //x1, y1, x2, y2 배열 생성
        int[][] mArr = new int[M][4];

        //mArr 요소 입력
        for (int i = 0; i < mArr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                mArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //주어진 행의 범위를 이동하면서 각 열에 해당하는 누적합의 차를 더한 후 출력
        for (int i = 0; i < mArr.length; i++) {
            int sum = 0;
            for (int j = mArr[i][0]; j <= mArr[i][2]; j++) {
                sum += nArr[j][mArr[i][3]] - nArr[j][mArr[i][1]-1];
            }
            bw.write(sum + "\n");
        }
        bw.close();
    }
}