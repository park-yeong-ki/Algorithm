package bj24480;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static ArrayList<Integer>[] abjList;
    static int[] result;
    static int count = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //정점의 수 N, 간선의 수 M, 시작 정점 R입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
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

            //무향그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //인접리스트 내림차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(abjList[i], Comparator.reverseOrder());
        }

        //dfs 사용
        result = new int[N + 1];
        dfs(R, new boolean[N + 1]);

        //출력
        for (int i = 1; i <= N; i++) {
            bw.write(result[i] + "\n");
        }
        bw.close();
    }

    //dfs 구현
    static void dfs(int start, boolean[] visited) {
        //정점 방문 순서 입력
        result[start] = count;

        //방문체크
        visited[start] = true;

        //인접정점 탐색
        for (int num: abjList[start]) {
            if (!visited[num]) {
                count++;
                dfs(num, visited);
            }
        }
    }
}
