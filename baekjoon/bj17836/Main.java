package bj17836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, T;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;
    static class Node{
        int r, c, g;
        public Node(int r, int c, int g) {
            this.r = r;
            this.c = c;
            this.g = g;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bfs(new Node(1, 1, 0));
        System.out.println(result == 0 ? "Fail" : result);
    }

    static void bfs(Node start) {
        Queue<Node> queue = new ArrayDeque<>();

        boolean[][][] visited = new boolean[N + 1][M + 1][2];

        queue.add(start);
        visited[start.r][start.c][start.g] = true;

        Node current;
        int size, time = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int s = 0; s < size; s++) {
                current = queue.poll();

                if (time > T) return;

                if (current.r == N && current.c == M) {
                    result = time;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int tR = current.r + dr[i];
                    int tC = current.c + dc[i];

                    if (tR >= 1 && tR <= N && tC >= 1 && tC <= M) {
                        if (!visited[tR][tC][current.g]) {
                            if (map[tR][tC] == 0 || (map[tR][tC] == 1 && current.g == 1)) {
                                queue.add(new Node(tR, tC, current.g));
                                visited[tR][tC][current.g] = true;
                            } else if (map[tR][tC] == 2) {
                                queue.add(new Node(tR, tC, 1));
                                visited[tR][tC][current.g] = true;
                                visited[tR][tC][1] = true;
                            }
                        }
                    }
                }
            }
            time++;
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
