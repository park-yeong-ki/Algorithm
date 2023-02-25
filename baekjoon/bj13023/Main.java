package bj13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static ArrayList<Integer>[] abjList;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //정점 수만큼 인접 리스트 생성
        abjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            abjList[i] = new ArrayList<>();
        }

        //친구 관계 입력
        int to, from;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            //무향 그래프
            abjList[from].add(to);
            abjList[to].add(from);
        }

        //dfs 사용 -> 각 정점에서 시작해서 깊이가 4인 경우를 찾는다.
        for (int i = 0; i < N; i++) {
            if (!flag) dfs(i, new boolean[N], 0);
        }


        //출력
        if (flag) System.out.println(1);
        else System.out.println(0);
    }

    static void dfs(int start, boolean[] visited, int depth) {
        //깊이가 4인 그래프의 경우 참으로 설정하고 깊이우선탐색 종료
        if (depth == 4) {
            flag = true;
            return;
        }

        //방문체크
        visited[start] = true;

        //인접 정점 탐색
        for (int i = 0; i < abjList[start].size(); i++) {
            if (!visited[abjList[start].get(i)]) {
                dfs(abjList[start].get(i), visited, depth + 1);
            }
        }

        //여러 경로의 깊이를 측정하는 것이기 때문에 방문해제
        visited[start] = false;
    }
}
