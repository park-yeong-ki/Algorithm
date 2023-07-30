package bj15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H, min;
    static boolean[][] rowLine;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 1);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int cnt, int row) {
        if (isPossible()) {
            min = Math.min(min, cnt);
            return;
        }

        if (cnt >= 3 || cnt >= min) {
            return;
        }

        for (int i = row; i <= H; i++) {
            for (int j = 1; j <= N - 1; j++) {
                if (rowLine[i][j] || rowLine[i][j-1] || rowLine[i][j+1]) continue;
                rowLine[i][j] = true;
                dfs(cnt + 1, i);
                rowLine[i][j] = false;
            }
        }
    }

    static boolean isPossible() {
        flag = true;
        for (int i = 1; i <= N; i++) {
            move(1, i, i);
            if (!flag) return false;
        }
        return true;
    }

    static void move(int h, int n, int start) {
        if (h == H + 1) {
            if (n == start) flag = true;
            else flag = false;
            return;
        }

        int tH = h;
        int tN = n;
        if (rowLine[tH][tN]) { //오른쪽
            tN++;
        } else if (rowLine[tH][tN - 1]) { //왼쪽
            tN--;
        }
        tH++;
        move(tH, tN, start);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        rowLine = new boolean[H + 1][N + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            rowLine[a][b] = true;
        }

        min = Integer.MAX_VALUE;
    }
}