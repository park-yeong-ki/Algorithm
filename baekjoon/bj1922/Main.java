package bj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //컴퓨터의 수 -> 정점
    static int N;
    //연결할 수 있는 선의 수 -> 간선
    static int M;
    //정점의 서로소 집합을 담을 배열
    static int[] parents;

    //간선 리스트 생성
    static Edge[] edgeList;

    //간선 클래스 생성
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        //가중치 순으로 정렬하기 위해 compareTo 메서드 오버라이딩
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    //정점의 최소단위 서로소 집합생성
    static void makeSet() {
        parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    //집합의 대표자를 찾을 함수
    static int findSet(int p) {
        if (p == parents[p]) return p;
        return parents[p] = findSet(parents[p]);
    }

    //합집합을 만들 함수
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        //대표자가 같은 경우 합집합을 할 수 없다.
        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N입력
        N = Integer.parseInt(br.readLine());
        //M입력
        M = Integer.parseInt(br.readLine());

        //간선 리스트 생성
        edgeList = new Edge[M];
        StringTokenizer st;
        int from, to, weight;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(from, to, weight);
        }

        //가중치 오름차순 정렬
        Arrays.sort(edgeList);

        //최소단위 서로소 집합 생성
        makeSet();

        //간선 선택
        int cnt = 0, result = 0;
        for (Edge e : edgeList) {
            //선택할 수 있는 간선일 경우
            if (union(e.from, e.to)) {
                cnt++;
                result += e.weight;
            }

            //간선의 수가 정점-1인 경우 최소신장트리 완성
            if (cnt == N-1) break;
        }

        //최소비용 출력
        System.out.println(result);
    }
}
