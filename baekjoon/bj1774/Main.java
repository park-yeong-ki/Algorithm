package bj1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        int n1, n2;
        double d;
        public Edge(int n1, int n2, double d) {
            this.n1 = n1;
            this.n2 = n2;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.d, o.d);
        }
    }
    static List<Edge> edgeList;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        edgeList = new ArrayList<>();
        for (int i = 1; i <= N-1; i++) {
            for (int j = i+1; j <= N; j++) {
                double dx = Math.abs(arr[i][0] - arr[j][0]);
                double dy = Math.abs(arr[i][1] - arr[j][1]);
                double d = Math.sqrt(dx * dx + dy * dy);
                edgeList.add(new Edge(i, j, d));
            }
        }

        makeSet();
        int o = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
                o++;
            }
        }

        Collections.sort(edgeList);

        double result = 0;
        int cnt = 0;
        for (Edge edge: edgeList) {
            if (union(edge.n1, edge.n2)) {
                result += edge.d;
                if (++cnt == N - 1 - o) {
                    break;
                }
            }
        }

        System.out.printf("%.2f", result);
    }

    static int[] parents;
    static void makeSet() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
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
}
