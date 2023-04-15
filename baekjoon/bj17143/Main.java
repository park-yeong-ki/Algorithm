package bj17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, M;
    static class Shark{
        int s, d , z;
        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};
    static Shark[][] map;
    static Shark[][] copyMap;
    static int result;
    public static void main(String[] args) throws IOException {
        input();
        int col = 1;
        while (col <= C) {
            catchShark(col);
            moveShark();
            col++;
        }
        System.out.println(result);
    }

    static void catchShark(int col) {
        for (int i = 1; i <= R; i++) {
            if (map[i][col] != null) {
                result += map[i][col].z;
                map[i][col] = null;
                break;
            }
        }
    }
    static void moveShark() {
        copyMap = new Shark[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == null) continue;
                Shark sh = map[i][j];
                map[i][j] = null;
                int t = 0;
                int tR = 0, tC = 0;
                int r = i, c = j;
                while (t < sh.s) {
                    tR = r + dr[sh.d];
                    tC = c + dc[sh.d];

                    if (tR < 1 || tR > R || tC < 1 || tC > C) { //범위를 벗어난 경우
                        if (sh.d % 2 == 1) sh.d++;
                        else sh.d--;

                        tR = r + dr[sh.d];
                        tC = c + dc[sh.d];
                    }

                    r = tR;
                    c = tC;
                    t++;
                }
                if (copyMap[r][c] != null) {
                    if (copyMap[r][c].z < sh.z) {
                        copyMap[r][c] = sh;
                    }
                } else {
                    copyMap[r][c] = sh;
                }
            }
        }
        map = copyMap;
    }
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R+1][C+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            if (d <= 2) s %= (2 * (R - 1));
            else s %= (2 * (C - 1));

            map[r][c] = new Shark(s, d, z);
        }
    }
}