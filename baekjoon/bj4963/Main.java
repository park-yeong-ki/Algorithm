package bj4963;

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
    static int[][] map;
    static int w;
    static int h;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            //너비w, 높이h입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            //지도 입력
            map = new int[h+2][w+2];
            for (int i = 1; i <= h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //bfs사용
            //방문 배열생성
            visited = new boolean[h+2][w+2];
            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) bfs(i, j);
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

            //인접합 상하, 좌우, 대각선을 탐색하며 방문하지 않은 땅인 경우 큐에 추가
            for (int i = 0; i < 8; i++) {
                if (map[r+dx[i]][c+dy[i]] == 1 && !visited[r+dx[i]][c+dy[i]]) {
                    queue.offer(new int[] {r+dx[i], c+dy[i]});
                    visited[r+dx[i]][c+dy[i]] = true;
                }
            }
        }

        count++;
    }
}