package swea1249;

import java.io.*;
import java.util.PriorityQueue;

public class Solution {
    static class Point implements Comparable<Point>{
        int r, c, w;

        public Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
            bfs(new Point(0, 0, map[0][0]));

            bw.write("#" + test_case + " " + result + "\n");
            result = 0;
        }
        bw.close();
    }

    static void bfs(Point start) {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        boolean[][] visited = new boolean[N][N];

        pq.offer(start);
        visited[start.r][start.c] = true;

        Point current;
        while (!pq.isEmpty()) {
            current = pq.poll();

            if (current.r == N - 1 && current.c == N - 1) {
                result = current.w;
                return;
            }

            for (int j = 0; j < 4; j++) {
                int tR = current.r + dr[j];
                int tC = current.c + dc[j];
                if (tR >= 0 && tR < N && tC >= 0 && tC < N) {
                    if (!visited[tR][tC]) {
                        pq.offer(new Point(tR, tC, current.w + map[tR][tC]));
                        visited[tR][tC] = true;
                    }
                }
            }
        }
    }
}
