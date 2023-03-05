package bj11403;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int[][] abjMatrix;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        //정점의 개수 N
        N = Integer.parseInt(br.readLine());

        //인접행렬
        abjMatrix = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                abjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //bfs사용
        //경로가 있는지 저장할 배열
        result = new int[N][N];
        for (int i = 0; i < N; i++) {
            bfs(i);
        }

        //결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(result[i][j] + " ");
            }
            bw.newLine();
        }

        bw.close();
    }

    static void bfs(int start) {
        //큐생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문 배열 생성
        boolean[] visited = new boolean[N];

        //초기 입력값 큐에 삽입
        queue.offer(start);

        //큐가 비어있지 않을 때까지 반복
        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            //방문하지 않은 정점인지와 간선이 존재하는지 확인
            for (int i = 0; i < N; i++) {
                if (!visited[i] && abjMatrix[current][i] == 1) {
                    //조건에 부합하면 큐에 해당 정점을 삽입하고 방문표시
                    queue.offer(i);
                    visited[i] = true;
                    result[start][i] = 1;
                }
            }
        }
    }
}
