package bj14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }

    static void dfs(int r, int c, int idx, int sum) {
        if (idx == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tR = r + dr[i];
            int tC = c + dc[i];

            if (tR >= 0 && tR < N && tC >= 0 && tC < M) {
                if (!visited[tR][tC]) {
                    if (idx == 2) {
                        visited[tR][tC] = true;
                        dfs(r, c, idx + 1, sum + arr[tR][tC]);
                        visited[tR][tC] = false;
                    }

                    visited[tR][tC] = true;
                    dfs(tR, tC, idx + 1, sum + arr[tR][tC]);
                    visited[tR][tC] = false;
                }
            }
        }
    }
}
