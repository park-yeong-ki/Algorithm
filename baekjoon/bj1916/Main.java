package bj1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class City implements Comparable<City>{
        int num, cost;

        public City(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static ArrayList<City>[] cityList;
    static int N;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        //인접리스트 생성 -> 1번 도시부터 시작
        cityList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            cityList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            //유향 그래프
            cityList[from].add(new City(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijstra(start, end);

        System.out.println(result);
    }

    static void dijstra(int start, int end) {
        PriorityQueue<City> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N+1];

        pq.add(new City(start, 0));

        City current;
        while(!pq.isEmpty()) {
            current = pq.poll();

            visited[current.num] = true;

            if (current.num == end) {
                result = current.cost;
                return;
            }

            for (City c : cityList[current.num]) {
                if (!visited[c.num]) {
                    pq.offer(new City(c.num, current.cost + c.cost));
                }
            }
        }
    }
}