package bj6497;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        int x, y, z;
        public Edge(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.z, o.z);
        }
    }
    static List<Edge> edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            int x, y, z;
            edgeList = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                z = Integer.parseInt(st.nextToken());

                edgeList.add(new Edge(x, y, z));
                sum += z;
            }

            Collections.sort(edgeList);

            makeSet();

            int result = 0;
            int cnt = 0;
            for (Edge edge: edgeList) {
                if (union(edge.x, edge.y)) {
                    result += edge.z;
                    if (++cnt == m-1) break;
                }
            }

            bw.write(sum - result + "\n");
        }
        bw.close();
    }

    static int m;
    static int[] parents;

    static void makeSet() {
        parents = new int[m];
        for (int i = 0; i < m; i++) {
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
