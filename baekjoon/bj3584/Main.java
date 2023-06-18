package bj3584;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            parents = new int[N + 1];

            StringTokenizer st;
            int parent, child;
            for (int i = 0; i < N-1; i++) {
                st = new StringTokenizer(br.readLine());
                parent = Integer.parseInt(st.nextToken());
                child = Integer.parseInt(st.nextToken());

                parents[child] = parent;
            }
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            ans = 0;
            boolean[] visited = new boolean[N + 1];
            dfs(num1, visited);
            dfs(num2, visited);

            bw.write(ans + "\n");
        }
        bw.close();
    }

    static void dfs(int num, boolean[] visited) {
        if (visited[num]) {
            ans = num;
            return;
        }

        if (num == 0) return;
        visited[num] = true;

        dfs(parents[num], visited);
    }
}
