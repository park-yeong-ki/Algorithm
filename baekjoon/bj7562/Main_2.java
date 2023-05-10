package bj7562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2 {
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int l;
    static Point start, end;
    static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T= Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            bfs();
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[l][l];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        int size, time = 0;
        while(!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.r == end.r && current.c == end.c) {
                    sb.append(time+"\n");
                    return;
                }

                for (int j = 0; j < 8; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    //큐에 들어가면 안되는 조건 설정
                    if (tR < 0 || tR >= l || tC < 0 || tC >= l) continue;
                    if (visited[tR][tC]) continue;

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
            time++;
        }
    }

    static void input() throws NumberFormatException, IOException {
        l = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
}