package bj1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        //인접리스트 생성
        //1번 문제부터 시작
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        //진입차수 배열 생성
        inDegree = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            //위상정렬은 유향그래프, 진입차수 갱신
            adjList[from].add(to);
            inDegree[to]++;
        }

        //초기 진입차수가 0인 정점 저장
        List<Integer> start = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i]==0) {
                start.add(i);
            }
        }

        bfs(start);
        sb.append("\n");

        System.out.println(sb);
    }

    static void bfs(List<Integer> start) {
        //우선순위 큐로 쉬운문제 부터 풀 수 있도록 설정한다
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int i = 0; i < start.size(); i++) {
            pq.add(start.get(i));
        }

        int current;
        while(!pq.isEmpty()) {
            current = pq.poll();

            sb.append(current + " ");

            for (int v : adjList[current]) {
                if (--inDegree[v] == 0) {
                    pq.add(v);
                }
            }
        }

    }
}