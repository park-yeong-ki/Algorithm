package bj2644;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_2 {
    static ArrayList<Integer>[] adjList;
    static int end;
    static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int start = sc.nextInt();
        end = sc.nextInt();
        int m = sc.nextInt();

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i < m; i++) {
            from = sc.nextInt();
            to = sc.nextInt();

            adjList[from].add(to);
            adjList[to].add(from);
        }

        result = -1;
        dfs(start, new boolean[n + 1], 0);

        System.out.println(result);
    }

    static void dfs(int num, boolean[] visited, int depth) {
        if (num == end) {
            result = depth;
            return;
        }

        visited[num] = true;

        for (int p : adjList[num]) {
            if (!visited[p]) {
                dfs(p, visited, depth + 1);
            }
        }
    }
}
