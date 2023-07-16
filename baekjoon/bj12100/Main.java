package bj12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int depth) {
        if (depth == 5) {
            ans = Math.max(ans, maxBlock());
            return;
        }

        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        for (int i = 0; i < 4; i++) {
            visited = new boolean[N][N];
            checkBlock(i);
            dfs(depth + 1);
            for (int j = 0; j < N; j++) {
                map[j] = copyMap[j].clone();
            }
        }
    }

    static void moveBlock(int r, int c, int d) {
        int tR = r;
        int tC = c;
        boolean flag = false;

        while (true) {
            tR += dr[d];
            tC += dc[d];

            if (tR < 0 || tR >= N || tC < 0 || tC >= N) {
                tR -= dr[d];
                tC -= dc[d];
                break;
            }

            if (map[tR][tC] == map[r][c] && !visited[tR][tC]) {
                flag = true;
                break;
            } else if (map[tR][tC] != 0) {
                tR -= dr[d];
                tC -= dc[d];
                break;
            }
        }

        if (tR != r || tC != c) {
            if (flag) {
                map[tR][tC] += map[r][c];
                visited[tR][tC] = true;
            } else {
                map[tR][tC] = map[r][c];
            }
            map[r][c] = 0;
        }
    }

    static void checkBlock(int d) {
        switch (d) {
            case 0: //상
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] != 0){
                            moveBlock(i, j, d);
                        }
                    }
                }
                break;
            case 1: //하
                for (int i = N-1; i >= 0; i--) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] != 0){
                            moveBlock(i, j, d);
                        }
                    }
                }
                break;
            case 2: //좌
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[j][i] != 0) {
                            moveBlock(j, i, d);
                        }
                    }
                }
                break;
            case 3: //우
                for (int i = N-1; i >= 0; i--) {
                    for (int j = 0; j < N; j++) {
                        if (map[j][i] != 0) {
                            moveBlock(j, i, d);
                        }
                    }
                }
                break;
        }
    }

    static int maxBlock() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MIN_VALUE;
    }
}
