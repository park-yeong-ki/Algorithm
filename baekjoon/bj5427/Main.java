package bj5427;

import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static int[][] map;
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];

            Point start = null;
            List<Point> fire = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') {
                        start = new Point(i, j, 0);
                    } else if (map[i][j] == '*') {
                        fire.add(new Point(i, j, 1));
                    }
                }
            }

            bfs(start, fire);
            bw.write(result);
            bw.newLine();
        }
        bw.close();
    }

    static void bfs(Point start, List<Point> fire) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[h][w][2];

        //불 넣기
        for (int i = 0; i < fire.size(); i++) {
            queue.add(fire.get(i));
            visited[fire.get(i).r][fire.get(i).c][fire.get(i).s] = true;
        }

        //상근이 넣기
        queue.add(start);
        visited[start.r][start.c][start.s] = true;

        int size, time = 0;
        Point current;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.s == 0) {
                    if (current.r == 0 || current.r == h - 1 || current.c == 0 || current.c == w - 1) {
                        result = String.valueOf(time + 1);
                        return;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    //빌딩 내부인 경우
                    if (tR >= 0 && tR < h && tC >= 0 && tC < w) {
                        if (!visited[tR][tC][current.s]) {
                            if (current.s == 0) { //상근이
                                if (map[tR][tC] != '#' && map[tR][tC] != '*') {
                                    queue.add(new Point(tR, tC, current.s));
                                    visited[tR][tC][current.s] = true;
                                }
                            } else { //불
                                if (map[tR][tC] != '#') {
                                    queue.add(new Point(tR, tC, current.s));
                                    visited[tR][tC][current.s] = true;
                                    map[tR][tC] = '*';
                                }
                            }
                        }
                    }
                }
            }
            time++;
        }
        result = "IMPOSSIBLE";
    }
}
