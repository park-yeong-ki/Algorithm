package bj1194;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Node{
        int r, c, key;

        public Node(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }
    }
    static Node start;
    static int N, M, result;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[1 << 6][N][M];

        queue.add(start);
        visited[start.key][start.r][start.c] = true;

        result = -1;
        Node current;
        int size, time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (map[current.r][current.c] == '1') {
                    result = time;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= N || tC < 0 || tC >= M) continue;
                    if (visited[current.key][tR][tC]) continue;
                    if (map[tR][tC] == '#') continue;
                    if (map[tR][tC] - 'A' >= 0 && map[tR][tC] - 'A' <= 5
                    && (current.key & (1 << (map[tR][tC] - 'A'))) == 0) continue;

                    if (map[tR][tC] - 'a' >= 0 && map[tR][tC] - 'a' <= 5) {
                        queue.add(new Node(tR, tC, (current.key | (1 << map[tR][tC] - 'a'))));
                        visited[(current.key | (1 << map[tR][tC] - 'a'))][tR][tC] = true;
                    } else {
                        queue.add(new Node(tR, tC, current.key));
                        visited[current.key][tR][tC] = true;
                    }
                }
            }

            time++;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') start = new Node(i, j, 0);
            }
        }
    }
}
