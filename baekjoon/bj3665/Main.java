package bj3665;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int m;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static int[] order;
    static List<Integer> start;
    static StringBuilder result;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            bfs();
            bw.write(result + "\n");
        }
        bw.close();
    }

    static void bfs() {
        result = new StringBuilder();

        Queue<Integer> queue = new ArrayDeque<>();

        queue.addAll(start);

        int current, size, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            if (size > 1) {
                result.setLength(0);
                result.append("?");
                return;
            }

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                result.append(current + " ");

                for (int to : adjList[current]) {
                    if (--inDegree[to] == 0) {
                        queue.add(to);
                    }
                }
            }
            cnt++;
        }

        if (cnt != n) {
            result.setLength(0);
            result.append("IMPOSSIBLE");
            return;
        }
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        //순서입력
        order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        inDegree = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                adjList[order[i]].add(order[j]);
                inDegree[order[j]]++;
            }
        }

        m = Integer.parseInt(br.readLine());
        int num1, num2;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());

            if (adjList[num1].contains(num2)) {
                adjList[num1].remove(new Integer(num2));
                inDegree[num2]--;
                adjList[num2].add(num1);
                inDegree[num1]++;
            } else {
                adjList[num2].remove(new Integer(num1));
                inDegree[num1]--;
                adjList[num1].add(num2);
                inDegree[num2]++;
            }
        }

        start = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                start.add(i);
            }
        }
    }
}
