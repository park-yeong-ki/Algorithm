package bj1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, E;
    static class Node implements Comparable<Node>{
        int to, dist;
        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static ArrayList<Node>[] adjList;
    static int num1, num2, d;
    public static void main(String[] args) throws IOException {
        input();
        int result = -1;

        d = 0;
        if (dijkstra(1, num1) && dijkstra(num1, num2) && dijkstra(num2, N)) {
            result = d;
        }
        d = 0;
        if (dijkstra(1, num2) && dijkstra(num2, num1) && dijkstra(num1, N)) {
            if (result != -1) result = Math.min(result, d);
            else result = d;
        }

        System.out.println(result);
    }

    static boolean dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N + 1];

        pq.add(new Node(start, 0));

        Node current;
        while (!pq.isEmpty()) {
            current = pq.poll();

            if (current.to == end) {
                d += current.dist;
                return true;
            }

            if (visited[current.to]) continue;
            visited[current.to] = true;

            for (Node node : adjList[current.to]) {
                if (!visited[node.to]) {
                    pq.add(new Node(node.to, current.dist + node.dist));
                }
            }
        }

        return false;
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from, to, dist;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, dist));
            adjList[to].add(new Node(from, dist));
        }

        st = new StringTokenizer(br.readLine());
        num1 = Integer.parseInt(st.nextToken());
        num2 = Integer.parseInt(st.nextToken());
    }
}
