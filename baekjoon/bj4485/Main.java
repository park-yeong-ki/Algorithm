package bj4485;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int N;

    static class Point {
        int r, c, cost;

        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(new Point(0, 0, arr[0][0]));
            bw.write("Problem " + num + ": " + min + "\n");
            min = Integer.MAX_VALUE;
            num++;
        }
        bw.close();

    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][N];

        queue.offer(start);
        visited[start.r][start.c] = true;

        int[][] costMap = new int[N][N];
        costMap[start.r][start.c] = start.cost;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR >= 0 && tR < N && tC >= 0 && tC < N) {
                    int tCost = current.cost + arr[tR][tC];
                    if (tCost < costMap[tR][tC] || !visited[tR][tC]) {
                        queue.offer(new Point(tR, tC, tCost));
                        visited[tR][tC] = true;
                        costMap[tR][tC] = tCost;
                    }
                }
            }
        }

        min = costMap[N-1][N-1];
    }

}