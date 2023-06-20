package B형특강_2회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서_연결하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, max, cCnt, ans;
    static int[][] cell;
    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static List<Node> coreList;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            dfs(0, 0);
            sb.append("#" + test_case + " " + ans + "\n");
        }
        System.out.print(sb);
    }

    static void dfs(int idx, int cnt) {
        if (idx == coreList.size()) {
            if (max < cnt) {
                ans = cCnt;
                max = cnt;
            } else if (max == cnt) {
                ans = Math.min(ans, cCnt);
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (canConnect(coreList.get(idx), i)) {
                connect(coreList.get(idx), i, -1);
                dfs(idx + 1, cnt + 1);
                connect(coreList.get(idx), i, 0);
            }
        }
        dfs(idx + 1, cnt);
    }

    static boolean canConnect(Node core, int d) {
        int r = core.r;
        int c = core.c;

        while (true) {
            r += dr[d];
            c += dc[d];
            if (r < 0 || r >= N || c < 0 || c >= N) break;
            if (cell[r][c] == -1 || cell[r][c] == 1) return false;
        }
        return true;
    }

    static void connect(Node core, int d, int status) {
        int r = core.r;
        int c = core.c;

        while (true) {
            r += dr[d];
            c += dc[d];
            if (r < 0 || r >= N || c < 0 || c >= N) break;
            cell[r][c] = status;
            if (status == -1) cCnt++;
            else cCnt--;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        cell = new int[N][N];
        coreList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cell[i][j] = Integer.parseInt(st.nextToken());
                if (cell[i][j] == 1) {
                    if (i == 0 || i == N-1 || j == 0 || j == N-1) continue;
                    coreList.add(new Node(i, j));
                }
            }
        }
        max = Integer.MIN_VALUE;
        cCnt = 0;
        ans = 0;
    }
}
