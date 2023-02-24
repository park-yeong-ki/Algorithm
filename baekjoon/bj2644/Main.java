package bj2644;

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
    static int n;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //전체 사람 수 n 입력 -> 정점의 개수
        n = Integer.parseInt(br.readLine());

        //촌수 계산 해야 되는 사람들
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        //인접리스트 생성
        abjList = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            abjList[i] = new ArrayList<>();
        }

        //관계의 개수 -> 간선의 개수
        int m = Integer.parseInt(br.readLine());

        int from, to;
        //인접리스트의 간선 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            //무향 그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //bfs구현
        bfs(p1, p2);


        bw.close();
    }

    //bfs구현
    private static void bfs(int start, int end) throws IOException {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<Integer>();

        //정점 방문 체크 배열 생성
        boolean[] visited = new boolean[n+1];

        //루트 노드 추가
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있지 않을때까지 반복
        int current = 0;
        int size=0;
        while(!queue.isEmpty()) {
            //큐의 사이즈를 저장
            size =queue.size();

            //큐의 사이즈 만큼 반복 -> 레벨의 노드 수만큼 반복 한다.
            for (int i = 0; i < size; i++) {
                //현재 노드를 빼준다
                current = queue.poll();

                //p2에 현재 노드와 같다면 종료
                if (current == end) {
                    bw.write(count + "\n");
                    return;
                }


                //자식 노드를 큐에 넣어준다
                for (int j = 0; j < abjList[current].size(); j++) {
                    if(!visited[abjList[current].get(j)]) {
                        queue.offer(abjList[current].get(j));
                        visited[abjList[current].get(j)] = true;
                    }
                }
            }

            //계층의 수를 센다
            count++;

        }

        bw.write(-1 + "\n");
    }
}