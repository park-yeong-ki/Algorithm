package bj14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] order;
    static int[] arr;
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        input();
        move();
        System.out.print(sb);
    }

    static void move() {
        sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            x += dr[order[i]];
            y += dc[order[i]];

            if (x < 0 || x >= N || y < 0 || y >= M){
                x -= dr[order[i]];
                y -= dc[order[i]];
                continue;
            }

            swap(order[i]);
            if (map[x][y] == 0) {
                map[x][y] = arr[1];
            } else {
                arr[1] = map[x][y];
                map[x][y] = 0;
            }

            sb.append(arr[6] + "\n");
        }
    }

    static void swap(int dir) {
        int temp = 0;
        switch (dir) {
            case 1: // 동
                temp = arr[1];
                arr[1] = arr[3];
                arr[3] = arr[6];
                arr[6] = arr[4];
                arr[4] = temp;
                break;
            case 2: // 서
                temp = arr[1];
                arr[1] = arr[4];
                arr[4] = arr[6];
                arr[6] = arr[3];
                arr[3] = temp;
                break;
            case 3: // 북
                temp = arr[1];
                arr[1] = arr[2];
                arr[2] = arr[6];
                arr[6] = arr[5];
                arr[5] = temp;
                break;
            case 4: // 남
                temp = arr[1];
                arr[1] = arr[5];
                arr[5] = arr[6];
                arr[6] = arr[2];
                arr[2] = temp;
                break;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        arr = new int[7];
    }
}
