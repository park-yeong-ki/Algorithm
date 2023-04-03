package swea1251;

import java.io.*;
import java.util.*;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    static List<Edge> edgeList;

    static int N;
    static int[] parents;
    static void makeSet() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int num) {
        if (parents[num] == num) {
            return num;
        }

        return parents[num] = findSet(parents[num]);
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            edgeList = new ArrayList<>();
            parents = new int[N];

            int[] X = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Integer.parseInt(st.nextToken());
            }

            int[] Y = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Y[i] = Integer.parseInt(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());

            for (int i = 0; i < N-1; i++) {
                for (int j = i + 1; j < N; j++) {
                    edgeList.add(new Edge(i, j, Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2)));
                }
            }

            Collections.sort(edgeList);

            makeSet();

            int cnt = 0;
            double result = 0;
            for (int i = 0; i < edgeList.size(); i++) {
                int from = edgeList.get(i).from;
                int to = edgeList.get(i).to;
                double weight = edgeList.get(i).weight;

                if (union(from, to)) {
                    result += weight;

                    if (++cnt == N - 1) {
                        break;
                    }
                }
            }

            bw.write("#" + test_case + " " + Math.round(E * result) + "\n");
        }
        bw.close();
    }
}
