package bj17142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, ans;
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
    static int[] selectedVirus;

    static List<Point> virusList;
    public static void main(String[] args) throws IOException {
        input();
        comb(0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static boolean isCompleted(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) continue;
                if (map[i][j] == 0 && !visited[i][j]) return false;
            }
        }

        return true;
    }

    static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            int num = selectedVirus[i];
            Point virus = virusList.get(num);
            queue.add(virus);
            visited[virus.r][virus.c] = true;
        }

        Point current;
        int size;
        int time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            if (ans <= time) return;
            if (isCompleted(visited)) {
                ans = Math.min(ans, time);
                return;
            }

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue;
                    if (map[tR][tC] == 1) continue;
                    if (visited[tR][tC]) continue;

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
            time++;
        }
    }

    static void comb(int idx, int start) {
        if (idx == M) {
            bfs();
            return;
        }

        Point virus;
        for (int i = start; i < virusList.size(); i++) {
            virus = virusList.get(i);
            selectedVirus[idx] = i;
            map[virus.r][virus.c] = 3;
            comb(idx + 1, i + 1);
            map[virus.r][virus.c] = 2;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virusList.add(new Point(i, j));
            }
        }

        selectedVirus = new int[M];
        ans = Integer.MAX_VALUE;
    }
}
