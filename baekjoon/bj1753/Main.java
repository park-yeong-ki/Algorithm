package bj1753;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //간선 클래스 생성
    static class Edge{
        int to, weight;

        public Edge(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //정점 개수 V, 간선 개수 E 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        //시작점 입력
        int start =Integer.parseInt(br.readLine());

        //인접 리스트 생성
        ArrayList<Edge>[] abjList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            abjList[i] = new ArrayList<>();
        }


        //요소 입력
        int u,v,w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            abjList[u].add(new Edge(v, w));
        }

        //시작점에서 정점 거리 배열 생성
        int[] distance = new int[V+1];
        int imv = Integer.MAX_VALUE;
        Arrays.fill(distance, imv);

        //방문 배열 설정
        boolean[] visited = new boolean[V+1];

        //시작점에서 시작점까지의 거리 입력
        distance[start] = 0;

        //정점 수만큼 반복문
        int current, min;
        for (int i = 1; i <= V; i++) {
            current = -1;
            min = imv;

            //시작점과 가장 가까운 정점 선택
            for (int j = 1; j <= V; j++) {
                if (!visited[j] && min > distance[j]) {
                    current = j;
                    min = distance[j];
                }
            }

            //선택할 정점이 없다면 반복문 종료
            if (current == -1) break;

            //선택한 정점 방문표시
            visited[current] = true;

            //선택한 정점에서 인접 정점까지의 거리갱신
            for (int j = 0; j < abjList[current].size(); j++) {
                if (!visited[abjList[current].get(j).to] && min + abjList[current].get(j).weight < distance[abjList[current].get(j).to]) {
                    distance[abjList[current].get(j).to] = min + abjList[current].get(j).weight;
                }
            }
        }

        //출력
        for (int i = 1; i <= V; i++) {
            if (distance[i] != imv) {
                bw.write(distance[i] + "\n");
            }else {
                bw.write("INF" + "\n");
            }
        }
        bw.close();
    }
}