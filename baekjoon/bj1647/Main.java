package bj1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //집의 개수 -> 정점의 개수
    static int N;
    //길의 개수 -> 간선의 개수
    static int M;
    //서로소 집합에서 부모의 인덱스를 저장할 배열
    static int[] parents;
    static Edge[] edgeList;

    //간선 클래스 생성
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        //가중치 순으로 정렬하기 위해 메서드 오버라이딩
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    //최소 단위 서로소 집합 생성
    static void makeSet() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    //집합의 대표자를 찾아주는 함수
    static int findSet(int p) {
        if (p == parents[p]) return p;
        //경로압축
        return parents[p] = findSet(parents[p]);
    }

    //합집합을 만드는 합수
    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        //싸이클 발생 방지
        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //간선리스트 생성
        edgeList = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        //간선리스트 가중치 순으로 오름차순 정렬
        Arrays.sort(edgeList);

        //최소단위 서로소 집합 생성
        makeSet();

        //간선 선택
        //최소 신장 트리에서 간선 하나를 제거하면 두 개의 마을로 분리되는 것을 이용한다.
        //즉 N-2개의 간선의 가중치 합을 구하면 두 개의 마을의 유지비 합이 최소가 된다.
        int count = 0, result = 0;
        for (Edge e: edgeList) {
            if (union(e.from, e.to)) {
                count++;
                result += e.weight;
            }

            if (count == N-2) break;
        }

        //출력
        System.out.println(result);
    }
}
