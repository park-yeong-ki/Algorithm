package swea1767;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_2 {
    static int N;
    static int[][] arr;
    static List<Integer> core;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] count;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            core = new ArrayList<>();
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        if (i != 0 && j != 0 && i != N - 1 && j != N - 1) {
                            core.add(i * N + j);
                        }
                    }
                }
            }

            count = new int[core.size() + 1];

            for (int i = 0; i < 4; i++) {
                dfs(0, i, 0, 0);
            }

            bw.write("#" + test_case + " " + min + "\n");
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }
        bw.close();
    }

    static void dfs(int idx, int dir, int cnt, int connect) {
        if (max > core.size() - idx + connect) {
            return;
        }

        if (idx == core.size()) {
            if (max < connect) {
                max = connect;
                min = cnt;
            } else if (max == connect) {
                min = Integer.min(min, cnt);
            }
            return;
        }

        int r = core.get(idx) / N;
        int c = core.get(idx) % N;

        for (int i = 0; i < 4; i++) {
            if (check(r, c, dir)) {
                int sum = connect(r, c, dir, 2);
                dfs(idx + 1, i, cnt + sum, connect + 1);
                connect(r, c, dir, -2);
            } else {
                dfs(idx + 1, i, cnt, connect);
            }
        }
    }

    static boolean check(int r, int c, int dir) {
        int tR = r;
        int tC = c;
        while (tR + dr[dir] >= 0 && tR + dr[dir] < N && tC + dc[dir] >= 0 && tC + dc[dir] < N) {
            tR += dr[dir];
            tC += dc[dir];

            if (arr[tR][tC] != 0) {
                return false;
            }
        }
        return true;
    }

    static int connect(int r, int c, int dir, int cNum) {
        int sum = 0;
        int tR = r;
        int tC = c;
        while (tR + dr[dir] >= 0 && tR + dr[dir] < N && tC + dc[dir] >= 0 && tC + dc[dir] < N) {
            tR += dr[dir];
            tC += dc[dir];

            arr[tR][tC] += cNum;
            sum++;
        }
        return sum;
    }
}