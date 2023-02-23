package bj2606;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static ArrayList<Integer>[] abjList;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int count;
    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //정점 V 입력
        V = Integer.parseInt(br.readLine());

        //간선 E 입력
        int E = Integer.parseInt(br.readLine());

        //정점 만큼의 크기 생성
        abjList = new ArrayList[V+1];
        for (int i = 1; i < V+1; i++) {
            abjList[i] = new ArrayList<>();
        }

        //인접리스트에 간선 기록하기
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            //무향그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //bfs사용
        bfs(1);

        //출력
        bw.write(count-1 + "\n");
        bw.close();
    }

    //bfs 구현
    private static void bfs(int start) throws IOException {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[V+1];

        //루트 노드 입력
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있을때까지 반복
        int current = 0;
        while(!queue.isEmpty()) {
            //현재 큐에 가장 처음 들어있는 값 제거
            current = queue.poll();
            //감염된 컴퓨터의 숫자를 센다
            count++;

            //현재 노드의 자식 노드를 큐에 삽입
            for (int i = 0; i <abjList[current].size(); i++) {
                if(!visited[abjList[current].get(i)]) {
                    queue.offer(abjList[current].get(i));
                    visited[abjList[current].get(i)] = true;
                }
            }

        }
    }
}