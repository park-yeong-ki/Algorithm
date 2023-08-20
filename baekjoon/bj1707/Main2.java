package bj1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
    static ArrayList<Integer>[] adjList;
    static int[] color;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < K; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
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

            color = new int[V + 1];
            flag = true;
            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) dfs(i, 1);
                if (!flag) break;
            }

            if (flag) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb);
    }

    static void dfs(int num, int c) {
        if (!flag) return;
        color[num] = c;

        for (int next : adjList[num]) {
            if (color[next] == 0) dfs(next, c * -1);
            else if (color[next] == c) {
                flag = false;
                return;
            }
        }
    }
}
