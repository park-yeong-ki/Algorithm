import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Fish{
        int d; // 좌표와 방향
        boolean isMove; //이동 여부

        public Fish(int d, boolean isMove) {
            this.d = d;
            this.isMove = isMove;
        }
    }

    static class Shark{
        int r, c;

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int M, S;
    static Shark shark;

    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}; //1~8
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sr = {-1, 0, 1, 0}; //상좌하우
    static int[] sc = {0, -1, 0, 1};
    static ArrayList<Fish>[][] map;
    static int[][] smell;
    static List<int[]> temp, list;
    static int max;

    public static void main(String[] args) {
        input();
        while (S > 0) {
            updateSmell();
            setFish();
            move();
            max = Integer.MIN_VALUE;
            temp = new ArrayList<>();
            list = new ArrayList<>();
            sharkMove(shark.r, shark.c, 0);
            removeFish();
            removeSmell();
            S--;
        }

        System.out.println(countFish());
    }

    static void setFish() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    if (map[i][j].get(k).isMove) map[i][j].get(k).isMove = false;
                }
            }
        }
    }

    static int countFish() {
        int count = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                count += map[i][j].size();
            }
        }
        return count;
    }

    static void updateSmell() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (smell[i][j] >= 1) smell[i][j]++; //냄새를 1씩 올려줌
            }
        }
    }

    static void removeSmell() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (smell[i][j] >= 3) smell[i][j] = 0; //2번전 연습에서 생긴 냄새 제거
            }
        }
    }

    static void removeFish() {
        int r = 0;
        int c = 0;
        for (int i = 0; i < list.size(); i++) {
            r = list.get(i)[0];
            c = list.get(i)[1];
            for (int j = map[r][c].size() - 1; j >= 0; j--) { //역순으로 탐색하여 이동한 물고기를 다 탐색하면 종료
                if (map[r][c].get(j).isMove) {
                    map[r][c].remove(j);
                    smell[r][c] = 1; //냄새 표시
                } else {
                    break;
                }
            }
        }

        shark.r = r; //상어 위치 변경
        shark.c = c;
    }

    static void sharkMove(int r, int c, int cnt) {
        if (cnt == 3) {
            int num = 0;
            boolean[][] visited = new boolean[5][5];
            for (int i = 0; i < temp.size(); i++) {
                int tR = temp.get(i)[0];
                int tC = temp.get(i)[1];
                if (visited[tR][tC]) continue;
                for (int j = 0; j < map[tR][tC].size(); j++) {
                    if (map[tR][tC].get(j).isMove) num++;
                }
                visited[tR][tC] = true;
            }

            if (max < num) {
                max = num;
                list.clear();
                for (int i = 0; i < temp.size(); i++) {
                    list.add(temp.get(i).clone());
                }
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            int tR = r + sr[i];
            int tC = c + sc[i];

            if (tR < 1 || tR > 4 || tC < 1 || tC > 4) continue; //경계벗어나는 경우
            temp.add(new int[]{tR, tC});
            sharkMove(tR, tC, cnt + 1);
            temp.remove(temp.size() - 1);
        }
    }

    static void move() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                int size = map[i][j].size();
                for (int k = 0; k < size; k++) {
                    if (map[i][j].get(k).isMove) break;
                    Fish current = map[i][j].get(k);
                    int d = current.d;
                    int tR = i;
                    int tC = j;

                    while (true) {
                        tR = i + dr[d];
                        tC = j + dc[d];

                        if ((tR < 1 || tR > 4 || tC < 1 || tC > 4) || //격자범위 초과
                                (tR == shark.r && tC == shark.c) || //상어가 있는 칸인 경우
                                (smell[tR][tC] != 0)){ //냄새있는 칸
                            d--; //반시계방향 회전
                            if (d == 0) d = 8;
                            if (d == current.d){
                                tR = i; //이동안함
                                tC = j;
                                break; //한바퀴 다 회전한 경우
                            }
                            continue;
                        }

                        break;
                    }

                    map[tR][tC].add(new Fish(d, true));
                    //기존의 상어는 삭제안함 -> 복제해야되므로
                }
            }
        }
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        S = sc.nextInt();

        smell = new int[5][5];
        map = new ArrayList[5][5]; //1,1 -> 4,4
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            Fish fish = new Fish(d, false);
            map[r][c].add(fish);
        }

        shark = new Shark(sc.nextInt(), sc.nextInt()); //상어
    }
}