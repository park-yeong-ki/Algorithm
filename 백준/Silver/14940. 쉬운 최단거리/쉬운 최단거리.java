import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] ans;
    static class Point{
        int r, c, w;

        Point(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        Point spot = new Point(-1, -1, -1); //목표지점 생성

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) spot = new Point(i, j, 0); //목표지점 찾기
            }
        }

        ans = new int[n][m];
        bfs(spot); //bfs로 목표지점에서 모든 지점을 방문
        notVisited(); //갈 수 있다가 못가는 경우는 -1로 변경

        for (int i = 0; i < n; i++) { //정답 출력
            for (int j = 0; j < m; j++) {
                bw.write(ans[i][j] + " ");
            }
            bw.newLine();
        }
        bw.close();
    }

    static void notVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] != 0) ans[i][j] = -1; //갈 수 있는 땅이 도달할 수 없어진 경우
            }
        }
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        visited = new boolean[n][m];

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            ans[current.r][current.c] = current.w;

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= n  || tC < 0 || tC >= m) continue; //경계초과한경우
                if (visited[tR][tC]) continue; //이미 방문한 경우
                if (map[tR][tC] == 0) continue; //갈 수 없는 땅인 경우

                queue.add(new Point(tR, tC, current.w + 1));
                visited[tR][tC] = true;
            }
        }
    }
}