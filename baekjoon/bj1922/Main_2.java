package bj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2 {
    static int N;
    static int M;

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] parents;
    static void makeSet(){
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int num) {
        if(num == parents[num]) {
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


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        //간선리스트 생성
        ArrayList<Edge> edgeList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from, to, weight));
        }

        //가중치 순으로 오름차순 정렬
        Collections.sort(edgeList);

        parents = new int[N+1];

        makeSet();

        int cnt = 0, result = 0;
        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;

                //간선이 N-1개 선택되어 최소신장트리가 완성된 경우
                if(++cnt == N-1) {
                    break;
                }
            }
        }

        System.out.println(result);

    }
}