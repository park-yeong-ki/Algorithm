import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        input();
        move();
        System.out.println(ans);
    }

    static void borderCheck(int tR, int tC, int num) {
        if (tR < 0 || tR >= N || tC <0 || tC >= N) ans += num;
        else map[tR][tC] += num;
    }

    static void spread(int r, int c, int d) {
        int tR = 0;
        int tC = 0;
        int r1 = (int) (map[r][c] * 0.01);
        int r2 = (int) (map[r][c] * 0.02);
        int r5 = (int) (map[r][c] * 0.05);
        int r7 = (int) (map[r][c] * 0.07);
        int r10 = (int) (map[r][c] * 0.1);
        int a = map[r][c] - (r5 + 2 * (r1 + r2 + r7 + r10));

        //1
        tR = r + 2 * dr[(d + 3) % 4];
        tC = c + 2 * dc[(d + 3) % 4];
        borderCheck(tR, tC, r2);

        //2
        tR = r + dr[d] + dr[(d + 3) % 4];
        tC = c + dc[d] + dc[(d + 3) % 4];
        borderCheck(tR, tC, r10);

        //3
        tR = r + dr[(d + 3) % 4];
        tC = c + dc[(d + 3) % 4];
        borderCheck(tR, tC, r7);

        //4
        tR = r - dr[d] + dr[(d + 3) % 4];
        tC = c - dc[d] + dc[(d + 3) % 4];
        borderCheck(tR, tC, r1);

        //5
        tR = r + 2 * dr[d];
        tC = c + 2 * dc[d];
        borderCheck(tR, tC, r5);

        //6
        tR = r + dr[d];
        tC = c + dc[d];
        borderCheck(tR, tC, a);

        //7
        tR = r + dr[d] + dr[(d + 1) % 4];
        tC = c + dc[d] + dc[(d + 1) % 4];
        borderCheck(tR, tC, r10);

        //8
        tR = r + dr[(d + 1) % 4];
        tC = c + dc[(d + 1) % 4];
        borderCheck(tR, tC, r7);

        //9
        tR = r - dr[d] + dr[(d + 1) % 4];
        tC = c - dc[d] + dc[(d + 1) % 4];
        borderCheck(tR, tC, r1);

        //10
        tR = r + 2 * dr[(d + 1) % 4];
        tC = c + 2 * dc[(d + 1) % 4];
        borderCheck(tR, tC, r2);

        map[r][c] = 0; //토네이도 부분은 초기화
    }

    static void move() {
        int r = N / 2; //가운데 칸부터 시작
        int c = N / 2;
        int d = 0; //초기 방향은 왼쪽부터 시작

        int idx = 1;
        int cnt = 0;
        while (true) {
            for (int i = 0; i < idx; i++) {
                r += dr[d];
                c += dc[d];

                spread(r, c, d); // 방향 별로 퍼트리기
            }

            if (idx == N - 1) { //idx가 N-1일때 3번 돌면 0,0으로 도착
                if (++cnt == 3) break;
            }else {
                if (++cnt == 2){ //2번 같은 idx로 이동하면 idx+1 해줌
                    idx++;
                    cnt = 0; //초기화
                }
            }

            d++;
            d %= 4;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
    }
}