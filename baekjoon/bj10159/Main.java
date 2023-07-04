package bj10159;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] downAdjList;
    static ArrayList<Integer>[] upAdjList;
    static int[] ans;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ans = new int[N + 1];

        downAdjList = new ArrayList[N + 1];
        upAdjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            downAdjList[i] = new ArrayList<>();
            upAdjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            downAdjList[from].add(to);
            upAdjList[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            up(i);
            down(i);
            ans[i] = N - ans[i] + 1;
        }

        for (int i = 1; i <= N; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.close();
    }

    static void up(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        int current, cnt = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            cnt++;

            for (int num : upAdjList[current]) {
                if (visited[num]) continue;
                queue.add(num);
                visited[num] = true;
            }
        }

        ans[start] += cnt;
    }

    static void down(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        int current, cnt = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            cnt++;

            for (int num : downAdjList[current]) {
                if (visited[num]) continue;
                queue.add(num);
                visited[num] = true;
            }
        }

        ans[start] += cnt;
    }
}
