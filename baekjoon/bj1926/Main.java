package bj1926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int n;
    static int m;
    static int[][] paper;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //세로 크기 n, 가로 크기 m입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //도화지 입력
        paper = new int[n+2][m+2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //bfs사용
        //방문배열 생성
        boolean[][] visited = new boolean[n+2][m+2];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (paper[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, visited);
                    //그림의 개수 체크
                    count++;
                }
            }
        }

        //출력
        System.out.println(count);
        System.out.println(max);
    }

    static void bfs(int x, int y, boolean[][] visited) {
        //큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        //큐에 초기값을 넣고 방문체크
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        //큐가 비어있을 때까지 반복
        int[] current;
        int r, c;
        int cnt = 1;
        while (!queue.isEmpty()) {
            current = queue.poll();
            r = current[0];
            c = current[1];

            //상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                if (paper[r + dx[i]][c + dy[i]] == 1 && !visited[r + dx[i]][c + dy[i]]) {
                    queue.offer(new int[]{r + dx[i], c + dy[i]});
                    visited[r + dx[i]][c + dy[i]] = true;
                    cnt++;
                }
            }
        }

        //가장 넓은 그림의 넓이 갱신
        max = Math.max(max, cnt);
    }
}
