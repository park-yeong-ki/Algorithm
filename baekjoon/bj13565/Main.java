package bj13565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean flag;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        boolean[][] visited = new boolean[M][N];
        for (int i = 0; i < N; i++) {
            if (!flag && map[0][i] == 0) bfs(new Point(0, i), visited);
//            if (!flag && map[0][i] == 0) dfs(0, i, visited);
        }
        if (flag) System.out.println("YES");
        else System.out.println("NO");
    }

//    static void dfs(int r, int c, boolean[][] visited) {
//        if (r == M - 1) {
//            flag = true;
//            return;
//        }
//
//        visited[r][c] = true;
//
//        for (int i = 0; i < 4; i++) {
//            int tR = r + dr[i];
//            int tC = c + dc[i];
//            if (tR < 0 || tR >= M || tC < 0 || tC >= N) continue;
//            if (map[tR][tC] == 1 || visited[tR][tC]) continue;
//            dfs(tR, tC, visited);
//        }
//    }

    static void bfs(Point start, boolean[][] visited) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.r == M - 1) {
                flag = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= M || tC < 0 || tC >= N) continue;
                if (map[tR][tC] == 1 || visited[tR][tC]) continue;

                queue.add(new Point(tR, tC));
                visited[tR][tC] = true;
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        flag = false;
    }
}
