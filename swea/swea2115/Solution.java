package swea2115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, C, max, a, b;
    static int[][] map;
    static int[][] honey;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            comb(0, 0);
            sb.append("#" + test_case + " " + max + "\n");
        }
        System.out.println(sb);
    }

    static void subset(int idx, int sum, int profit, int num) {
        if (sum > C) {
            return;
        }

        if (M == idx) {
            if (num == 0) a = Math.max(a, profit);
            else b = Math.max(b, profit);
            return;
        }

        subset(idx + 1, sum + honey[num][idx], profit + (int) Math.pow(honey[num][idx], 2), num);
        subset(idx + 1, sum, profit, num);
    }

    static void comb(int start, int idx) {
        if (idx == 2) {
            a = b = Integer.MIN_VALUE;
            subset(0, 0, 0, 0);
            subset(0, 0, 0, 1);
            max = Math.max(max, a + b);

            return;
        }

        for (int i = start; i < N*N; i++) {
            int end = (i + M - 1);
            if (end/N != i/N) continue;
            for (int j = 0; j <= M-1; j++) {
                honey[idx][j] = map[(i + j) / N][(i + j) % N];
            }
            comb(end + 1, idx + 1);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        honey = new int[2][M];
        max = Integer.MIN_VALUE;
    }
}
