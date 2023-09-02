package bj17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] map, population;
    static int[] border, count;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        perm(0);
        System.out.println(ans);
    }

    static void populationCnt() {
        count = new int[6];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                count[map[i][j]] += population[i][j];
            }
        }
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 1 || tR > N || tC < 1 || tC > N) continue;
                if (map[tR][tC] != 5 && map[tR][tC] != 0) continue;
                if (visited[tR][tC]) continue;

                queue.add(new Point(tR, tC));
                map[tR][tC] = 5;
                visited[tR][tC] = true;
            }
        }
    }

    static void divide() {
        map = new int[N + 1][N + 1];
        //1
        for (int i = 0; i <= border[0]; i++) {
            int tX = border[2] + i;
            int tY = border[3] - i;
            map[tX][tY] = 5;
        }

        //2
        for (int i = 0; i <= border[1]; i++) {
            int tX = border[2] + i;
            int tY = border[3] + i;
            map[tX][tY] = 5;
        }

        //3
        for (int i = 0; i <= border[1]; i++) {
            int tX = border[2] + border[0] + i;
            int tY = border[3] - border[0] + i;
            map[tX][tY] = 5;
        }

        //4
        for (int i = 0; i <= border[0]; i++) {
            int tX = border[2] + border[1] + i;
            int tY = border[3] + border[1] - i;
            map[tX][tY] = 5;
        }

        //1번 선거구
        for (int i = 1; i < border[2] + border[0]; i++) {
            for (int j = 1; j <= border[3]; j++) {
                if (map[i][j] == 5) break;
                map[i][j] = 1;
            }
        }

        //2번 선거구
        for (int i = 1; i <= border[2] + border[1]; i++) {
            for (int j = N; j >= border[3] + 1; j--) {
                if (map[i][j] == 5) break;
                map[i][j] = 2;
            }
        }

        //3번 선거구
        for (int i = border[2] + border[0]; i <= N; i++) {
            for (int j = 1; j < border[3] - border[0] + border[1]; j++) {
                if (map[i][j] == 5) break;
                map[i][j] = 3;
            }
        }

        //4번 선거구
        for (int i = border[2] + border[1] + 1; i <= N; i++) {
            for (int j = N; j >= border[3] - border[0] + border[1]; j--) {
                if (map[i][j] == 5) break;
                map[i][j] = 4;
            }
        }

        bfs(new Point(border[2], border[3]));
    }


    static void perm(int idx) {
        if (idx == 4) {
            divide();
            populationCnt();

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= 5; i++) {
                max = Math.max(count[i], max);
                min = Math.min(count[i], min);
            }
            ans = Math.min(ans, max - min);

            return;
        }

        //d1, d2, x, y 순으로 뽑는다.
        for (int i = 1; i < N - 1; i++) {
            if (idx == 1 && border[0] + i > N - 1) continue;
            if (idx == 2 && border[0] + border[1] + i > N) continue;
            if (idx == 3 && (i - border[0] < 1) || (i + border[1] > N)) continue;
            border[idx] = i;
            perm(idx + 1);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        border = new int[4];
        ans = Integer.MAX_VALUE;
    }
}
