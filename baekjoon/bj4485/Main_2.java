package bj4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2 {
    static class Point implements Comparable<Point> {
        int r, c, w;

        public Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());

            //종료조건
            if (N==0) {
                break;
            }

            //동굴 입력
            arr = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            sb.append("Problem " + num + ": ");
            dijkstra(new Point(0, 0, arr[0][0]));

            num++;
        }
        System.out.println(sb);
    }

    static void dijkstra(Point start) {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        boolean[][] visited = new boolean[N][N];

        pq.add(start);

        Point current;
        while(!pq.isEmpty()) {
            current = pq.poll();

            if (current.r == N-1 && current.c == N-1) {
                sb.append(current.w + "\n");
                return;
            }

            visited[current.r][current.c] = true;

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                //경계
                if (tR >= 0 && tR < N && tC >= 0 && tC <N) {
                    int tW = current.w + arr[tR][tC];

                    if (!visited[tR][tC]) {
                        pq.add(new Point(tR, tC, tW));
                    }
                }
            }
        }
    }
}