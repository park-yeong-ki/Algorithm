package swea4193;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                 st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            st = new StringTokenizer(br.readLine());
            Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            bfs(start, end);

            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.close();
    }

    static void bfs(Point start, Point end) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][N];

        queue.offer(start);
        visited[start.r][start.c] = true;

        Point current;
        int size;
        int time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (arr[current.r][current.c] == 2 && (time-1) % 3 != 2) {
                    queue.offer(current);
                    continue;
                }

                if (current.r == end.r && current.c == end.c) {
                    result = time;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR >= 0 && tR < N && tC >= 0 && tC < N) {
                        if (!visited[tR][tC] && arr[tR][tC] != 1) {
                            queue.offer(new Point(tR, tC));
                            visited[tR][tC] = true;
                        }
                    }
                }
            }

            time++;
        }

        result = -1;
    }
}
