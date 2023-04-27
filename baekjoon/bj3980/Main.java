package bj3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            input();
            dfs(0, 0, new boolean[11]);
            sb.append(max+"\n");
        }
        System.out.println(sb);
    }
    static void input() throws IOException {
        StringTokenizer st;
        max = Integer.MIN_VALUE;
        arr = new int[11][11];
        for (int i = 0; i < 11; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 11; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    static void dfs(int idx, int ability, boolean[] visited) {
        if (idx == 11) {
            max = Math.max(max, ability);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (!visited[i] && arr[i][idx] != 0) {
                visited[i] = true;
                dfs(idx + 1, ability + arr[i][idx], visited);
                visited[i] = false;
            }
        }
    }
}
