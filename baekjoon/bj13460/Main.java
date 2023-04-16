package bj13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] board;
    static class ball {
        int rR, rC, bR, bC;
        public ball(int rR, int rC, int bR, int bC) {
            this.rR = rR;
            this.rC = rC;
            this.bR = bR;
            this.bC = bC;
        }
    }
    static ball start;
    static int N;
    static int M;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;
    static int trR, trC, tbR, tbC;

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<ball> queue = new ArrayDeque<>();

        queue.add(start);

        ball current;
        int size, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (cnt > 10) {
                    result = -1;
                    return;
                }else if (board[current.bR][current.bC] == 'O') {
                    continue;
                } else if (board[current.rR][current.rC] == 'O') {
                    result = cnt;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    trR = current.rR;
                    trC = current.rC;
                    tbR = current.bR;
                    tbC = current.bC;
                    if (j == 0 && trC == tbC && trR < tbR) { //상
                        moveRed(j);
                        moveBlue(j);
                    } else if (j == 0 && trC == tbC && trR > tbR) { //상
                        moveBlue(j);
                        moveRed(j);
                    } else if (j == 1 && trC == tbC && trR > tbR) { //하
                        moveRed(j);
                        moveBlue(j);
                    } else if (j == 1 && trC == tbC && trR < tbR) {
                        moveBlue(j);
                        moveRed(j);
                    } else if (j == 2 && trR == tbR && trC < tbC) {
                        moveRed(j);
                        moveBlue(j);
                    } else if (j == 2 && trR == tbR && trC > tbC) {
                        moveBlue(j);
                        moveRed(j);
                    } else if (j == 3 && trR == tbR && trC > tbC) {
                        moveRed(j);
                        moveBlue(j);
                    } else if (j == 3 && trR == tbR && trC < tbC) {
                        moveBlue(j);
                        moveRed(j);
                    } else {
                        moveRed(j);
                        moveBlue(j);
                    }

                    queue.add(new ball(trR, trC, tbR, tbC));
                }
            }
            cnt++;
        }
    }

    static void moveRed(int j) {
        while (trR >= 0 && trR < N && trC >= 0 && trC < M) {
            trR += dr[j];
            trC += dc[j];

            if (board[trR][trC] == 'O') {
                break;
            }else if (board[trR][trC] == '#' || (trR == tbR && trC == tbC)) {
                trR -= dr[j];
                trC -= dc[j];
                break;
            }
        }
    }

    static void moveBlue(int j){
        while (tbR >= 0 && tbR < N && tbC >= 0 && tbC < M) {
            tbR += dr[j];
            tbC += dc[j];

            if (board[tbR][tbC] == 'O') {
                break;
            } else if (board[tbR][tbC] == '#' || (trR == tbR && trC == tbC)) {
                tbR -= dr[j];
                tbC -= dc[j];
                break;
            }
        }
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        int bR = 0, bC = 0, rR = 0, rC = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'B') {
                    bR = i;
                    bC = j;
                    board[i][j] = '.';
                }
                else if (board[i][j] == 'R') {
                    rR = i;
                    rC = j;
                    board[i][j] = '.';
                }
            }
        }
        start = new ball(rR, rC, bR, bC);
    }
}
