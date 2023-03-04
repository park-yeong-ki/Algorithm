package bj7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //좌표 클래스 생성
    static class Point {
        int z, y, x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    //전역변수 설정
    static int M;
    static int N;
    static int H;
    static int[][][] box;
    static int[] dy = {-1, 0, 1, 0, 0, 0};
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수, 쌓아올려지는 상자의 수를 나타내는 H
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        //박스 입력
        box = new int[H][N][M];
        //토마토가 익은 좌표를 저장할 리스트
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) {
                        points.add(new Point(i, j, k));
                    }
                }
            }
        }

        //bfs
        bfs(points);

        //토마토가 다 익었는지 체크
        boolean flag = true;
        outer:
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0) {
                        flag = false;
                        break outer;
                    }
                }
            }
        }

        //출력
        if (flag) System.out.println(count-1);
        else System.out.println(-1);
    }

    //bfs구현
    static void bfs(ArrayList<Point> points) {
        //큐 생성
        Queue<Point> queue = new ArrayDeque<>();

        //방문배열 생성
        boolean[][][] visited = new boolean[H][N][M];

        //초기 익은 토마토 입력
        for (int i = 0; i < points.size(); i++) {
            queue.offer(points.get(i));
            visited[points.get(i).z][points.get(i).y][points.get(i).x] = true;
        }

        //큐가 비어있을 때까지 반복
        Point current;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();

            //최소 며칠이 걸리는지 알기 위해 레벨을 파악할 수 있도록 반복문을 설정
            int x, y ,z;
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                x = current.x;
                y = current.y;
                z = current.z;
                box[z][y][x] = 1;

                //6방향을 탐색한다.
                for (int j = 0; j < 6; j++) {
                    if (z + dz[j] >= 0 && z + dz[j] < H && y + dy[j] >= 0 && y + dy[j] < N && x + dx[j] >= 0 && x + dx[j] < M) {
                        if (!visited[z + dz[j]][y + dy[j]][x + dx[j]] && box[z + dz[j]][y + dy[j]][x + dx[j]] == 0) {
                            queue.offer(new Point(z + dz[j], y + dy[j], x + dx[j]));
                            visited[z + dz[j]][y + dy[j]][x + dx[j]] = true;
                        }
                    }
                }
            }

            //걸리는 기간을 체크
            count++;
        }
    }
}
