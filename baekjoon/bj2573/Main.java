package bj2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static class Point{
        int r, c;

        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
    static int N;
    static int M;
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N과 M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //배열 입력
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        boolean flag = false;
        while(true) {
            //방문배열 생성
            visited = new boolean[N][M];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && arr[i][j] != 0) {
                        bfs(new Point(i, j));
                        cnt++;
                    }
                }
            }

            //빙산이 쪼개진 경우 반복문 종료
            if (cnt > 1) {
                flag = true;
                break;
            }

            //빙산제거 -> 제거 개수를 확인하여 쪼개지기전에 모두 다 녹은 경우를 체크
            if (removeIce() == 0) {
                break;
            }

            //시간체크
            time++;
        }

        //출력
        if (flag) System.out.println(time);
        else System.out.println(0);
    }

    //bfs
    static void bfs(Point start) {
        //큐생성
        Queue<Point> queue = new ArrayDeque<>();

        //초기 값 큐에 저장후 방문 체크
        queue.offer(start);
        visited[start.r][start.c] = true;

        //큐가 비어있지 않을 떄까지 반복
        int size;
        Point current;
        while(!queue.isEmpty()) {
            size = queue.size();

            //레벨 측정하기 위해서 반복문 설정
            for (int i = 0; i < size; i++) {
                current = queue.poll();

                //4방 탐색하여 바다에 접촉하는 만큼 녹도록 설정
                int r,c;
                for (int j = 0; j < 4; j++) {
                    r = current.r + dr[j];
                    c = current.c + dc[j];

                    //주변이 바다인 경우 횟수를 체크
                    int count = 0;
                    if (arr[r][c] == 0) {
                        count++;
                    }
                    arr[current.r][current.c] += count * 100;

                    //방문하지 않은 상하좌우의 빙산을 탐색하여 큐에 저장
                    if (!visited[r][c] && arr[r][c] != 0) {
                        queue.offer(new Point(r, c));
                        visited[r][c] = true;
                    }
                }
            }

        }
    }

    //빙산 삭제
    static int removeIce() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] > 100) {
                    int num = Integer.parseInt(String.valueOf(arr[i][j]).substring(1, 3))
                            - Character.getNumericValue(String.valueOf(arr[i][j]).charAt(0));

                    //지운 빙산의 수를 체크
                    count++;
                    if (num <= 0 ) {
                        arr[i][j] = 0;
                    }else {
                        arr[i][j] = num;
                    }
                }
            }
        }
        return count;
    }
}