package bj24445;

import java.io.*;
import java.util.*;

public class Main {
    //전역변수 설정
    static ArrayList<Integer>[] abjList;
    static int N;
    static int M;
    static int[] result;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //정점의 수 N, 간선의 수 M, 시작 정점 R
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        //인접리스트 생성
        abjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            abjList[i] = new ArrayList<>();
        }

        //간선 입력
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            //무향 그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //인접리스트 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(abjList[i], Comparator.reverseOrder());
        }

        //bfs 사용
        result = new int[N + 1];
        bfs(R);

        //출력
        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }
        bw.close();
    }

    static void bfs(int start) {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문배열 생성
        boolean[] visited = new boolean[N + 1];

        //시작 정점 큐 삽입
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있지 않을 때까지 반복
        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            result[current] = count;
            count++;

            //인접 정점 탐색
            for (int v: abjList[current]) {
                if (!visited[v]) {
                    queue.offer(v);
                    visited[v] = true;
                }
            }
        }
    }
}
