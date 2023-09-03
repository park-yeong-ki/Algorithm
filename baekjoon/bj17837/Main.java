package bj17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] map;
    static class Horse{
        int r, c, d;

        public Horse(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};

    static Horse[] horses;
    static ArrayList<Integer>[][] horseMap;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        input();
        int turn = 0;
        while (turn <= 1000 && !flag) {
            move();
            turn++;
        }

        System.out.println(flag ? turn : -1);
    }

    static void move() {
        Horse h = null;
        for (int i = 1; i <= K; i++) {
            h = horses[i];
            int tR = h.r + dr[h.d];
            int tC = h.c + dc[h.d];

            if (tR < 1 || tR > N || tC < 1 || tC > N || map[tR][tC] == 2) { // 경계밖, 파란색
                if (horses[i].d % 2 == 0) horses[i].d--;
                else horses[i].d++;
                tR = h.r + dr[h.d];
                tC = h.c + dc[h.d];

                if (tR < 1 || tR > N || tC < 1 || tC > N || map[tR][tC] == 2) { // 경계밖, 파란색
                    continue;
                }

                if (map[tR][tC] == 0) { // 흰색
                    int idx = horseMap[tR][tC].size();
                    for (int j = horseMap[h.r][h.c].size()-1; j >= 0; j--) {
                        int num = horseMap[h.r][h.c].remove(j);
                        horses[num].r = tR;
                        horses[num].c = tC;
                        horseMap[tR][tC].add(idx, num);
                        if (horseMap[tR][tC].size() >= 4){
                            flag = true;
                            return;
                        }
                        if (num == i) break;
                    }
                } else if (map[tR][tC] == 1) { // 빨간색
                    for (int j = horseMap[h.r][h.c].size() - 1; j >= 0; j--) {
                        int num = horseMap[h.r][h.c].remove(j);
                        horses[num].r = tR;
                        horses[num].c = tC;
                        horseMap[tR][tC].add(num);
                        if (horseMap[tR][tC].size() >= 4) {
                            flag = true;
                            return;
                        }
                        if (num == i) break;
                    }
                }
            } else if (map[tR][tC] == 0) { // 흰색
                int idx = horseMap[tR][tC].size();
                for (int j = horseMap[h.r][h.c].size()-1; j >= 0; j--) {
                    int num = horseMap[h.r][h.c].remove(j);
                    horses[num].r = tR;
                    horses[num].c = tC;
                    horseMap[tR][tC].add(idx, num);
                    if (horseMap[tR][tC].size() >= 4){
                        flag = true;
                        return;
                    }
                    if (num == i) break;
                }
            } else if (map[tR][tC] == 1) { // 빨간색
                for (int j = horseMap[h.r][h.c].size() - 1; j >= 0; j--) {
                    int num = horseMap[h.r][h.c].remove(j);
                    horses[num].r = tR;
                    horses[num].c = tC;
                    horseMap[tR][tC].add(num);
                    if (horseMap[tR][tC].size() >= 4) {
                        flag = true;
                        return;
                    }
                    if (num == i) break;
                }
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        horseMap = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                horseMap[i][j] = new ArrayList<>();
            }
        }

        horses = new Horse[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(r, c, d);
            horseMap[r][c].add(i);
        }

        flag = false;
    }
}
