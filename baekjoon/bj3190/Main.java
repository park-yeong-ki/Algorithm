package bj3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, L, ans;
    static int[][] map;
    static HashMap<Integer, Character> dInfo;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Queue<Point> snake;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 1, 1, 0);
        System.out.println(ans);
    }

    static void dfs(int time, int r, int c, int d) {
        int tD = updateDirection(time, d);
        int tR = r + dr[tD];
        int tC = c + dc[tD];

        if (tR < 1 || tR > N || tC < 1 || tC > N || map[tR][tC] == 1){
            ans = time + 1;
            return;
        }

        if (map[tR][tC] != 2) {
            Point first = snake.poll();
            map[first.r][first.c] = 0;
        }
        map[tR][tC] = 1;
        snake.add(new Point(tR, tC));

        dfs(time + 1, tR, tC, tD);
    }

    static int updateDirection(int time, int direction) {
        int temp = direction;
        if (dInfo.containsKey(time)) {
            if (dInfo.get(time) == 'L'){
                temp--;
                if (temp == -1) temp = 3;
            } else if (dInfo.get(time) == 'D'){
                temp++;
                if (temp == 4) temp = 0;
            }
        }

        return temp;
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        map[1][1] = 1;
        snake = new LinkedList<>();
        snake.add(new Point(1, 1));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int row, column;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            column = Integer.parseInt(st.nextToken());
            map[row][column] = 2;
        }

        L = Integer.parseInt(br.readLine());
        dInfo = new HashMap<>();
        int time;
        char direction;
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            time = Integer.parseInt(st.nextToken());
            direction = st.nextToken().charAt(0);
            dInfo.put(time, direction);
        }

        ans = 0;
    }
}
