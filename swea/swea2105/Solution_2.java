package swea2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, sR, sC, max;
    static int[][] map;
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sR = i;
                    sC = j;
                    dfs(i, j, 0, 0, new boolean[101]);
                }
            }
            sb.append("#" + test_case + " " + max + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c, int d, int cnt, boolean[] visited) {
        if (d == 3 && r == sR && c == sC) {
            max = Math.max(max, cnt);
            return;
        }

        if (r < 0 || r >= N || c < 0 || c >= N) return;
        if (visited[map[r][c]]) return;

        visited[map[r][c]] = true;

        dfs(r + dr[d], c + dc[d], d, cnt + 1, visited);
        if (d < 3) dfs(r + dr[d + 1], c + dc[d + 1], d + 1, cnt + 1, visited);

        visited[map[r][c]] = false;
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = -1;
    }
}
