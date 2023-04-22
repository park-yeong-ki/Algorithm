package bj2606;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_2 {
    static ArrayList<Integer>[] adjList;
    static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }
        int from, to;
        for (int i = 0; i < E; i++) {
            from = sc.nextInt();
            to = sc.nextInt();

            adjList[from].add(to);
            adjList[to].add(from);
        }

        dfs(1, new boolean[V + 1]);
        System.out.println(cnt);
    }

    static void dfs(int num, boolean[] visited) {
        visited[num] = true;

        for (int virus: adjList[num]) {
            if (!visited[virus]) {
                cnt++;
                dfs(virus, visited);
            }
        }
    }
}
