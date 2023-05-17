package bj11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2 {
    static ArrayList<Integer>[] adjList;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i, visited);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void bfs(int start, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;

        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int num : adjList[current]) {
                if (visited[num]) continue;
                queue.add(num);
                visited[num] = true;
            }
        }
    }
}
