package swea3124;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    //간선 클래스 생성
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        //가중치에 대해 오름차순 정렬을 하기위한 메서드 오버라이딩
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int V, E;
    static Edge[] edgeList;
    static int[] parents;

    //최소 단위 서로소 집합 생성
    static void makeSet() {
        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    //집합의 대표자를 찾는 함수
    static int findSet(int a) {
        if (a == parents[a]) {
            return a;
        }
        return parents[a] = findSet(parents[a]);
    }

    //합집합을 만드는 함수
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        //대표자가 같다면 합칠 수 없다
        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //정점의 개수 V, 간선의 개수 E 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            //간선에 대한 정보 입력
            edgeList = new Edge[E];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            //가중치에 대한 오름차순 정렬
            Arrays.sort(edgeList);

            //최소 단위 서로소 집합 생성
            makeSet();

            long result = 0, count = 0;
            //주어진 간선만큼 반복
            for (Edge edge: edgeList) {
                //합집합으로 만들 수 있는 경우
                if (union(edge.from, edge.to)) {
                    //가중치의 합을 더한다
                    result += edge.weight;
                    //간선의 개수가 V-1개라면 최소 신장 트리를 완성한 것이므로 종료
                    if (++count == V - 1) {
                        break;
                    }
                }
            }

            //출력
            bw.write("#" + test_case + " " + result + "\n");
        }

        bw.close();
    }
}
