package bj17471;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int[] population;
    static ArrayList<Integer>[] abjList;
    static boolean isSelected[];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //구역의 개수 N입력
        N = Integer.parseInt(br.readLine());

        //인구 배열
        population = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        //인접리스트
        abjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            abjList[i] = new ArrayList<>();
        }

        //인접리스트 입력
        int size = 0, to = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                to = Integer.parseInt(st.nextToken());
                //무향그래프
                abjList[i].add(to);
                abjList[to].add(i);
            }
        }

        //부분집합으로 구역을 나눈다.
        isSelected = new boolean[N + 1];
        subset(1, 0);

        //출력
        bw.write((min != Integer.MAX_VALUE ? min : -1) + "\n");
        bw.close();
    }

    static void subset(int cnt, int num) {
        //기저조건
        if (cnt == N + 1) {
            //선거구 하나에 몰려있는 경우는 종료
            if (num == N || num == 0) {
                return;
            }

            //각 선거구별 인구수 구하기
            int p1 = 0;
            int p2 = 0;
            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) {
                    if (!bfs(i, num)) return;
                    p1 += population[i];
                } else {
                    if (!bfs(i, N-num)) return;
                    p2 += population[i];
                }
            }

            //최소값 구하기
            min = Math.min(min, Math.abs(p1 - p2));

            return;
        }

        //유도부분
        isSelected[cnt] = true;
        subset(cnt + 1, num + 1);
        isSelected[cnt] = false;
        subset(cnt + 1, num);
    }

    static boolean bfs(int start, int num) {
        //큐생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문배열 생성
        boolean[] visited = new boolean[N + 1];

        //큐에 값 삽입
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있을 때까지 반복
        int current, count=0;
        while (!queue.isEmpty()) {
            current = queue.poll();

            //연결된 구역인지 확인 후 큐에 추가
            for (int i = 0; i < abjList[current].size(); i++) {
                if (!visited[abjList[current].get(i)] && isSelected[start] == isSelected[abjList[current].get(i)]) {
                    queue.offer(abjList[current].get(i));
                    visited[abjList[current].get(i)] = true;
                }
            }

            //총 큐에 저장되었던 횟수를 체크
            count++;
        }

        //선거구의 구역 수와 큐에 저장되었던 횟수가 같은지 확인
        if (count == num) return true;
        else return false;
    }
}
