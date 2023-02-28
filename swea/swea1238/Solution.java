package swea1238;

import java.io.*;
import java.util.*;

public class Solution {
    //인접리스트
    static ArrayList<Integer>[] abjList = new ArrayList[101];
    //큐의 마지막 레벨에 해당하는 값을 저장할 리스트
    static ArrayList<Integer> result = new ArrayList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트케이스 10개
        for (int test_case = 1; test_case <= 10; test_case++) {
            //입력받는 데이터의 길이, 시작점
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            //인접리스트 생성
            for (int i = 1; i < 101; i++) {
                abjList[i] = new ArrayList<>();
            }

            //간선 입력
            st = new StringTokenizer(br.readLine());
            int from, to;
            for (int i = 0; i < l/2; i++) {
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());

                //유향그래프
                abjList[from].add(to);
            }

            //bfs사용
            bfs(s);

            //리스트를 내림차순 정렬
            Collections.sort(result, Comparator.reverseOrder());

            //출력
            bw.write("#" + test_case + " " + result.get(0) + "\n");
        }
        bw.close();
    }

    //bfs 구현
    static void bfs(int start) {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문 배열 생성
        boolean[] visited = new boolean[101];

        //시작점 큐에 삽입
        queue.offer(start);
        visited[start] = true;

        int current, size;
        //큐가 비어있을 때까지 반복
        while (!queue.isEmpty()) {
            size = queue.size();
            result.clear();

            //큐의 레벨을 구할 수 있도록 큐의 크기만큼만 반복한다
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                result.add(current);

                //방문하지 않은 인접정점을 큐에 넣고 방문표시를 한다
                for (int j = 0; j < abjList[current].size(); j++) {
                    if (!visited[abjList[current].get(j)]) {
                        queue.offer(abjList[current].get(j));
                        visited[abjList[current].get(j)] = true;
                    }
                }
            }

        }
    }
}
