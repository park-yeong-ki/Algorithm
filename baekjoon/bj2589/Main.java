package bj2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int H, W, max;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'L') bfs(new Point(i, j));
            }
        }
        System.out.println(max);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        int size, time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= H || tC < 0 || tC >= W) continue;
                    if (visited[tR][tC] || map[tR][tC] == 'W') continue;

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
            time++;
        }
        max = Math.max(max, time - 1);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }

        max = Integer.MIN_VALUE;
    }
}
