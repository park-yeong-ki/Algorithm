import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Fish{
        int r, c, d;

        Fish(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static Fish[] fishes;
    static int[][] map;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //1부터 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish shark;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        shark = new Fish(0, 0, fishes[map[0][0]].d); //초기 상어
        int score = map[0][0]; //초기 점수
        fishes[map[0][0]] = null; //물고기 삭제
        map[0][0] = 0; //지도에 물고기 삭제

        dfs(score);

        System.out.println(max);
    }

    static void dfs(int score) {
        max = Math.max(max, score); //점수 최대값 갱신
        Fish[] copyFishes = new Fish[fishes.length];
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null) copyFishes[i] = null;
            else copyFishes[i] = new Fish(fishes[i].r, fishes[i].c, fishes[i].d);
        }
        int[][] copyMap = new int[map.length][map.length];
        for (int i = 0; i < copyMap.length; i++) {
            copyMap[i] = map[i].clone();
        }

        moveFish(); //물고기 이동

        for (int i = 1; i < 4; i++) {
            int tR = shark.r + i * dr[shark.d];
            int tC = shark.c + i * dc[shark.d];

            if (tR < 0 || tR >= 4 || tC <0 || tC >= 4) continue; //경계밖인 경우는 통과
            if (map[tR][tC] == 0) continue; //물고기가 없으면 이동 못함
            int prevD = shark.d;
            int prevR = shark.r;
            int prevC = shark.c;
            int num = map[tR][tC];

            shark.r = tR;
            shark.c = tC;
            shark.d = fishes[num].d; //먹은 물고기 방향 가짐
            fishes[num] = null;
            map[tR][tC] = 0;
            dfs(score + num);
            map[tR][tC] = num;
            fishes[num] = new Fish(tR, tC, shark.d);
            shark.d = prevD;
            shark.c = prevC;
            shark.r = prevR;
        }

        fishes = copyFishes;
        map = copyMap;
    }

    static void moveFish() {
        for (int i = 1; i <= 16; i++) {
            if (fishes[i] == null) continue; //죽은 물고기는 건너뜀

            while (true) {
                int tR = fishes[i].r + dr[fishes[i].d];
                int tC = fishes[i].c + dc[fishes[i].d];

                if ((tR < 0 || tR >= 4 || tC < 0 || tC >= 4) //경계밖인 경우
                        || (tR == shark.r && tC == shark.c)) { //상어칸인 경우
                    int dir = (fishes[i].d + 1) % 8;
                    fishes[i].d = dir; //반시계방향으로 45도 회전
                } else {
                    int currentR = fishes[i].r;
                    int currentC = fishes[i].c;
                    if (map[tR][tC] != 0) { //물고기 칸인 경우
                        int prevNum = map[tR][tC];

                        map[currentR][currentC] = prevNum;
                        fishes[prevNum].r = currentR;
                        fishes[prevNum].c = currentC;
                    } else { //빈 칸인 경우
                        map[currentR][currentC] = 0; //지난 칸은 0으로 초기화
                    }

                    map[tR][tC] = i; //새로운 칸으로 물고기 번호 입력
                    fishes[i].r = tR; //물고기 위치 변경
                    fishes[i].c = tC;
                    break;
                }
            }
        }
    }

    static void input() throws IOException {
        fishes = new Fish[17];
        map = new int[4][4];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                fishes[num] = new Fish(i, j, dir - 1); //방향은 1을 빼서 저장 0~7까지
                map[i][j] = num;
            }
        }
    }
}