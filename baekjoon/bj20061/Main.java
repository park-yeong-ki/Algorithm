package bj20061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, length, score;
    static int[][] info;
    static boolean[][] map; //총 보드
    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < N; i++) {
            moveBlock(info[i][0], info[i][1], info[i][2]); //블록 종류, x좌표, y좌표
            removeBlock();
            hasMidBlock();
        }
        System.out.println(score);
        System.out.println(remainTile());
    }

    static void hasMidBlock() {
        //파란색 중간 확인
        int bCnt = 0;
        for (int i = 4; i <= 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[j][i]){ //블록이 있는 열의 수 체크
                    bCnt++;
                    break;
                }
            }
        }

        for (int i = 0; i < bCnt; i++) { //블록이 있는 열의 수 만큼 이동
            for (int j = length - 1; j >= 4; j--) { //파란색 전체
                map[0][j] = map[0][j - 1];
                map[1][j] = map[1][j - 1];
                map[2][j] = map[2][j - 1];
                map[3][j] = map[3][j - 1];
            }
        }

        //초록색 중간 확인
        int gCnt = 0;
        for (int i = 4; i <= 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j]){ //블록이 있는 행의 수 체크
                    gCnt++;
                    break;
                }
            }
        }

        for (int i = 0; i < gCnt; i++) { //블록이 있는 행의 수만큼 이동
            for (int j = length - 1; j >= 4; j--) { //초록색 전체
                map[j][0] = map[j - 1][0];
                map[j][1] = map[j - 1][1];
                map[j][2] = map[j - 1][2];
                map[j][3] = map[j - 1][3];
            }
        }

    }

    static int remainTile() {
        int cnt = 0;
        for (int i = 4; i < length; i++) { //파란색보드
            for (int j = 0; j < 4; j++) {
                if (map[j][i]) cnt++;
            }
        }

        for (int i = 4; i < length; i++) { //초록색보드
            for (int j = 0; j < 4; j++) {
                if (map[i][j]) cnt++;
            }
        }

        return cnt;
    }

    static void removeBlock() {
        int bCnt = 0;
        int bIdx = length;
        for (int i = 6; i < length; i++) { //파란색보드 -> 열이 가득차야함
            if (map[0][i] && map[1][i] && map[2][i] && map[3][i]){ //열이 가득 찬 경우
                bCnt++; //없어진 열 개수
                bIdx = Math.min(i, bIdx); //없어진 열의 가장 왼쪽
                map[0][i] = map[1][i] = map[2][i] = map[3][i] = false; //제거
            }
        }

        for (int i = 0; i < bCnt; i++) { //없어진 열만큼 이동
            for (int j = bIdx; j >= 4; j--) {
                map[0][j] = map[0][j - 1];
                map[1][j] = map[1][j - 1];
                map[2][j] = map[2][j - 1];
                map[3][j] = map[3][j - 1];
            }
            bIdx++;
        }

        int gCnt = 0;
        int gIdx = length;
        for (int i = 6; i < length; i++) { //초록색보드 -> 행이 가득차야함
            if (map[i][0] && map[i][1] && map[i][2] && map[i][3]) { //행이 가득찬 경우
                gCnt++; //없어진 행의 개수
                gIdx = Math.min(i, gIdx); //없어진 행의 가장 위쪽
                map[i][0] = map[i][1] = map[i][2] = map[i][3] = false; //제거
            }
        }

        for (int i = 0; i < gCnt; i++) { //없어진 행만큼 이동
            for (int j = gIdx; j >= 4; j--) {
                map[j][0] = map[j - 1][0];
                map[j][1] = map[j - 1][1];
                map[j][2] = map[j - 1][2];
                map[j][3] = map[j - 1][3];
            }
            gIdx++;
        }

        score += bCnt + gCnt;
    }

    static void moveBlock(int t, int x, int y) { //블록 이동
        switch (t){ //블록 종류별 초기 위치에 넣기
            case 1: //1x1
                map[x][y] = true;

                for (int i = x+1; i < length; i++) { //초록색 이동 -> 아래 이동
                    if (map[i][y]){ //아래쪽 블럭이 이미 있는 경우
                        break;
                    }else { //이동할 수 있는 경우
                        map[i - 1][y] = false;
                        map[i][y] = true;
                    }
                }

                for (int i = y+1; i < length; i++) { //파란색 이동 -> 오른쪽 이동
                    if (map[x][i]) { //오른쪽 블럭이 이미 있는 경우
                        break;
                    } else { //이동할 수 있는 경우
                        map[x][i - 1] = false;
                        map[x][i] = true;
                    }
                }

                break;
            case 2: //1x2
                map[x][y] = true;
                map[x][y + 1] = true;

                for (int i = x+1; i < length; i++) { //초록색 이동 -> 아래 이동
                    if (map[i][y] || map[i][y+1]){ //아래쪽 블럭이 이미 있는 경우
                        break;
                    }else { //이동할 수 있는 경우
                        map[i - 1][y] = false;
                        map[i][y] = true;

                        map[i - 1][y + 1] = false;
                        map[i][y + 1] = true;
                    }
                }

                for (int i = y+2; i < length; i++) { //파란색 이동 -> 오른쪽 이동
                    if (map[x][i]) { //오른쪽 블럭이 이미 있는 경우
                        break;
                    } else { //이동할 수 있는 경우
                        map[x][i - 1] = false; //오른쪽 블럭 이동
                        map[x][i] = true;

                        map[x][i - 2] = false; //왼쪽 블럭 이동
                        map[x][i - 1] = true;
                    }
                }

                break;
            case 3: //2x1
                map[x][y] = true;
                map[x + 1][y] = true;

                for (int i = x+2; i < length; i++) { //초록색 이동 -> 아래 이동
                    if (map[i][y]){ //아래쪽 블럭이 이미 있는 경우
                        break;
                    }else { //이동할 수 있는 경우
                        map[i - 1][y] = false; // 아래 블럭 이동
                        map[i][y] = true;

                        map[i - 2][y] = false; //위쪽 블럭 이동
                        map[i - 1][y] = true;
                    }
                }

                for (int i = y+1; i < length; i++) { //파란색 이동 -> 오른쪽 이동
                    if (map[x][i] || map[x + 1][i]) { //오른쪽 블럭이 이미 있는 경우
                        break;
                    } else { //이동할 수 있는 경우
                        map[x][i - 1] = false; //오른쪽 블럭 이동
                        map[x][i] = true;

                        map[x + 1][i - 1] = false; //왼쪽 블럭 이동
                        map[x + 1][i] = true;
                    }
                }

                break;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //블록 놓는 횟수
        info = new int[N][3]; //블록 정보 저장
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()); //블록 종류 1-> 1x1(x,y), 2-> 1x2(x,y), (x,y+1), 3-> 2x1(x,y), (x+1,y)
            info[i][1] = Integer.parseInt(st.nextToken()); //블록 x좌표
            info[i][2] = Integer.parseInt(st.nextToken()); //블로 y 좌표
        }

        length = 10;
        map = new boolean[length][length]; //0~3까지는 빨강, 4~5까지는 연한색 초록, 파랑, 6~9까지는 진한색 초록, 파랑
        score = 0;
    }
}