package bj11404;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
    static ArrayList<Node>[] adjList;
    static class Node implements Comparable<Node>{
        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int[][] dist;
    static int INF, n;

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
        output();
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) bw.write(0 + " ");
                else bw.write(dist[i][j] + " ");
            }
            bw.newLine();
        }
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        Arrays.fill(dist[start], INF);

        Node current;
        while (!pq.isEmpty()) {
            current = pq.poll();

            if (dist[start][current.to] <= current.weight) continue;
            dist[start][current.to] = current.weight;

            for (Node node : adjList[current.to]) {
                if (dist[start][node.to] > current.weight + node.weight)
                    pq.add(new Node(node.to, current.weight + node.weight));
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        INF = 100000 * n + 1;

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int from, to, weight;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
        }

        dist = new int[n + 1][n + 1];
    }
}
