package bj14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, r;
    static int[] item;
    static int max = Integer.MIN_VALUE;
    static class Edge implements Comparable<Edge>{
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static ArrayList<Edge>[] adjList;
    public static void main(String[] args) throws IOException {
        input();
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
        System.out.println(max);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        int from, to, weight;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[n + 1];

        pq.add(new Edge(start, 0));

        Edge current;
        int cnt = 0;
        while (!pq.isEmpty()) {
            current = pq.poll();

            if (visited[current.to]) continue;
            visited[current.to] = true;

            if (current.weight <= m) {
                cnt += item[current.to];
            }

            for (Edge e: adjList[current.to]) {
                if (!visited[e.to]) {
                    pq.add(new Edge(e.to, current.weight + e.weight));
                }
            }
        }
        max = Math.max(max, cnt);
    }
}
