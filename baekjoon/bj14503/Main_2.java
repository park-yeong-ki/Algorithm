package bj14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int sR, sC, sD, N, M, cnt;
    public static void main(String[] args) throws IOException {
        input();
        operate(sR, sC, sD);
        System.out.println(cnt);
    }

    static boolean isEmptyAndClean(int r, int c) {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            if (arr[r + dr[i]][c + dc[i]] == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    static void operate(int r, int c, int d) {
        if (arr[r][c] == 0) {
            arr[r][c] = 2;
            cnt++;
        }

        if (!isEmptyAndClean(r, c)) {
            if (arr[r - dr[d]][c - dc[d]] != 1) operate(r - dr[d], c - dc[d], d);
            else return;
        } else {
            d = d == 0 ? 3 : d - 1;
            if (arr[r + dr[d]][c + dc[d]] == 0) operate(r + dr[d], c + dc[d], d);
            else operate(r, c, d);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sR = Integer.parseInt(st.nextToken());
        sC = Integer.parseInt(st.nextToken());
        sD = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
