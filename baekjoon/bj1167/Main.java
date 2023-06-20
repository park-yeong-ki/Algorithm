package bj1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, max, start;
    static class Edge{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static ArrayList<Edge>[] adjList;
    public static void main(String[] args) throws IOException {
        input();
        dfs(1, new boolean[V + 1], 0);
        max = 0;
        dfs(start, new boolean[V + 1], 0);
        System.out.println(max);
    }

    static void dfs(int num, boolean[] visited, int dist) {
        if (max < dist) {
            max = dist;
            start = num;
        }
        visited[num] = true;

        for (Edge edge : adjList[num]) {
            if (visited[edge.to]) continue;
            dfs(edge.to, visited, dist + edge.weight);
        }
    }

    static void input() throws IOException {
        V = Integer.parseInt(br.readLine());
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int from, to, weight;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            while (true) {
                to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                weight = Integer.parseInt(st.nextToken());
                adjList[from].add(new Edge(to, weight));
            }
        }

        max = Integer.MIN_VALUE;
        start = 0;
    }
}