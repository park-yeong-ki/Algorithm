package bj2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //미로 입력
        map = new int[N+2][M+2];
        for (int i = 1; i <= N; i++) {
            char[] cArr = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Character.getNumericValue(cArr[j-1]);
            }
        }

        //bfs 사용
        bfs(1, 1);

    }

    static void bfs(int x, int y) {
        //큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        //방문 배열 생성
        boolean[][] visited = new boolean[N+2][M+2];

        //큐에 삽입
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        //큐가 비어있지 않을 때까지 반복
        int[] current;
        int r, c, size, count = 1;
        while (!queue.isEmpty()) {
            size = queue.size();

            //그래프의 각 레벨별 정점의 개수만큼 반복한다.
            for (int i = 0; i < size; i++) {
                //큐에 저장된 첫 번째 좌표를 꺼낸다
                current = queue.poll();
                r = current[0];
                c = current[1];

                //도착 위치에 도달하면 칸 수 출력후 종료
                if (r == N && c == M) {
                    System.out.println(count);
                    return;
                }

                //현재 정점에서의 인접 정점만큼 반복 -> 4방향(상하, 좌우)
                for (int j = 0; j < 4; j++) {
                    //이동할 수 있는 칸이고 방문한적이 없다면 큐에 저장 후 방문 체크
                    if (map[r + dx[j]][c + dy[j]] == 1 && !visited[r + dx[j]][c + dy[j]]) {
                        queue.offer(new int[]{r + dx[j], c + dy[j]});
                        visited[r + dx[j]][c + dy[j]] = true;
                    }
                }
            }

            //레벨을 체크하는 변수
            count++;
        }
    }
}
