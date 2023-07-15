package bj13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
    static int N, M, ans;
    static char[][] map;
    static class Point{
        int rR, rC, bR, bC;
        public Point(int rR, int rC, int bR, int bC){
            this.rR = rR;
            this.rC = rC;
            this.bR = bR;
            this.bC = bC;
        }
    }
    static Point start;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(ans);
    }

    static void bfs(){
        ans = -1;
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        queue.add(start);
        visited[start.rR][start.rC][start.bR][start.bC] = true;

        Point current;
        int size, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            if (cnt > 10) break;

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (map[current.bR][current.bC] == 'O') continue;

                if (map[current.rR][current.rC] == 'O') {
                    ans = cnt;
                    return;
                }

                //북쪽인 경우
                if (current.rC == current.bC && current.rR < current.bR) { //붉은 공 먼저 이동
                    int[] arr = move(current.rR, current.rC, current.bR, current.bC, 0);

                    if (!visited[arr[0]][arr[1]][arr[2]][arr[3]]){
                        queue.add(new Point(arr[0], arr[1], arr[2], arr[3]));
                        visited[arr[0]][arr[1]][arr[2]][arr[3]] = true;
                    }
                } else { //파란공 먼저 이동
                    int[] arr = move(current.bR, current.bC, current.rR, current.rC, 0);

                    if (!visited[arr[2]][arr[3]][arr[0]][arr[1]]){
                        queue.add(new Point(arr[2], arr[3], arr[0], arr[1]));
                        visited[arr[2]][arr[3]][arr[0]][arr[1]] = true;
                    }
                }

                //남쪽인 경우
                if (current.rC == current.bC && current.rR > current.bR) { //붉은 공 먼저 이동
                    int[] arr = move(current.rR, current.rC, current.bR, current.bC, 1);

                    if (!visited[arr[0]][arr[1]][arr[2]][arr[3]]){
                        queue.add(new Point(arr[0], arr[1], arr[2], arr[3]));
                        visited[arr[0]][arr[1]][arr[2]][arr[3]] = true;
                    }
                } else { //파란공 먼저 이동
                    int[] arr = move(current.bR, current.bC, current.rR, current.rC, 1);

                    if (!visited[arr[2]][arr[3]][arr[0]][arr[1]]){
                        queue.add(new Point(arr[2], arr[3], arr[0], arr[1]));
                        visited[arr[2]][arr[3]][arr[0]][arr[1]] = true;
                    }
                }

                //서쪽인경우
                if (current.rR == current.bR && current.rC < current.bC) { //붉은 공 먼저 이동
                    int[] arr = move(current.rR, current.rC, current.bR, current.bC, 2);

                    if (!visited[arr[0]][arr[1]][arr[2]][arr[3]]){
                        queue.add(new Point(arr[0], arr[1], arr[2], arr[3]));
                        visited[arr[0]][arr[1]][arr[2]][arr[3]] = true;
                    }
                } else { //파란공 먼저 이동
                    int[] arr = move(current.bR, current.bC, current.rR, current.rC, 2);

                    if (!visited[arr[2]][arr[3]][arr[0]][arr[1]]){
                        queue.add(new Point(arr[2], arr[3], arr[0], arr[1]));
                        visited[arr[2]][arr[3]][arr[0]][arr[1]] = true;
                    }
                }

                //동쪽인 경우
                if (current.rR == current.bR && current.rC > current.bC) { //붉은 공 먼저 이동
                    int[] arr = move(current.rR, current.rC, current.bR, current.bC, 3);

                    if (!visited[arr[0]][arr[1]][arr[2]][arr[3]]){
                        queue.add(new Point(arr[0], arr[1], arr[2], arr[3]));
                        visited[arr[0]][arr[1]][arr[2]][arr[3]] = true;
                    }
                } else {  //파란공 먼저 이동
                    int[] arr = move(current.bR, current.bC, current.rR, current.rC, 3);

                    if (!visited[arr[2]][arr[3]][arr[0]][arr[1]]){
                        queue.add(new Point(arr[2], arr[3], arr[0], arr[1]));
                        visited[arr[2]][arr[3]][arr[0]][arr[1]] = true;
                    }
                }
            }
            cnt++;
        }
    }

    static int[] move(int r1, int c1, int r2, int c2, int d) {
        int tr1 = r1;
        int tc1 = c1;
        int tr2 = r2;
        int tc2 = c2;

        while (true) {
            tr1 += dr[d];
            tc1 += dc[d];

            if (map[tr1][tc1] == 'O') break;
            if ((map[tr1][tc1] == '#') || (tr1 == tr2 && tc1 == tc2)) {
                tr1 -= dr[d];
                tc1 -= dc[d];
                break;
            }
        }

        while (true) {
            tr2 += dr[d];
            tc2 += dc[d];

            if (map[tr2][tc2] == 'O') break;
            if ((map[tr2][tc2] == '#') || (tr2 == tr1 && tc2 == tc1)) {
                tr2 -= dr[d];
                tc2 -= dc[d];
                break;
            }
        }

        return new int[]{tr1, tc1, tr2, tc2};
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        start = new Point(0, 0, 0, 0);
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B') {
                    start.bR = i;
                    start.bC = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'R') {
                    start.rR = i;
                    start.rC = j;
                    map[i][j] = '.';
                }
            }
        }
    }
}
