package bj16930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K, x1, y1, x2, y2, result;
    static char[][] map;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();

        int[][] visited = new int[N + 1][M + 1];

        queue.add(new Point(x1, y1));
        visited[x1][y1] = 1;
        result = -1;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.r == x2 && current.c == y2) {
                result = visited[current.r][current.c]-1;
                return;
            }

            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= K; k++) {
                    int tR = current.r + dr[j] * k;
                    int tC = current.c + dc[j] * k;

                    if (tR < 1 || tR > N || tC < 1 || tC > M) break;
                    if (visited[tR][tC] != 0) {
                        if (visited[tR][tC] <= visited[current.r][current.c]) break;
                        else continue;
                    }
                    if (map[tR][tC] == '#') break;

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = visited[current.r][current.c]+1;
                }
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j-1);
            }
        }

        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
    }
}
