package bj3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r, c, s;

        public Point(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int R;
    static int C;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static char[][] map;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        Point start = null, end = null;
        List<Point> water = new ArrayList();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    start = new Point(i, j, 0);
                }
                if (map[i][j] == '*') {
                    water.add(new Point(i, j, 1));
                }
            }
        }

        bfs(start, water);

        System.out.println(result == 0 ? "KAKTUS" : result);
    }

    static void bfs(Point start, List<Point> water) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[R][C][2];

        for (int i = 0; i < water.size(); i++) {
            Point w = water.get(i);
            queue.offer(w);
            visited[w.r][w.c][w.s] = true;
        }

        queue.offer(start);
        visited[start.r][start.c][start.s] = true;

        Point current;
        int time = 0, size;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (map[current.r][current.c] == 'D') {
                    result = time;
                    return;
                }

                if (current.s == 1) {
                    for (int j = 0; j < 4; j++) {
                        int tR = current.r + dr[j];
                        int tC = current.c + dc[j];

                        if (tR >= 0 && tR < R && tC >= 0 && tC < C) {
                            if (!visited[tR][tC][current.s] && map[tR][tC] != 'D' && map[tR][tC] != 'X') {
                                queue.offer(new Point(tR, tC, current.s));
                                visited[tR][tC][current.s] = true;
                                map[tR][tC] = '*';
                            }
                        }
                    }
                }
                else {
                    for (int j = 0; j < 4; j++) {
                        int tR = current.r + dr[j];
                        int tC = current.c + dc[j];

                        if (tR >= 0 && tR < R && tC >= 0 && tC < C) {
                            if (!visited[tR][tC][current.s] && map[tR][tC] != '*' && map[tR][tC] != 'X') {
                                queue.offer(new Point(tR, tC, current.s));
                                visited[tR][tC][current.s] = true;
                            }
                        }
                    }
                }
            }

            time++;
        }
    }
}