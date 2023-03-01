package bj4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    //정점의 개수
    static int n;
    //각 인덱스의 부모를 담을 배열
    static int[] parents;
    //간선리스트 생성
    static ArrayList<Edge> edgeList;

    //간선 클래스 생성
    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        //가중치순으로 오름차순할 수 있도록 메서드 오버라이딩
        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    //최소 단위 서로소 집합 생성
    static void makeSet() {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    //집합의 대표를 찾는 함수
    static int findSet(int p) {
        if (p == parents[p]) {
            return p;
        }
        return parents[p] = findSet(parents[p]);
    }

    //합집합을 만들어주는 함수
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        //싸이클 방지
        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //별의 개수 입력
        n = Integer.parseInt(br.readLine());

        //별 위치를 담을 배열 생성
        double[][] stars = new double[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        //간선리스트 생성
        edgeList = new ArrayList<>();

        double dx, dy, weight;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                dx = stars[i][0] - stars[j][0];
                dy = stars[i][1] - stars[j][1];

                weight = Math.sqrt(dx * dx + dy * dy);
                edgeList.add(new Edge(i, j, weight));
            }
        }

        //최소 단위 서로소 집합 생성
        makeSet();

        //간선리스트 정렬
        Collections.sort(edgeList);

        //간선 선택
        int count = 0;
        double result = 0;
        for (Edge e : edgeList) {
            if (union(e.from, e.to)) {
                count++;
                result += e.weight;
            }

            if (count == n-1) break;
        }

        //최소 비용 출력
        System.out.printf("%.2f", result);
    }
}
