package bj16973;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, H, W, Sr, Sc, Fr, Fc, result;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[N+1][M+1];

        queue.add(new Point(Sr, Sc));
        visited[Sr][Sc] = true;

        Point current;
        int size, time=0;
        while(!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.r == Fr && current.c == Fc) {
                    result = time;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if(tR < 1 || tR > N || tC < 1 || tC > M) continue;
                    if(tR + H - 1 > N || tC + W -1 > M) continue;
                    if (visited[tR][tC]) continue;
                    if (!isPossible(tR, tC)) continue;

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }

            time++;
        }
    }

    static boolean isPossible(int tR, int tC) {
        for (int k = tR; k < tR + H; k++) {
            for (int k2 = tC; k2 < tC + W; k2++) {
                if(map[k][k2] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken());
        Sc = Integer.parseInt(st.nextToken());
        Fr = Integer.parseInt(st.nextToken());
        Fc = Integer.parseInt(st.nextToken());
        result = -1;
    }
}