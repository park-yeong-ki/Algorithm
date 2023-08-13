package bj15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int N, cnt;
    static int[][] dragonCurve;
    static boolean[][] map = new boolean[101][101];
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        input();
        makeLine();
        count();
        System.out.println(cnt);
    }

    static void count() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]){
                    cnt++;
                }
            }
        }
    }

    static void makeLine() {
        for (int i = 0; i < dragonCurve.length; i++) {
            int x = dragonCurve[i][0];
            int y = dragonCurve[i][1];
            int d = dragonCurve[i][2];
            int g = dragonCurve[i][3];

            //세대별 방향을 저장후 역순으로 +1씩 방향을 더하여 저장
            map[y][x] = true;
            y += dr[d];
            x += dc[d];
            map[y][x] = true;
            ArrayList<Integer> dir = new ArrayList<>();
            dir.add(d);

            for (int j = 0; j < g; j++) {
                for (int k = dir.size()-1; k >= 0; k--) {
                    int tD = (dir.get(k) + 1) % 4;
                    y += dr[tD];
                    x += dc[tD];
                    map[y][x] = true;
                    dir.add(tD);
                }
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dragonCurve = new int[N][4];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dragonCurve[i][0] = Integer.parseInt(st.nextToken());
            dragonCurve[i][1] = Integer.parseInt(st.nextToken());
            dragonCurve[i][2] = Integer.parseInt(st.nextToken());
            dragonCurve[i][3] = Integer.parseInt(st.nextToken());
        }

        cnt = 0;
    }
}
