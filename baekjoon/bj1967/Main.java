package bj1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, max;
    static class Edge{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static ArrayList<Edge>[] adjList;
    static int[] outDegree;
    public static void main(String[] args) throws IOException {
        input();
        for (int i = 1; i <= n; i++) {
            if (outDegree[i] == 0) dfs(i, new boolean[n + 1], 0);
        }
        System.out.println(max);
    }

    static void dfs(int num, boolean[] visited, int dist) {
        max = Math.max(max, dist);
        visited[num] = true;

        for (Edge edge : adjList[num]) {
            if (visited[edge.to]) continue;
            dfs(edge.to, visited, dist + edge.weight);
        }
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        outDegree = new int[n + 1];

        int from, to, weight;
        StringTokenizer st;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
            outDegree[from]++;
        }

        max = Integer.MIN_VALUE;
    }
}