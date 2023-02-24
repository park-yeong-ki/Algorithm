package bj1012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스의 개수 입력
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            //가로길이 M, 세로길이 N, 배추 위치의 개수 K
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            //배추밭 생성
            arr = new int[M+2][N+2];
            int r, c;
            //배추 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                r = Integer.parseInt(st.nextToken()) + 1;
                c = Integer.parseInt(st.nextToken()) + 1;

                arr[r][c] = 1;
            }

            //bfs사용
            visited = new boolean[M+2][N+2];
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= N; j++) {
                    if(arr[i][j] == 1 && !visited[i][j]) bfs(i, j);
                }
            }

            //출력
            bw.write(count + "\n");

            //count 초기화
            count = 0;
        }

        bw.close();
    }

    static void bfs(int x, int y) {
        //큐생성
        Queue<int[]> queue = new ArrayDeque<>();

        //시작좌표 큐에 추가
        queue.offer(new int[] {x, y});
        visited[x][y] = true;

        //큐가 비어있지 않으면 반복
        int[] current = new int[2];
        while(!queue.isEmpty()) {
            //큐에 들어있는 값 제거
            current = queue.poll();
            int r = current[0];
            int c = current[1];

            //인접합 상하, 좌우를 탐색하며 방문하지 않은 배추가 있는 땅을 큐에 추가
            for (int i = 0; i < 4; i++) {
                if (arr[r+dx[i]][c+dy[i]] == 1 && !visited[r+dx[i]][c+dy[i]]) {
                    queue.offer(new int[] {r+dx[i], c+dy[i]});
                    visited[r+dx[i]][c+dy[i]] = true;
                }
            }
        }

        count++;
    }
}