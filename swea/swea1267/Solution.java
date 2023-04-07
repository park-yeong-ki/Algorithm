package swea1267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int[] inDegree;
    static ArrayList<Integer>[] adjList;
    static int V;
    static int E;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            //1번 정점부터 시작
            adjList = new ArrayList[V+1];
            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            //진입차수 저장배열
            inDegree = new int[V+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                //유향그래프, 진입차수 기록
                adjList[from].add(to);
                inDegree[to]++;
            }

            //진입차수가 0인 정점구하기
            List<Integer> start = new ArrayList<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    start.add(i);
                }
            }

            sb.append("#" + test_case + " ");
            bfs(start);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(List<Integer> start) {
        Queue<Integer> queue = new ArrayDeque<Integer>();

        //방문배열 필요?

        //진입 차수가 0인 정점 입력
        for (int i = 0; i < start.size(); i++) {
            queue.offer(start.get(i));
        }

        int current;
        while(!queue.isEmpty()) {
            current = queue.poll();

            sb.append(current + " ");

            for (int v : adjList[current]) {
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
    }
}