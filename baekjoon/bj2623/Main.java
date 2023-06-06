package bj2623;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static int N, M;
    static List<Integer> result;
    public static void main(String[] args) throws IOException {
        input();
        bfs();
        output();
    }

    static void output() throws IOException {
        if (result.size() == N) {
            for (int num : result) {
                bw.write(num + "\n");
            }
        } else {
            bw.write(0 + "\n");
        }
        bw.close();
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        result = new ArrayList<>();

        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            result.add(current);

            for (int num : adjList[current]) {
                if (--inDegree[num] == 0) {
                    queue.add(num);
                }
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int mNum = Integer.parseInt(st.nextToken());
            int[] arr = new int[mNum];
            for (int j = 0; j < mNum; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int from, to;
            for (int j = 0; j < arr.length - 1; j++) {
                from = arr[j];
                to = arr[j + 1];
                adjList[from].add(to);
                inDegree[to]++;
            }
        }
    }
}
