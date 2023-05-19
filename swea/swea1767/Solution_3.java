package swea1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, result, max;
    static int[][] cell;
    static List<Core> coreList;
    static class Core{
        int r, c;
        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            connect(0, 0);
            sb.append("#" + test_case + " " + result + "\n");
        }
        System.out.print(sb);
    }

    static void connect(int idx, int cnt) {
        if (idx == coreList.size()) {
            if (max <= cnt) {
                int length = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (cell[i][j] == 2) length++;
                    }
                }

                if (max < cnt) {
                    max = cnt;
                    result = length;
                } else {
                    result = Math.min(result, length);
                }
            }
            return;
        }

        int r = coreList.get(idx).r;
        int c = coreList.get(idx).c;

        for (int i = 0; i < 4; i++) {
            if (isPossible(r, c, i)) {
                check(r, c, i, 2);
                connect(idx + 1, cnt + 1);
                check(r, c, i, 0);
            }
        }
        connect(idx + 1, cnt);
    }

    static void check(int r, int c, int d, int num) {
        int tR = r;
        int tC = c;
        while (true) {
            tR += dr[d];
            tC += dc[d];

            if (tR > N - 1 || tR < 0 || tC > N - 1 || tC < 0) return;
            cell[tR][tC] = num;
        }
    }

    static boolean isPossible(int r, int c, int d) {
        int tR = r;
        int tC = c;
        while (true) {
            tR += dr[d];
            tC += dc[d];

            if (tR > N - 1 || tR < 0 || tC > N - 1 || tC < 0) return true;
            if (cell[tR][tC] != 0) return false;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        cell = new int[N][N];
        coreList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cell[i][j] = Integer.parseInt(st.nextToken());
                if (i != 0 && i != N-1 && j != 0 && j != N-1 && cell[i][j] == 1) coreList.add(new Core(i, j));
            }
        }
        result = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }
}
