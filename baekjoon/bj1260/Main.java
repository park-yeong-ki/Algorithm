package bj1260;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] abjList;
    static int N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //정점의 개수 N, 간선의 개수 M, 탐색을 시작할 정점의 번호 V입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        //인접 리스트 생성
        abjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            abjList[i] = new ArrayList<>();
        }

        //간선 입력
        int to, from;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            to = Integer.parseInt(st.nextToken());
            from = Integer.parseInt(st.nextToken());

            //무향 그래프
            abjList[to].add(from);
            abjList[from].add(to);
        }

        //인접 리스트의 값을 오름차순 정렬한다.
        for (int i = 1; i < N+1; i++) {
            Collections.sort(abjList[i]);
        }

        //dfs 사용
        dfs(V, new boolean[N+1]);
        bw.newLine();
        //bfs 사용
        bfs(V);

        bw.close();
    }

    //dfs 함수 생성
    static void dfs(int start, boolean[] dVisited) throws IOException {
        //방문 표시
        dVisited[start] = true;
        bw.write(start + " ");

        //입력한 정점과 연결된 정점들 중에 방문하지 않은 경우 해당 정점을 재귀를 이용하여 탐색
        for (int i = 0; i < abjList[start].size(); i++) {
            if(!dVisited[abjList[start].get(i)])
                dfs(abjList[start].get(i), dVisited);
        }
    }

    //bfs 함수 생성
    static void bfs(int start) throws IOException {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<Integer>();

        //방문 배열 생성
        boolean[] bVisited = new boolean[N+1];

        //첫번째 노드 입력
        queue.offer(start);
        bVisited[start] = true;

        //큐가 비어있을 때 까지 반복
        int current = 0;
        while(!queue.isEmpty()) {
            //가장 첫번째 있는 값을 제거한다.
            current = queue.poll();
            bw.write(current + " ");

            //꺼낸 노드에 연결된 정점을 탐색한다.
            for (int i = 0; i < abjList[current].size(); i++) {
                //방문하지 않은 정점이라면 큐에 추가한다.
                if (!bVisited[abjList[current].get(i)]) {
                    queue.offer(abjList[current].get(i));
                    bVisited[abjList[current].get(i)] = true;
                }
            }
        }
    }
}