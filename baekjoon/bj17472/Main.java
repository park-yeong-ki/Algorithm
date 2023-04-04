package bj17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int N;
    static int M;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Integer>[] list = new ArrayList[7];

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] parents;
    static int V;

    static void makeSet() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int p) {
        if (p == parents[p]) {
            return p;
        }

        return parents[p] = findSet(parents[p]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < 7; i++) {
            list[i] = new ArrayList<>();
        }

        int num = 0;
        int cnt = 1;
        visited = new boolean[N * M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[num] && map[i][j] == 1) {
                    bfs(num, cnt++);
                }
                num++;
            }
        }

        V = cnt - 1;

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                for (int j2 = 0; j2 < list[i].size(); j2++) {
                    for (int k = 0; k < list[j].size(); k++) {
                        int r1 = list[i].get(j2) / M;
                        int c1 = list[i].get(j2) % M;
                        int r2 = list[j].get(k) / M;
                        int c2 = list[j].get(k) % M;
                        int distance = Math.abs(r1 - r2) + Math.abs(c1 - c2) - 1;

                        if (distance >= 2) {
                            boolean flag = true;
                            if (r1 == r2) {
                                for (int l = Math.min(c1, c2) + 1; l <= Math.max(c1, c2) - 1; l++) {
                                    if (map[r1][l] != 0) {
                                        flag = false;
                                        break;
                                    }
                                }
                            } else if (c1 == c2) {
                                for (int l = Math.min(r1, r2) + 1; l <= Math.max(r1, r2) - 1; l++) {
                                    if (map[l][c1] != 0) {
                                        flag = false;
                                        break;
                                    }
                                }
                            } else {
                                flag = false;
                            }

                            if (flag) {
                                edges.add(new Edge(i, j, distance));
                            }
                        }
                    }
                }
            }
        }

        Collections.sort(edges);

        parents = new int[V + 1];

        makeSet();

        int wSum = 0;
        int E = 0;
        int result = -1;
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                wSum += edge.weight;

                if (++E == V - 1) {
                    result = wSum;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static void bfs(int start, int cnt) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            int r = current / M;
            int c = current % M;
            map[r][c] = cnt;
            list[cnt].add(current);

            for (int i = 0; i < 4; i++) {
                int tR = r + dr[i];
                int tC = c + dc[i];

                if (tR >= 0 && tR < N && tC >= 0 && tC < M) {
                    if (!visited[tR * M + tC] && map[tR][tC] == 1) {
                        queue.offer(tR * M + tC);
                        visited[tR * M + tC] = true;
                    }
                }
            }
        }
    }
}