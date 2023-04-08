package bj15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> cctv = new ArrayList<>();
    static int N;
    static int M;
    static int[][] office;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] != 0 && office[i][j] != 6) {
                    cctv.add(i * M + j);
                }
            }
        }

        dfs(0);

        System.out.println(min);
    }

    static void dfs(int idx) {
        if (idx == cctv.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (office[i][j] == 0) cnt++;
                }
            }
            min = Math.min(min, cnt);
            return;
        }

        int r = cctv.get(idx) / M;
        int c = cctv.get(idx) % M;

        for (int i = 1; i <= 4; i++) {
            if (office[r][c] == 2 && i > 2) continue;
            if (office[r][c] == 5 && i > 1) continue;
            check(r, c, i, 9);
            dfs(idx+1);
            check(r, c, i, -9);
        }
    }

    static void check(int r, int c, int dir, int change) {
        switch (office[r][c]) {
            case 1:
                switch (dir) {
                    case 1:
                        right(r, c, change);
                        break;
                    case 2:
                        left(r, c, change);
                        break;
                    case 3:
                        up(r, c, change);
                        break;
                    case 4:
                        down(r, c, change);
                        break;
                }
                break;
            case 2:
                switch (dir) {
                    case 1:
                        right(r, c, change);
                        left(r, c, change);
                        break;
                    case 2:
                        up(r, c, change);
                        down(r, c, change);
                        break;
                }
                break;
            case 3:
                switch (dir) {
                    case 1:
                        right(r, c, change);
                        up(r, c, change);
                        break;
                    case 2:
                        up(r, c, change);
                        left(r, c, change);
                        break;
                    case 3:
                        left(r, c, change);
                        down(r, c, change);
                        break;
                    case 4:
                        down(r, c, change);
                        right(r, c, change);
                        break;
                }
                break;
            case 4:
                switch (dir) {
                    case 1:
                        right(r, c, change);
                        up(r, c, change);
                        left(r, c, change);
                        break;
                    case 2:
                        up(r, c, change);
                        left(r, c, change);
                        down(r, c, change);
                        break;
                    case 3:
                        left(r, c, change);
                        down(r, c, change);
                        right(r, c, change);
                        break;
                    case 4:
                        down(r, c, change);
                        right(r, c, change);
                        up(r, c, change);
                        break;
                }
                break;
            case 5:
                right(r, c, change);
                left(r, c, change);
                up(r, c, change);
                down(r, c, change);
                break;
        }
    }

    static void right(int r, int c, int change) {
        for (int i = c; i < M; i++) {
            if (office[r][i] % 9 == 0) {
                office[r][i] += change;
            } else if (office[r][i] == 6) {
                break;
            }
        }
    }

    static void left(int r, int c, int change){
        for (int i = c; i >= 0; i--) {
            if (office[r][i] % 9 == 0) {
                office[r][i] += change;
            } else if (office[r][i] == 6) {
                break;
            }
        }
    }

    static void up(int r, int c,int change){
        for (int i = r; i >= 0; i--) {
            if (office[i][c] % 9 == 0) {
                office[i][c] += change;
            } else if (office[i][c] == 6) {
                break;
            }
        }
    }

    static void down(int r, int c, int change){
        for (int i = r; i < N; i++) {
            if (office[i][c] % 9 == 0) {
                office[i][c] += change;
            } else if (office[i][c] == 6) {
                break;
            }
        }
    }
}
