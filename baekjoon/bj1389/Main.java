package bj1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //전역변수 설정
    static ArrayList<Integer>[] abjList;
    static int N;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //유저의 수 N, 친구 관계의 수 M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //인접리스트 생성
        abjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            abjList[i] = new ArrayList<>();
        }

        //관계 입력
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            //무향그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }


        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                //bfs 사용
                bfs(i, j);
            }

            //케빈 베이컨의 수의 최소값을 갱신하여 가장 작은 사람을 구한다.
            if (min > sum) {
                min = sum;
                result = i;
            }
            //케빈 베이컨 수 초기화
            sum = 0;
        }

        //출력
        System.out.println(result);
    }

    //bfs 구현
    static void bfs(int start, int end) {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문 배열 생성
        boolean[] visited = new boolean[N + 1];

        //시작점 큐에 삽입
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있을 때까지 반복
        int current, size;
        int count = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            //단계를 측정하기 위한 반복문 설정
            for (int i = 0; i < size; i++) {
                current = queue.poll();

                //원하는 정점까지의 단계를 구하고 종료
                if (current == end) {
                    sum += count;
                    return;
                }

                //인접한 정점이 방문되지 않았으면 큐에 넣고 방문체크
                for (int j = 0; j < abjList[current].size(); j++) {
                    if (!visited[abjList[current].get(j)]) {
                        queue.offer(abjList[current].get(j));
                        visited[abjList[current].get(j)] = true;
                    }
                }
            }
            count++;
        }
    }
}
