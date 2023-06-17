package bj1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int N, leafNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int from, start = -1;
        for (int i = 0; i < N; i++) {
            from = Integer.parseInt(st.nextToken());
            if (from == -1) {
                start = i;
                continue;
            }

            adjList[from].add(i);
            adjList[i].add(from);
        }

        int remove = Integer.parseInt(br.readLine());
        leafNode = 0;

        if (start != -1 && remove != start) bfs(start, remove);

        System.out.println(leafNode);
    }

    static void bfs(int start, int remove) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.add(start);
        visited[start] = true;
        visited[remove] = true;

        int current, size;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                int cnt = 0;

                for (int num : adjList[current]) {
                    if (visited[num]) continue;
                    queue.add(num);
                    visited[num] = true;
                    cnt++;
                }

                if (cnt == 0) leafNode++;
            }
        }
    }
}
