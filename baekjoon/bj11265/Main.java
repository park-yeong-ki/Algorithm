package bj11265;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[][] adjArray;
    static int[][] mArr;
    static int[][] dist;
    static int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int num, time;
        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
        output();
    }

    static void output() {
        for (int i = 0; i < m; i++) {
            if (dist[mArr[i][0]][mArr[i][1]] <= mArr[i][2]) {
                sb.append("Enjoy other party\n");
            } else {
                sb.append("Stay here\n");
            }
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjArray = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                adjArray[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        mArr = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            mArr[i][0] = Integer.parseInt(st.nextToken());
            mArr[i][1] = Integer.parseInt(st.nextToken());
            mArr[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[n + 1];

        pq.add(new Node(start, adjArray[start][start]));
        dist[start][start] = 0;

        Node current;
        int cnt = 0;
        while (!pq.isEmpty()) {
            current = pq.poll();

            if (cnt == n) {
                return;
            }

            if (visited[current.num]) continue;
            visited[current.num] = true;
            cnt++;

            for (int i = 1; i <= n; i++) {
                if (!visited[i] && dist[start][i] > current.time + adjArray[current.num][i]) {
                    pq.add(new Node(i, current.time + adjArray[current.num][i]));
                    dist[start][i] = current.time + adjArray[current.num][i];
                }
            }
        }
    }
}
