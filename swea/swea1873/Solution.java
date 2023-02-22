package swea1873;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static char[][] map;
    static char[] tank = {'^', 'v', '<', '>'};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int H;
    static int W;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //H, W입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            //맵 입력
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                map[i] = st.nextToken().toCharArray();
            }

            //입력의 개수 N입력
            int N = Integer.parseInt(br.readLine());
            //명령 입력
            char[] command = br.readLine().toCharArray();

            //맵에서 전차 찾기
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    for (int k = 0; k < tank.length; k++) {
                        if (map[i][j] == tank[k]) {
                            r = i;
                            c = j;
                        }
                    }
                }
            }

            //명령 실행
            for (int i = 0; i < N; i++) {
                battleField(command[i], r, c);
            }

            //출력
            bw.write("#" + test_case + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    bw.write(map[i][j]);
                }
                bw.newLine();
            }
        }
        bw.close();
    }

    static void battleField(char cmd, int x, int y) {
        switch (cmd) {
            case 'U':
                //방향을 바꾼다
                map[x][y] = tank[0];
                //맵 안이라면 이동
                if(x + dx[0] >= 0) move(x, y, 0);
                break;
            case 'D':
                //방향을 바꾼다
                map[x][y] = tank[1];
                //맵 안이라면 이동
                if (x + dx[1] < H) move(x, y, 1);
                break;
            case 'L':
                //방향을 바꾼다
                map[x][y] = tank[2];
                //맵 안이라면 이동
                if (y + dy[2] >= 0) move(x, y, 2);
                break;
            case 'R':
                //방향을 바꾼다
                map[x][y] = tank[3];
                //맵 안이라면 이동
                if (y + dy[3] < W) move(x, y, 3);
                break;
            case 'S':
                //포탄발사
               shoot(x, y);
        }
    }

    //전차 이동 함수
    static void move(int x, int y, int d) {
        if(map[x+dx[d]][y+dy[d]] == '.'){
            //전차 이동
            map[x+dx[d]][y+dy[d]] = tank[d];
            map[x][y] = '.';
            r = x + dx[d];
            c = y + dy[d];
        }
    }

    //포탄 발사 함수
    static void shoot(int x, int y) {
        switch (map[x][y]){
            case '^':
                for (int i = x; i >= 0; i--) {
                    if (map[i][y] == '*') { //벽돌로 만든 벽에 만나면 평지로 만들고 종료
                        map[i][y] = '.';
                        break;
                    } else if (map[i][y] == '#') { //강철로 만든 벽에 만나면 그냥 종료
                        break;
                    }
                }
                break;
            case 'v':
                for (int i = x; i < H; i++) {
                    if (map[i][y] == '*') { //벽돌로 만든 벽에 만나면 평지로 만들고 종료
                        map[i][y] = '.';
                        break;
                    } else if (map[i][y] == '#') { //강철로 만든 벽에 만나면 그냥 종료
                        break;
                    }
                }
                break;
            case '<':
                for (int i = y; i >= 0; i--) {
                    if (map[x][i] == '*') { //벽돌로 만든 벽에 만나면 평지로 만들고 종료
                        map[x][i] = '.';
                        break;
                    } else if (map[x][i] == '#') { //강철로 만든 벽에 만나면 그냥 종료
                        break;
                    }
                }
                break;
            case '>':
                for (int i = y; i < W; i++) {
                    if (map[x][i] == '*') { //벽돌로 만든 벽에 만나면 평지로 만들고 종료
                        map[x][i] = '.';
                        break;
                    } else if (map[x][i] == '#') { //강철로 만든 벽에 만나면 그냥 종료
                        break;
                    }
                }
                break;
        }
    }
}
