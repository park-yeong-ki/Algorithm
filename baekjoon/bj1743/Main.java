package bj1743;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int cnt;
    public static void main(String[] args) throws IOException {
        input();
        int max = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!visited[i][j] && arr[i][j] == 1) {
                    cnt = 0;
                    dfs(i, j, visited);
                    max = Math.max(max, cnt);
                }
            }
        }
        System.out.println(max);
    }

    static void dfs(int r, int c, boolean[][] visited){
        cnt++;
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int tR = r + dr[i];
            int tC = c + dc[i];
            if (tR >= 1 && tR <= N && tC >= 1 && tC <= M) {
                if (!visited[tR][tC] && arr[tR][tC] == 1) {
                    dfs(tR, tC, visited);
                }
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        int r,c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            arr[r][c] = 1;
        }
    }
}
