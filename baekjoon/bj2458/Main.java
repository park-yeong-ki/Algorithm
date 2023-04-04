package bj2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] up;
    static int[] down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
        }

        up = new int[N + 1];
        down = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (up[i] + down[i] == N) {
                result++;
            }
        }

        System.out.println(result);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[N + 1];

        queue.offer(start);
        visited[start] = true;

        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            up[start]++;

            for (int student: adjList[current]) {
                if (!visited[student]) {
                    queue.offer(student);
                    visited[student] = true;
                    down[student]++;
                }
            }
        }
    }
}
