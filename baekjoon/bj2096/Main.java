package bj2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];

        for (int i = 0; i < 3; i++) {
            maxDp[0][i] = arr[0][i];
            minDp[0][i] = arr[0][i];
        }

        int[] dx = {-1, 0, 1};
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                minDp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (j + dx[k] < 0 || j + dx[k] > 2) continue;
                    maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j + dx[k]] + arr[i][j]);
                    minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j + dx[k]] + arr[i][j]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxDp[N - 1][i]);
            min = Math.min(min, minDp[N - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}
