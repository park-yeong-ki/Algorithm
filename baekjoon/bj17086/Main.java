package bj17086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static class Point{
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) bfs(new Point(i, j));
            }
        }
        System.out.println(max);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][M];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        int size, time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (map[current.r][current.c] == 1) {
                    max = Math.max(max, time);
                    return;
                }

                for (int j = 0; j < 8; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR >= 0 && tR < N && tC >= 0 && tC < M) {
                        if (!visited[tR][tC]) {
                            queue.add(new Point(tR, tC));
                            visited[tR][tC] = true;
                        }
                    }
                }
            }
            time++;
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
