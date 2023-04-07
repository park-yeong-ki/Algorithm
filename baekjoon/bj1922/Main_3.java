package bj1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_3 {
    static class Computer implements Comparable<Computer>{
        int to, weight;

        public Computer(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N;
    static int M;
    static ArrayList<Computer>[] adjList;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        //인접리스트 생성
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            //무향 그래프
            adjList[from].add(new Computer(to, weight));
            adjList[to].add(new Computer(from, weight));
        }

        prim(new Computer(1, 0));

        System.out.println(result);
    }

    static void prim(Computer start) {
        PriorityQueue<Computer> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N+1];

        pq.add(start);

        Computer current;
        int total = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            current = pq.poll();

            if(visited[current.to]) continue;

            total += current.weight;
            visited[current.to] = true;

            for (Computer com : adjList[current.to]) {
                if(!visited[com.to]) {
                    pq.add(com);
                }
            }
            cnt++;

            if (cnt == N) {
                result = total;
                return;
            }
        }
    }
}