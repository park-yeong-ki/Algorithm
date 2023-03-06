package bj24479;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static ArrayList<Integer>[] abjList;
    static int N;
    static int M;
    static int count = 1;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //정점의 수 N, 간선의 수 M, 시작 정점 R
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        //인접리스트 생성
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

            //무향그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //간선리스트 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(abjList[i]);
        }

        //dfs 사용
        result = new int[N + 1];
        dfs(R, new boolean[N + 1]);

        //결과 출력
        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }

        bw.close();
    }

    //dfs 구현
    static void dfs(int start, boolean[] visited){
        //정점에 방문한 경우 순서 입력
        result[start] = count;

        //방문 표시
        visited[start] = true;

        //인접 정점 탐색
        for (int V: abjList[start]) {
            if (!visited[V]) {
                count++;
                dfs(V, visited);
            }
        }
    }
}
