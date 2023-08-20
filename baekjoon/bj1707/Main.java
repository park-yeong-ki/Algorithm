package bj1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int V;
    static boolean flag;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < K; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            int from, to;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());

                adjList[from].add(to);
                adjList[to].add(from);
            }

            flag = true;
            visited = new boolean[V + 1];
            for (int i = 1; i <= V; i++) {
                if (visited[i]) continue;
                bfs(i);
                if (!flag) break;
            }

            if (flag) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.print(sb);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();

        queue.add(start);
        visited[start] = true;

        int current, size;
        while (!queue.isEmpty()) {
            size = queue.size();
            set.addAll(queue);

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                for (int num : adjList[current]) {
                    if (set.contains(num)){
                        flag = false;
                        return;
                    }
                    if (visited[num]) continue;
                    queue.add(num);
                    visited[num] = true;
                }
            }

            set.clear();
        }
    }
}
