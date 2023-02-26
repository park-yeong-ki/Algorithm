package bj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    //전역변수 설정
    static char[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N입력
        N = Integer.parseInt(br.readLine());

        //NxN 배열 생성
        arr = new char[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                arr[i][j] = str.charAt(j-1);
            }
        }

        //bfs사용 -> 정상인 경우
        //영역의 개수를 체크할 카운트 변수 선언 및 초기화
        int count = 0;
        //방문 배열 생성
        visited = new boolean[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        //출력
        System.out.print(count + " ");

        //dfs사용 -> 적록색약일 경우
        count = 0;
        //방문배열 생성
        visited = new boolean[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        //출력
        System.out.println(count);
    }

    //bfs
    static void bfs(int x, int y) {
        //큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        //처음 입력된 값 큐에 삽입
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        //큐가 비어있지 않을 때까지 반복
        int[] current;
        int r, c;
        while (!queue.isEmpty()) {
            current = queue.poll();
            r = current[0];
            c = current[1];

            //상하좌우에 같은 색상인지와 방문여부에 따라 탐색
            for (int i = 0; i < 4; i++) {
                if (arr[r][c] == arr[r + dx[i]][c + dy[i]] && !visited[r + dx[i]][c + dy[i]]) {
                    queue.offer(new int[]{r + dx[i], c + dy[i]});
                    visited[r + dx[i]][c + dy[i]] = true;
                }
            }
        }
    }

    //dfs
    static void dfs(int x, int y) {
        //방문 표시
        visited[x][y] = true;

        //적록색약인 사람이 보았을 때 상하좌우에 같은 색상인지와 방문여부에 따라 탐색
        if (arr[x][y] == 'B') {
            for (int i = 0; i < 4; i++) {
                if (arr[x][y] == arr[x + dx[i]][y + dy[i]] && !visited[x + dx[i]][y + dy[i]]) {
                    dfs(x + dx[i], y + dy[i]);
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (('R' == arr[x + dx[i]][y + dy[i]] || 'G' == arr[x + dx[i]][y + dy[i]])
                        && !visited[x + dx[i]][y + dy[i]]) {
                    dfs(x + dx[i], y + dy[i]);
                }
            }
        }
    }
}
