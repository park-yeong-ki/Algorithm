package swea2117;

import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] map;
    static class Point{
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int max;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bfs(new Point(i, j));
                }
            }
            bw.write("#" + test_case + " " + max + "\n");
        }
        bw.close();
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][N];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        int size, cost, cnt = 0, K = 1;
        while (!queue.isEmpty()) {
            size = queue.size();

            cost = K * K + (K - 1) * (K - 1);

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                if (map[current.r][current.c] == 1) cnt++;

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue;
                    if (visited[tR][tC]) continue;

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }

            if (cnt * M >= cost) max = Math.max(max, cnt);
            K++;
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = Integer.MIN_VALUE;
    }
}
