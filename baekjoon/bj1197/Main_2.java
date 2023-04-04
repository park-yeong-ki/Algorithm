package bj1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] abjList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            abjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight= Integer.parseInt(st.nextToken());

            abjList[from].add(new Edge(to, weight));
            abjList[to].add(new Edge(from, weight));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        boolean[] visited = new boolean[V + 1];

        Edge current;
        long result = 0, cnt = 0;
        while (!pq.isEmpty() && cnt < V) {
            current = pq.poll();

            if (visited[current.num])continue;

            visited[current.num] = true;
            result += current.weight;
            cnt++;

            for (Edge edge : abjList[current.num]) {
                pq.offer(edge);
            }
        }

        System.out.println(result);
    }
}