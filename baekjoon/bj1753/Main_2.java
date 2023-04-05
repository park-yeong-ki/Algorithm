package bj1753;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2 {
    static class Edge implements Comparable<Edge> {

        int num, weight;

        public Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int V;
    static ArrayList<Edge>[] adjList;
    static int[] dist;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
        }

        dijkstra(start);
        for (int i = 1; i <= V; i++) {
            bw.write(dist[i] == INF ? "INF\n" : dist[i] +"\n");
        }

        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        Edge current;
        while (!pq.isEmpty()) {
            current = pq.poll();

            if (visited[current.num]) continue;
            visited[current.num] = true;

            for (int i = 0; i < adjList[current.num].size(); i++) {
                Edge edge = adjList[current.num].get(i);
                if (!visited[edge.num] && dist[edge.num] > current.weight + edge.weight) {
                    pq.offer(new Edge(edge.num, current.weight + edge.weight));
                    dist[edge.num] = current.weight + edge.weight;
                }
            }
        }
    }
}
