package bj4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static class Point{
        int r, c, s;
        public Point(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static String result;
    static Point start;
    static List<Point> fire;
    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[R][C][2];

        if (fire.size() != 0) {
            for (int i = 0; i < fire.size(); i++) {
                queue.add(fire.get(i));
                visited[fire.get(i).r][fire.get(i).c][fire.get(i).s] = true;
            }
        }

        queue.add(start);
        visited[start.r][start.c][start.s] = true;

        int size, time = 0;
        Point current;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.s == 0) {
                    if (current.r == 0 || current.r == R-1 || current.c == 0 || current.c == C-1) {
                        result = String.valueOf(time+1);
                        return;
                    }
                }

                if (current.s == 0) {
                    for (int j = 0; j < 4; j++) {
                        int tR = current.r + dr[j];
                        int tC = current.c + dc[j];

                        //경계 벗어난 경우
                        if (tR < 0 || tR > R-1 || tC < 0 || tC > C-1) continue;

                        //지훈
                        if (map[tR][tC] == '#' || map[tR][tC] == 'F') continue;
                        if (visited[tR][tC][current.s]) continue;

                        queue.add(new Point(tR, tC, current.s));
                        visited[tR][tC][current.s] = true;
                    }
                }

                if (current.s == 1) {
                    for (int j = 0; j < 4; j++) {
                        int tR = current.r + dr[j];
                        int tC = current.c + dc[j];

                        //경계 벗어난 경우
                        if (tR < 0 || tR > R-1 || tC < 0 || tC > C-1) continue;

                        //불
                        if (map[tR][tC] == '#') continue;
                        if (visited[tR][tC][current.s]) continue;

                        queue.add(new Point(tR, tC, current.s));
                        visited[tR][tC][current.s] = true;
                        map[tR][tC] = 'F';
                    }
                }
            }
            time++;
        }
        result = "IMPOSSIBLE";
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fire = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    start = new Point(i, j, 0);
                }
                else if (map[i][j] == 'F') {
                    fire.add(new Point(i, j, 1));
                }
            }
        }
    }
}