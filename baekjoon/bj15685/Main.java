package bj15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, cnt;
    static class Node {
        int x, y, d, g;
        public Node(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }
    static Node[] nodes;
    static boolean[][] map;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        input();
        make();
        count();
        System.out.println(cnt);
    }

    static void count() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) cnt++;
            }
        }
    }
    static void make(){
        for (int i = 0; i < N; i++) {
            int r = nodes[i].y;
            int c = nodes[i].x;
            int d = nodes[i].d;
            int g = nodes[i].g;

            ArrayList<Integer> dir = new ArrayList<>();
            //0세대 방향 저장
            dir.add(d);
            //각 세대의 방향은 이전 세대까지 저장된 방향을 역순으로 90도 시계 방향 회전하여 저장
            for (int j = 1; j <= g; j++) {
                for (int k = dir.size() - 1; k >= 0; k--) {
                    dir.add((dir.get(k) + 1) % 4);
                }
            }
            //저장된 방향대로 이동하면서 좌표에 체크한다.
            map[r][c] = true;
            for (int j = 0; j < dir.size(); j++) {
                r += dr[dir.get(j)];
                c += dc[dir.get(j)];
                map[r][c] = true;
            }
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y, d, g);
        }
        map = new boolean[101][101];
    }
}
