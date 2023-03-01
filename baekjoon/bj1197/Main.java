package bj1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //크루스칼 알고리즘 사용
    //정점의 수
    static int V;
    //간선의 개수
    static int E;

    //간선 리스트
    static Edge[] edgeList;
    //정점에 대한 서로소 집합 배열
    static int[] parents;

    //간선 클래스 생성
    static class Edge implements Comparable<Edge> {
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

    //최소단위 서로소 집합 생성
    static void makeSet() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    //집합의 대표자를 찾아주는 함수
    static int findSet(int p) {
        if (p == parents[p]) {
            return p;
        }
        //경로 압축
        return parents[p] = findSet(parents[p]);
    }

    //합집합을 만드는 함수
    static boolean union(int a, int b) {
        //각 집합의 대표자 찾기
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        //싸이클이 생기는 경우 방지
        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //V, E 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        //간선리스트 등록
        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        //최소 단위 서로소 집합 생성
        makeSet();

        //간선리스트를 가중치 기준으로 오름차순 정렬
        Arrays.sort(edgeList);

        //간선 선택
        int count = 0, result = 0;
        for (int i = 0; i < edgeList.length; i++) {
            if (union(edgeList[i].from, edgeList[i].to)) {
                count++;
                result += edgeList[i].weight;
            }

            //정점-1개 만큼 간선을 선택한 경우 최소 신장트리 완성
            if (count == V-1) break;
        }

        //최소 스패닝 트리의 가중치 출력
        System.out.println(result);
    }
}
