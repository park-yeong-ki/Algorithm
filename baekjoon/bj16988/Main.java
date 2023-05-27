package bj16988;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, result, max;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Integer> ai;
    static boolean[][] visited;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        setStone(0, 0);
        System.out.println(max);
    }

    static void setStone(int start, int idx) {
        if (idx == 2) {
            visited = new boolean[N][M];
            for (int num: ai) {
                int r = num / M;
                int c = num % M;
                if (!visited[r][c]) calculate(num, visited);
            }
            max = Math.max(max, result);
            result = 0;
            return;
        }

        for (int i = start; i < N*M; i++) {
            int r = i / M;
            int c = i % M;
            if (map[r][c] != 0) continue;
            map[r][c] = 1;
            setStone(i + 1, idx + 1);
            map[r][c] = 0;
        }
    }

    static void calculate(int start, boolean[][] visited) {
        Queue<Point> queue = new ArrayDeque<>();

        int r = start / M;
        int c = start % M;
        queue.add(new Point(r, c));
        visited[r][c] = true;

        boolean flag = true;
        Point current;
        int cnt = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= N || tC < 0 || tC >= M) continue;
                if (visited[tR][tC]) continue;

                if (map[tR][tC] == 0) flag = false;
                if (map[tR][tC] == 2) {
                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
            cnt++;
        }
        if (flag) result += cnt;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ai = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) ai.add(i * M + j);
            }
        }
        result = 0;
    }
}
