package bj1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, X;
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
    static ArrayList<Node>[] adjList;
    static int[] dist;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 1; i <= N; i++) {
            dijkstra(i, X);
            dijkstra(X, i);
        }
        System.out.println(max);
    }

    static void dijkstra(int num, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N+1];

        pq.add(new Node(num, 0));

        Node current;
        while(!pq.isEmpty()) {
            current = pq.poll();

            if (num != X && current.to == end) {
                dist[num] += current.weight;
                return;
            }

            if (num == X && current.to == end) {
                dist[end] += current.weight;
                max = Math.max(max, dist[end]);
                return;
            }

            if(visited[current.to]) continue;
            visited[current.to] = true;

            for (Node node : adjList[current.to]) {
                if(visited[node.to]) continue;
                pq.add(new Node(node.to, current.weight + node.weight));
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from, to ,weight;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
        }

        dist = new int[N+1];
    }
}