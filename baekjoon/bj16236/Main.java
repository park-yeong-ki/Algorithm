package bj16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int shark = 2;
    static int[][] arr;
    static int time;
    static int r;
    static int c;
    static int cnt;
    static boolean flag = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    r = i;
                    c = j;
                    arr[i][j] = 0;
                }
            }
        }

        while (flag) {
            bfs(new Point(r, c));
            if (cnt == shark) {
                shark++;
                cnt = 0;
            }
        }

        System.out.println(time);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][N];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        int size;
        int fR = Integer.MAX_VALUE;
        int fC = Integer.MAX_VALUE;
        int t = 0;
        flag = false;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int s = 0; s < size; s++) {
                current = queue.poll();

                if (arr[current.r][current.c] < shark && arr[current.r][current.c] != 0) {
                    if (fR > current.r) {
                        fR = current.r;
                        fC = current.c;
                    } else if (fR == current.r) {
                        if (fC > current.c) {
                            fC = current.c;
                        }
                    }

                    flag = true;
                }

                for (int i = 0; i < 4; i++) {
                    int tR = current.r + dr[i];
                    int tC = current.c + dc[i];

                    if (tR >= 0 && tR < N && tC >= 0 && tC < N) {
                        if (!visited[tR][tC] && shark >= arr[tR][tC]) {
                            queue.add(new Point(tR, tC));
                            visited[tR][tC] = true;
                        }
                    }
                }
            }
            if (flag) {
                arr[fR][fC] = 0;
                r = fR;
                c = fC;
                time += t;
                cnt++;
                return;
            }

            t++;
        }
    }
}
