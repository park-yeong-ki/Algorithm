package B형특강_2회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 공통조상 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E, num1, num2, LCA, cnt;
    static int[] parents;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            dfs(num1);
            dfs(num2);
            bfs(LCA);
            sb.append("#" + test_case + " " + LCA + " " + cnt + "\n");
        }
        System.out.print(sb);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);

        int current;
        cnt = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            cnt++;

            for (int node : adjList[current]) {
                queue.add(node);
            }
        }
    }

    static void dfs(int num) {
        if (visited[num]) {
            LCA = num;
            return;
        }
        if (num == 0) return;
        visited[num] = true;

        dfs(parents[num]);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        num1 = Integer.parseInt(st.nextToken());
        num2 = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        int parent, child;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < E; i++) {
            parent = Integer.parseInt(st.nextToken());
            child = Integer.parseInt(st.nextToken());

            parents[child] = parent;
            adjList[parent].add(child);
        }

        visited = new boolean[V + 1];
        LCA = 0;
    }
}
