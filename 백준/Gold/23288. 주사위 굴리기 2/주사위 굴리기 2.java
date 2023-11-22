import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, score, row, col, dir;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1}; //동, 남, 서, 북
    static int[] dc = {1, 0, -1, 0};
    static int[] arr, copyArr;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        while (0 < K) {
            move();
            getScore();
            changeDir();
            K--;
        }

        System.out.println(score);
    }

    static void move() {
        int tR = row + dr[dir];
        int tC = col + dc[dir];

        if (tR < 0 || tR >= N || tC < 0 || tC >= M){ //경계 초과한 경우
            dir = (dir + 2) % 4;//반대방향으로 방향 변경
            tR = row + dr[dir]; //반대방향으로 이동
            tC = col + dc[dir];
        }

        row = tR; //위치 이동
        col = tC;

        swap(dir); //전개도 변경
    }

    static void swap(int d) {
        switch (d) {
            case 0: //동
                copyArr[0] = arr[0];
                copyArr[1] = arr[5];
                copyArr[2] = arr[1];
                copyArr[3] = arr[2];
                copyArr[4] = arr[4];
                copyArr[5] = arr[3];
                break;
            case 1: //남
                copyArr[0] = arr[5];
                copyArr[1] = arr[1];
                copyArr[2] = arr[0];
                copyArr[3] = arr[3];
                copyArr[4] = arr[2];
                copyArr[5] = arr[4];
                break;
            case 2: //서
                copyArr[0] = arr[0];
                copyArr[1] = arr[2];
                copyArr[2] = arr[3];
                copyArr[3] = arr[5];
                copyArr[4] = arr[4];
                copyArr[5] = arr[1];
                break;
            case 3: //북
                copyArr[0] = arr[2];
                copyArr[1] = arr[1];
                copyArr[2] = arr[4];
                copyArr[3] = arr[3];
                copyArr[4] = arr[5];
                copyArr[5] = arr[0];
                break;
        }
        arr = copyArr.clone();
    }

    static void getScore() {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(new Point(row, col));
        visited[row][col] = true;

        int cnt = 0;
        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= N || tC < 0 || tC >= M) continue; //경계초과
                if (map[tR][tC] != map[row][col]) continue; //지도에 적힌 점수와 다른 경우
                if (visited[tR][tC]) continue; //방문경우

                queue.add(new Point(tR, tC));
                visited[tR][tC] = true;
            }
        }

        score += map[row][col] * cnt; //점수 갱신
    }

    static void changeDir() {
        if (arr[5] > map[row][col]){ //시계방향 90도
            dir++;
            if (dir == 4) dir = 0;
        } else if (arr[5] < map[row][col]) { //반시계방향 90도
            dir--;
            if (dir == -1) dir = 3;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        K = Integer.parseInt(st.nextToken()); //횟수

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        score = 0;

        arr = new int[]{2, 4, 1, 3, 5, 6}; //초기 주사위
        copyArr = new int[6]; //주사위 이동후 복사할 배열

        row = 0; //초기 행 위치
        col = 0; //초기 열 위치
        dir = 0; //초기 방향
    }
}