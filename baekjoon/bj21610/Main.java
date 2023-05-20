package bj21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] map, mArr;
    static boolean[][] prevCloud;
    static class Cloud{
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static List<Cloud> clouds;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0,-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < M; i++) {
            move(i);
            rain();
            waterCopy();
            createCloud();
        }
        System.out.println(waterSum());
    }

    static int waterSum() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }

    static void move(int idx) {
        for (int i = 0; i < clouds.size(); i++) {
            for (int j = 0; j < mArr[idx][1]; j++) {
                int tR = clouds.get(i).r + dr[mArr[idx][0]];
                int tC = clouds.get(i).c + dc[mArr[idx][0]];

                if (tR < 1) tR = N;
                else if (tR > N) tR = 1;
                if (tC < 1) tC = N;
                else if (tC > N) tC = 1;

                clouds.get(i).r = tR;
                clouds.get(i).c = tC;
            }
        }
    }

    static void rain() {
        for (Cloud cloud: clouds) {
            map[cloud.r][cloud.c]++;
        }
    }

    static void waterCopy() {
        for (Cloud cloud: clouds) {
            for (int i = 2; i <= 8; i+=2) {
                int tR = cloud.r + dr[i];
                int tC = cloud.c + dc[i];

                if (tR < 1 || tR > N || tC < 1 || tC > N) continue;
                if (map[tR][tC] != 0) map[cloud.r][cloud.c]++;
            }
        }
    }

    static void createCloud() {
        prevCloud = new boolean[N + 1][N + 1];

        for (Cloud cloud: clouds) {
            prevCloud[cloud.r][cloud.c] = true;
        }
        clouds.clear();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] >= 2 && !prevCloud[i][j]) {
                    clouds.add(new Cloud(i, j));
                    map[i][j] -= 2;
                }
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mArr = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            mArr[i][0] = Integer.parseInt(st.nextToken());
            mArr[i][1] = Integer.parseInt(st.nextToken());
        }

        clouds = new ArrayList<>();
        clouds.add(new Cloud(N, 1));
        clouds.add(new Cloud(N, 2));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 1, 2));
    }
}

