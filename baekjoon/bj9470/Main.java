package bj9470;

import java.io.*;
import java.util.*;

public class Main {
    static int K, M, P;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static List<Integer> start;
    static int[] strahler;
    static ArrayList<Integer>[] inList;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            bfs();
            bw.write(test_case + " " + strahler[M] + "\n");
        }
        bw.close();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[M + 1];
        inList = new ArrayList[M + 1];
        for (int i = 1; i <= M; i++) {
            adjList[i] = new ArrayList<>();
            inList[i] = new ArrayList<>();
        }

        inDegree = new int[M + 1];
        int from, to;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            inList[to].add(from);
            inDegree[to]++;
        }

        start = new ArrayList<>();
        strahler = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            if (inDegree[i] == 0) {
                start.add(i);
                strahler[i] = 1;
            }
        }
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.addAll(start);

        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int to : adjList[current]) {
                if (--inDegree[to] == 0) {
                    int max = Integer.MIN_VALUE;
                    for (int from : inList[to]) {
                        if (max < strahler[from]) {
                            max = strahler[from];
                        } else if (max == strahler[from]) {
                            max = strahler[from] + 1;
                        }
                        strahler[to] = max;
                    }
                    queue.add(to);
                }
            }
        }
    }
}
