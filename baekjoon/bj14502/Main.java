import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int dr[] = { -1, 0, 1, 0 };
    static int dc[] = { 0, 1, 0, -1 };
    static int virus;
    static int max = Integer.MIN_VALUE;
    static int wall;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    wall++;
                }
            }
        }

        comb(0, 0);

        System.out.println(max);
    }

    static void bfs(Point start, boolean[][] visited ) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start.r][start.c] = true;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            virus++;

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];
                if (tR >= 0 && tR < N && tC >= 0 && tC < M && !visited[tR][tC] && map[tR][tC] == 0) {
                    queue.offer(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
        }
    }

    static void comb(int start, int idx) {
        if (idx == 3) {
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2 && !visited[i][j]) {
                        bfs(new Point(i, j), visited);
                    }
                }
            }
            int safe = N * M - (virus + wall + 3);
            max = Math.max(max, safe);
            virus = 0;

            return;
        }

        for (int i = start; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    comb(i, idx + 1);
                    map[i][j] = 0;
                }
            }
        }

    }
}