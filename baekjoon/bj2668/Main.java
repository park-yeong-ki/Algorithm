package bj2668;

import java.util.Scanner;

public class Main {
    static int[] arr;
    static int N, start, cnt;
    static boolean flag;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        cnt = 0;
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                flag = true;
                start = i;
                dfs(i, visited, 0);
            }
        }

        System.out.println(cnt);
        for (int i = 1; i <= N; i++) {
            if (visited[i]) System.out.println(i);
        }
    }

    static void dfs(int idx, boolean[] visited, int depth) {
        if (visited[idx]) {
            if (idx == start) {
                flag = false;
                cnt += depth;
            }
            return;
        }

        visited[idx] = true;
        dfs(arr[idx], visited, depth + 1);
        if (flag) visited[idx] = false;
    }
}
