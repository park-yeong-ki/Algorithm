package bj16927;

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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (cnt < N/2 && cnt < M/2) {
            int mod = (N-(2*cnt)) * 2 + (M-(2*cnt)-2) * 2;

            for (int r = 0; r < R%mod; r++) {
                int startC = 0 + cnt;
                int endC = M - 1 - cnt;
                int startR = 0 + cnt;
                int endR = N - 1 - cnt;

                int temp = arr[startR][startC];
                for (int i = startC; i < endC; i++) {
                    arr[startR][i] = arr[startR][i + 1];
                }
                for (int i = startR; i < endR; i++) {
                    arr[i][endC] = arr[i + 1][endC];
                }
                for (int i = endC; i >= startC + 1; i--) {
                    arr[endR][i] = arr[endR][i - 1];
                }
                for (int i = endR; i >= startR + 1; i--) {
                    arr[i][startC] = arr[i - 1][startC];
                }
                arr[startR + 1][startC] = temp;
            }

            cnt++;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.newLine();
        }
        bw.close();
    }
}