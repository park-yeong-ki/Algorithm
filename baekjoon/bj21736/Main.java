package bj21736;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt;
    static char[][] campus;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Point doyeon;
    public static void main(String[] args) throws IOException {
        input();
        dfs(doyeon, new boolean[N][M]);
        if (cnt == 0) System.out.println("TT");
        else System.out.println(cnt);
    }

    static void dfs(Point point, boolean[][] visited) {
        if (campus[point.r][point.c] == 'P') {
            cnt++;
        }
        visited[point.r][point.c] = true;

        for (int i = 0; i < 4; i++) {
            int tR = point.r + dr[i];
            int tC = point.c + dc[i];

            if (tR < 0 || tR >= N || tC < 0 || tC >= M) continue;
            if (visited[tR][tC] || campus[tR][tC] == 'X') continue;
            dfs(new Point(tR, tC), visited);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        campus = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                campus[i][j] = str.charAt(j);
                if (campus[i][j] == 'I') doyeon = new Point(i, j);
            }
        }
        cnt = 0;
    }
}
