package bj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[][] storage;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int count;

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //가로 칸 M(열), 세로 칸 N(행)
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        //창고 입력
        storage = new int[N][M];
        //좌표를 저장할 리스트
        ArrayList<Point> pList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                storage[i][j] = Integer.parseInt(st.nextToken());
                if (storage[i][j] == 1) {
                    pList.add(new Point(i, j));
                }
            }
        }

        //bfs구현
        bfs(pList);

        //토마토가 모두 익었는지 검사
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(storage[i][j] == 0) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
        }

        //출력
        if(flag) System.out.println(count - 1);
        else System.out.println(-1);

    }

    //bfs
    static void bfs(ArrayList<Point> pList) {
        //큐 생성
        Queue<Point> queue = new ArrayDeque<>();

        //방문배열 생성
        boolean[][] visited = new boolean[N][M];

        //큐에 익은 토마토 위치 삽입
        for (int i = 0; i < pList.size(); i++) {
            queue.offer(pList.get(i));
            visited[pList.get(i).x][pList.get(i).y] = true;
        }


        //큐가 비어있을 때까지 반복
        Point current;
        int size;
        while(!queue.isEmpty()) {
            size = queue.size();

            //최소일수를 파악하기 위해 레벨별로 반복
            for (int i = 0; i < size; i++) {
                current = queue.poll();

                //주변에 탐색된 토마토가 익는다.
                storage[current.x][current.y] = 1;

                //사방탐색
                for (int j = 0; j < 4; j++) {
                    if (current.x + dx[j] >= 0 && current.x + dx[j] <N && current.y + dy[j] >= 0 && current.y + dy[j] < M) {
                        //방문하지 않은 위치의 익지않은 토마토는 큐에 넣고 방문 표시한다.
                        if (!visited[current.x + dx[j]][current.y + dy[j]]
                                && storage[current.x + dx[j]][current.y + dy[j]] == 0) {
                            queue.offer(new Point(current.x + dx[j], current.y + dy[j]));
                            visited[current.x + dx[j]][current.y + dy[j]] = true;
                        }
                    }
                }
            }

            count++;
        }
    }
}