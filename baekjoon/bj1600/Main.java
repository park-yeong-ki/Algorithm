package bj1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static class Point{
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static int K;
    static int W;
    static int H;
    static int[][] map;
    static int[] dr = {-1, 0 ,1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] drK = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] drC = {1, 2, 2, 1, -1, -2, -2, -1};
    static int result;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(new Point(0, 0, 0));
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[H][W][K+1];

        queue.offer(start);
        visited[start.r][start.c][start.cnt] = true;

        int size;
        Point current;
        while(!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                int kCnt = current.cnt;

                if (current.r == H-1 && current.c == W-1) {
                    System.out.println(result);
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if(tR >=0 && tR < H && tC >= 0 && tC < W && !visited[tR][tC][kCnt] && map[tR][tC] == 0) {
                        queue.offer(new Point(tR, tC, kCnt));
                        visited[tR][tC][kCnt] = true;
                    }
                }

                if(kCnt < K) {
                    kCnt += 1;
                    for (int j = 0; j < 8; j++) {
                        int tR = current.r + drK[j];
                        int tC = current.c + drC[j];

                        if(tR >=0 && tR < H && tC >= 0 && tC < W && !visited[tR][tC][kCnt] && map[tR][tC] == 0) {
                            queue.offer(new Point(tR, tC, kCnt));
                            visited[tR][tC][kCnt] = true;
                        }
                    }
                }
            }

            result++;
        }

        System.out.println(-1);
    }
}