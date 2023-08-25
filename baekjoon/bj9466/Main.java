package bj9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, start, cnt, prev;
    static int[] arr;
    static boolean[] prevVisited, visited;
    static boolean flag;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            input();

            for (int i = 1; i <= n; i++) {
                flag = false;
                prev = 0;
                start = i;
                dfs(i);
            }

            sb.append(n - cnt + "\n");
        }
        System.out.print(sb);
    }

    static void dfs(int num) {
        if (prevVisited[num]) {
            return;
        }

        if (visited[num]) {
            flag = true;
            prev = num;
            return;
        }

        visited[num] = true;
        dfs(arr[num]);
        if (flag) cnt++;
        if (prev == num) flag = false;
        visited[num] = false;
        prevVisited[num] = true;
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        prevVisited = new boolean[n + 1];
        visited = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        cnt = 0;
    }
}
