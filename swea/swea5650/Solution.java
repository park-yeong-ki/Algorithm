package swea5650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, score, max;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            operate(i, j, k);
                            max = Math.max(max, score);
                            score = 0;
                        }
                    }
                }
            }
            sb.append("#" + test_case + " " + max + "\n");
        }
        System.out.print(sb);
    }

    static void operate(int r, int c, int d) {
        int sR = r;
        int sC = c;
        while (true) {
            r += dr[d];
            c += dc[d];

            //벽에 부딪히는지 확인 -> 부딪히면 방향 바꾸고 점수올리고 다음 차례로 넘어감
            int b = isBorder(r, c);
            if (b != -1) {
                d = b;
                score++;
                continue;
            }

            //출발위치로 돌아온경우, 블랙홀 확인
            if ((sR == r && sC == c) || map[r][c] == -1) {
                return;
            }

            //블록 반사
            switch (map[r][c]) {
                case 1:
                    switch (d) {
                        case 0:
                            d = 1; //튕김
                            break;
                        case 1:
                            d = 3; //직각
                            break;
                        case 2:
                            d = 0; //직각
                            break;
                        case 3:
                            d = 2; //튕김
                            break;
                    }
                    score++;
                    break;
                case 2:
                    switch (d) {
                        case 0:
                            d = 3; //직각
                            break;
                        case 1:
                            d = 0; //튕김
                            break;
                        case 2:
                            d = 1; //직각
                            break;
                        case 3:
                            d = 2; //튕김
                            break;
                    }
                    score++;
                    break;
                case 3:
                    switch (d) {
                        case 0:
                            d = 2; //직각
                            break;
                        case 1:
                            d = 0; //튕김
                            break;
                        case 2:
                            d = 3; //튕김
                            break;
                        case 3:
                            d = 1; //직각
                            break;
                    }
                    score++;
                    break;
                case 4:
                    switch (d) {
                        case 0:
                            d = 1; //튕김
                            break;
                        case 1:
                            d = 2; //직각
                            break;
                        case 2:
                            d = 3; //튕김
                            break;
                        case 3:
                            d = 0; //직각
                            break;
                    }
                    score++;
                    break;
                case 5:
                    switch (d) {
                        case 0:
                            d = 1; //튕김
                            break;
                        case 1:
                            d = 0; //튕김
                            break;
                        case 2:
                            d = 3; //튕김
                            break;
                        case 3:
                            d = 2; //튕김
                            break;
                    }
                    score++;
                    break;
            }

            //웜홀
            if (map[r][c] >= 6 && map[r][c] <= 10) {
                int num = map[r][c];
                outer:
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i == r && j == c) continue;
                        if (map[i][j] == num) {
                            r = i;
                            c = j;
                            break outer;
                        }
                    }
                }
            }
        }
    }

    static int isBorder(int r, int c) {
        int dir = -1;
        if (r < 0) dir = 1;
        else if (r >= N) dir = 0;
        else if (c < 0) dir = 3;
        else if (c >= N) dir = 2;

        return dir;
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = Integer.MIN_VALUE;
    }
}
