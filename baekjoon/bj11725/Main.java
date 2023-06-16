package bj11725;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int[] ans;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int from, to;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        bfs(1);

        for (int i = 2; i <= N; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.close();
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        ans = new int[N + 1];

        queue.add(start);
        ans[start] = start;

        int current, size;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                for (int num: adjList[current]) {
                    if (ans[num] != 0) continue;
                    queue.add(num);
                    ans[num] = current;
                }
            }
        }
    }
}
