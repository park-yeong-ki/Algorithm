package bj11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int M;
    static ArrayList<Integer>[] abjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //정점의 개수 N과 간선의 개수 M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //인접리스트 생성
        abjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            abjList[i] = new ArrayList<>();
        }

        //간선입력
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            //무향그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //bfs
        int count = 0;
        //방문배열 생성
        visited = new boolean[N + 1];
        //연결요소의 개수 체크
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }

        //출력
        System.out.println(count);
    }

    //bfs 구현
    static void bfs(int start) {
        //큐생성
        Queue<Integer> queue = new ArrayDeque<>();

        //초기 정점 삽입
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있을 때까지 반복
        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            //방문하지 않은 인접 정점을 큐에 넣고 방문체크
            for (int i : abjList[current]) {
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
