package bj17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T;
    static int[][] map;
    static int[][] rot;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean flag;

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < T; i++) {
            int x = rot[i][0];
            int d = rot[i][1];
            int k = rot[i][2];

            moveCircle(x, d, k);

            flag = true;
            for (int j = 0; j < N; j++) {
                for (int l = 0; l < M; l++) {
                    if (map[j][l] == 0) continue;
                    bfs(new Point(j, l));
                }
            }

            if (flag){
                int sum = 0;
                int cnt = 0;
                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < M; l++) {
                        if (map[j][l] == 0) continue;
                        sum += map[j][l];
                        cnt++;
                    }
                }

                double avg = (double) sum / cnt;

                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < M; l++) {
                        if (map[j][l] == 0) continue;

                        if(map[j][l] > avg) map[j][l]--;
                        else if (map[j][l] < avg) map[j][l]++;
                    }
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < N; j++) {
            for (int l = 0; l < M; l++) {
                if (map[j][l] == 0) continue;
                ans += map[j][l];
            }
        }

        System.out.println(ans);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.add(start);

        Point current;
        int size;
        int time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int s = 0; s < size; s++) {
                current = queue.poll();

                int num = map[current.r][current.c];

                for (int i = 0; i < 4; i++) {
                    int tR = current.r + dr[i];
                    int tC = current.c + dc[i];

                    if (tR < 0 || tR >= N) continue;
                    if (tC < 0) tC = M - 1;
                    if (tC >= M) tC = 0;

                    if (map[tR][tC] == 0) continue;
                    if (num != map[tR][tC]) continue;

                    queue.add(new Point(tR, tC));
                    map[current.r][current.c] = 0;
                    flag = false;
                }

                if (time != 0) map[current.r][current.c] = 0;
            }

            time++;
        }
    }

    static void moveCircle(int x, int d, int k) {
        for (int i = 0; i < N; i++) {
            if ((i+1) % x != 0) continue;

            int cnt = 0;
            while (cnt < k) {
                if (d == 0) { //시계 방향
                    int temp = map[i][M - 1];
                    for (int j = M - 1; j >= 1; j--) {
                        map[i][j] = map[i][j - 1];
                    }
                    map[i][0] = temp;
                } else { //반시계 방향
                    int temp = map[i][0];
                    for (int j = 0; j < M - 1; j++) {
                        map[i][j] = map[i][j + 1];
                    }
                    map[i][M - 1] = temp;
                }
                cnt++;
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        T = Integer.parseInt(st.nextToken()); //회전수

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rot = new int[T][3];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            rot[i][0] = Integer.parseInt(st.nextToken());
            rot[i][1] = Integer.parseInt(st.nextToken());
            rot[i][2] = Integer.parseInt(st.nextToken());
        }
    }
}
