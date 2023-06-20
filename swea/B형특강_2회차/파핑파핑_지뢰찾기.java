package B형특강_2회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파핑파핑_지뢰찾기 {
    static int N, cnt;
    static char[][] map;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
    static List<Point> start;
    static boolean[][] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
           input();
            for (Point point : start) {
                if (!visited[point.r][point.c]) bfs(point);
            }

            sb.append("#" + test_case + " " + cnt+ "\n");
        }
        System.out.print(sb);
    }

    static void bfs(Point point) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.add(point);
        visited[point.r][point.c] = true;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            cnt--;

            if (check(current.r, current.c)) {
                for (int i = 0; i < 8; i++) {
                    int tR = current.r + dr[i];
                    int tC = current.c + dc[i];

                    if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue;
                    if (visited[tR][tC]) continue;
                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
        }
        cnt++;
    }

    static boolean check(int r, int c){
        for (int i = 0; i < 8; i++) {
            int tR = r + dr[i];
            int tC = c + dc[i];

            if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue;
            if (map[tR][tC] == '*') return false;
        }
        return true;
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        start = new ArrayList<>();
        cnt = 0;
        visited = new boolean[N][N];

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.') {
                    cnt++;
                    if (check(i, j)) start.add(new Point(i, j));
                }
            }
        }
    }
}
