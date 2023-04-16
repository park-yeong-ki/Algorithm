package bj16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j, visited)) {
                            flag = true;
                        }
                    }
                }
            }
            if (flag) day++;
            else break;
        }
        System.out.println(day);
    }

    static boolean bfs(int r, int c, boolean[][] visited) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.add(new Point(r, c));
        visited[r][c] = true;

        Point current;
        int sum = 0;
        int cnt = 0;
        List<Point> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            current = queue.poll();
            sum += map[current.r][current.c];
            cnt++;

            list.add(new Point(current.r, current.c));

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR >= 0 && tR < N && tC >= 0 && tC < N) {
                    if (!visited[tR][tC]) {
                        int p = Math.abs(map[current.r][current.c] - map[tR][tC]);
                        if (p >= L && p <= R) {
                            queue.add(new Point(tR, tC));
                            visited[tR][tC] = true;
                        }
                    }
                }
            }
        }

        int population = sum / cnt;
        Point p;
        for (int i = 0; i < list.size(); i++) {
            p = list.get(i);
            map[p.r][p.c] = population;
        }

        if (cnt > 1) return true;
        else return false;
    }
}