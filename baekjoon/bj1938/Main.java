package bj1938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, result;
    static char[][] map;
    static class Node {
        int r, c, d;
        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static Node start, end;
    static int[] dr = {-1, 1, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[N][N][2];

        queue.add(start);
        visited[start.r][start.c][start.d] = true;

        int size, cnt = 0;
        Node current;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.r == end.r && current.c == end.c && current.d == end.d) {
                    result = cnt;
                    return;
                }

                for (int j = 0; j < 5; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    boolean flag = false;
                    if (current.d == 0) { //세로인 경우
                        switch (j) {
                            case 0:
                                if (tR - 1 < 0 || map[tR-1][tC] == '1') flag = true;
                                break;
                            case 1:
                                if (tR + 1 >= N || map[tR+1][tC] == '1') flag = true;
                                break;
                            case 2:
                                if (tC < 0 || map[tR-1][tC] == '1' || map[tR][tC] == '1' || map[tR+1][tC] == '1') flag = true;
                                break;
                            case 3:
                                if (tC >= N || map[tR-1][tC] == '1' || map[tR][tC] == '1' || map[tR+1][tC] == '1') flag = true;
                                break;
                        }
                    } else { //가로인 경우
                        switch (j) {
                            case 0:
                                if (tR < 0 || map[tR][tC-1] == '1' || map[tR][tC] == '1' || map[tR][tC+1] == '1') flag = true;
                                break;
                            case 1:
                                if (tR >= N || map[tR][tC-1] == '1' || map[tR][tC] == '1' || map[tR][tC+1] == '1') flag = true;
                                break;
                            case 2:
                                if (tC - 1 < 0 || map[tR][tC-1] == '1') flag = true;
                                break;
                            case 3:
                                if (tC + 1 >= N || map[tR][tC+1] == '1') flag = true;
                                break;
                        }
                    }

                    int d = current.d;
                    if (j == 4) {
                        outer:
                        for (int k = -1; k <= 1; k++) {
                            for (int l = -1; l <= 1; l++) {
                                if (k == 0 && l == 0) continue;
                                if (tR + k < 0 || tR + k >= N || tC + l < 0 || tC + l >= N) {
                                    flag = true;
                                    break outer;
                                }
                                if (map[tR + k][tC + l] == '1') {
                                    flag = true;
                                    break outer;
                                }
                            }
                        }
                        if (!flag) d = d == 0 ? 1 : 0;
                    }

                    if (flag) continue;
                    if (visited[tR][tC][d]) continue;
                    queue.add(new Node(tR, tC, d));
                    visited[tR][tC][d] = true;
                }
            }
            cnt++;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int sR = -1, sC = -1, eR = -1, eC = -1;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B' && start == null) {
                    if (sR == -1 && sC == -1) {
                        sR = i;
                        sC = j;
                    } else {
                        if (sR + 1 == i) start = new Node(i, j, 0);
                        else start = new Node(i, j, 1);
                    }
                }
                if (map[i][j] == 'E' && end == null) {
                    if (eR == -1 && eC == -1) {
                        eR = i;
                        eC = j;
                    } else {
                        if (eR + 1 == i) end = new Node(i, j, 0);
                        else end = new Node(i, j, 1);
                    }
                }
            }
        }
        result = 0;
    }
}
