package bj2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j - 1) - '0';
            }
        }

        bfs(new Point(1, 1, 0));

        System.out.println(result);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[N + 1][M + 1][2];

        queue.offer(start);
        visited[start.r][start.c][0] = true;

        Point current;
        int size;
        int distance = 1;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.r == N && current.c == M) {
                    result = distance;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR >= 1 && tR <= N && tC >= 1 && tC <= M) {
                        if (!visited[tR][tC][current.cnt]) {
                            if (map[tR][tC] == 0) {
                                queue.offer(new Point(tR, tC, current.cnt));
                                visited[tR][tC][current.cnt] = true;
                            }
                        }
                        if (!visited[tR][tC][1] && current.cnt == 0) {
                            if (map[tR][tC] == 1) {
                                queue.offer(new Point(tR, tC, current.cnt+1));
                                visited[tR][tC][1] = true;
                            }
                        }
                    }
                }
            }

            distance++;
        }

    }
}