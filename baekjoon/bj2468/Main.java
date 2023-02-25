package bj2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] region;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N입력
        int N = Integer.parseInt(br.readLine());

        //영역 입력
        int max = Integer.MIN_VALUE;
        region = new int[N+2][N+2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, region[i][j]);
            }
        }

        //bfs사용
        int result = 0;
        //0부터 최대 높이 전까지 반복한다
        for (int h = 0; h < max; h++) {
            //방문 배열을 생성한다
            visited = new boolean[N+2][N+2];
            //카운트 변수를 생성한다
            int count = 0;
            //각 지역의 위치 모든 좌표에 대해 탐색을 한다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    //해당 좌표의 높이가 h 보다 크고 방문한 적이 없다면 안전한 영역이라 여기고 횟수를 센다.
                    if (region[i][j] > h && !visited[i][j]) {
                        //bfs -> 안전한 영역을 찾고 방문배열을 체크하기 위해 사용
//                        bfs(i, j, h);
                        //dfs -> 안전한 영역을 찾고 방문배열을 체크하기 위해 사용
                        dfs(i, j ,h);
                        count++;
                    }
                }
            }
            //높이에 따른 안전한 영역의 최대 개수를 갱신
            result = Math.max(result, count);
        }

        //출력
        System.out.println(result);
    }

    //dfs구현
    static void dfs(int x, int y, int h) {
        //방문체크
        visited[x][y] = true;

        //상하좌우의 방향으로 인접한 정점들을 탐색하며 방문하지 않은 안전한 영역을 탐색하도록 설정한다.
        for (int i = 0; i < 4; i++) {
            if (region[x + dx[i]][y + dy[i]] > h && !visited[x + dx[i]][y + dy[i]]) {
                dfs(x + dx[i], y + dy[i], h);
            }
        }
    }

    //bfs구현
//    static void bfs(int x, int y, int h) {
//        //큐 생성
//        Queue<int[]> queue = new ArrayDeque<>();
//
//        //큐에 입력받은 좌표를 넣고 방문체크한다.
//        queue.offer(new int[]{x, y});
//        visited[x][y] = true;
//
//        int[] current;
//        int r, c;
//        //큐가 비어있지 않을 때까지 반복
//        while (!queue.isEmpty()) {
//            //가장 처음 삽입한 큐를 제거
//            current = queue.poll();
//            r = current[0];
//            c = current[1];
//
//            //상하좌우의 방향으로 인접한 정점들을 탐색하며 방문하지 않은 안전한 영역을 탐색하도록 설정한다.
//            for (int i = 0; i < 4; i++) {
//                if (region[r + dx[i]][c + dy[i]] > h && !visited[r + dx[i]][c + dy[i]]) {
//                    //조건을 통과한 정점들을 큐에 넣고 방문체크
//                    queue.offer(new int[]{r + dx[i], c + dy[i]});
//                    visited[r + dx[i]][c + dy[i]] = true;
//                }
//            }
//        }
//    }
}
