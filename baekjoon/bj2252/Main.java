package bj2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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

        //인접리스트 생성 -> 1번부터 N번
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        //진입차수 행렬생성
        inDegree = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            //유향그래프
            adjList[from].add(to);
            //진입차수 갱신
            inDegree[to]++;
        }

        //큐에 넣을 진입차수 0인 정점 구하기
        List<Integer> start = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                start.add(i);
            }
        }

        bfs(start);
        sb.append("\n");

        System.out.println(sb);
    }

    static void bfs(List<Integer> start) {
        Queue<Integer> queue = new ArrayDeque<>();

        //방문배열 불필요

        //초기 진입차수 0인 정점 넣기
        for (int i = 0; i < start.size(); i++) {
            queue.offer(start.get(i));
        }

        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            sb.append(current + " ");

            //연결된 정점의 진입차수를 1씩 감소시킨 후 진입차수가 0인 정점을 큐에 넣는다
            for (int v : adjList[current]) {
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
    }
}