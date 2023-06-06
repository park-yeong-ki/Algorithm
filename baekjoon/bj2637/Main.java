package bj2637;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<Node>[] adjList;
    static int[] inDegree, arr;

    static class Node{
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int N, M;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        output();
    }

    static void output() throws IOException {
        for (int i = 1; i <= N; i++) {
            if (adjList[i].isEmpty()) {
                bw.write(i + " " + arr[i] + "\n");
            }
        }
        bw.close();
    }

    static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(N, 1));

        Node current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (Node part : adjList[current.num]) {
                arr[part.num] += current.cnt * part.cnt;
                if (--inDegree[part.num] == 0) {
                    queue.add(new Node(part.num, arr[part.num]));
                }
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        inDegree = new int[N + 1];
        arr = new int[N + 1];

        int X, Y, K;
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            adjList[X].add(new Node(Y, K));
            inDegree[Y]++;
        }
    }
}