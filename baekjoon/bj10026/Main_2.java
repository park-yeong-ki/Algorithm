package bj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2 {
    // 상하좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int N;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static char start;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());

        //원본 배열
        char[][] arr1 = new char[N][N];
        //적록색약 배열
        char[][] arr2 = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr1[i][j] = str.charAt(j);
                arr2[i][j] = str.charAt(j) == 'G' ? 'R' : str.charAt(j);
            }
        }


        //dfs
        boolean[][] visited1 = new boolean[N][N];
        boolean[][] visited2 = new boolean[N][N];
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //적록색약 아닌 경우
                if (!visited1[i][j]) {
                    start = arr1[i][j];
                    dfs(new Point(i, j), visited1, arr1);
                    cnt1++;
                }
                //적록색약인 경우
                if(!visited2[i][j]) {
                    start = arr2[i][j];
                    dfs(new Point(i, j), visited2, arr2);
                    cnt2++;
                }
            }
        }

        //출력
        System.out.println(cnt1 + " " + cnt2);
    }

    static void dfs(Point point, boolean[][] visited, char[][] arr) {
        int r = point.r;
        int c = point.c;

        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int tR = r + dr[i];
            int tC = c + dc[i];

            if (tR >= 0 && tR < N && tC >= 0 && tC < N) {
                if (!visited[tR][tC] && arr[tR][tC] == start) {
                    dfs(new Point(tR, tC), visited, arr);
                }
            }
        }

    }
}