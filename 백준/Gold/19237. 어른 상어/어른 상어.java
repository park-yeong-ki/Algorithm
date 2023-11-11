import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, k;
    static class Node{
        int num, time; //상어 번호와 냄새 남은 시간

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    static Node[][] map;
    static Node[][] copyMap;

    static class Shark {
        int r, c, d;

        public Shark(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static Shark[] sharks;
    static int[] dr = {-1, 1, 0, 0}; //위, 아래, 왼쪽, 오른쪽
    static int[] dc = {0, 0, -1, 1};
    static int[][][] dir;

    public static void main(String[] args) throws IOException {
        input();
        int ans = 0;
        while (!isOne()) {
            if (ans >= 1000){ //1000초가 넘어도 상어가 남아 있는 경우
                ans = -1;
                break;
            }

            makeNewMap(); //카피맵 생성
            moveShark(); //전체 상어 이동
            map = copyMap; //기존맵 변경
            ans++;
        }

        System.out.println(ans);
    }

    static void makeNewMap() {
        copyMap = new Node[N][N]; //카피맵 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].num == 0 || map[i][j].time <= 1) copyMap[i][j] = new Node(0, 0); //상어의 냄새가 없는 경우
                else copyMap[i][j] = new Node(map[i][j].num, map[i][j].time - 1); //시간 1씩 빼주기
            }
        }
    }

    static void moveShark() {
        for (int i = 1; i <= M; i++) {
            if (sharks[i] == null) continue; //죽은 상어는 통과

            int d = sharks[i].d; //상어 현재 방향

            boolean flag = true; //냄새가 없는 칸이 있는지 먼저 탐색
            for (int j = 0; j < 4; j++) { //우선순위별로 탐색
                int pd = dir[i][d][j]; //우선순위 방향

                int tR = sharks[i].r + dr[pd];
                int tC = sharks[i].c + dc[pd];

                if (tR < 0 || tR >= N || tC <0 || tC >= N) continue; //경계초과하는 경우 통과
                if (map[tR][tC].num != 0) continue; // 냄새가 있는 경우 통과

                if (copyMap[tR][tC].num != 0) { //상어가 겹치는 경우
                    if (copyMap[tR][tC].num > i) { //작은 번호 상어라서 이동가능한 경우
                        sharks[copyMap[tR][tC].num] = null; //기존 상어 죽이기
                        copyMap[tR][tC] = new Node(i, k); //카피맵에 상어냄새 표시
                        sharks[i].d = pd; //상어의 방향 변경
                        sharks[i].r = tR; //상어 위치 변경
                        sharks[i].c = tC;
                    } else {
                        sharks[i] = null; //현재 상어 죽이기
                    }
                } else { //겹치지 않는 경우
                    copyMap[tR][tC] = new Node(i, k); //카피맵에 상어냄새 표시
                    sharks[i].d = pd; //상어의 방향 변경
                    sharks[i].r = tR; //상어 위치 변경
                    sharks[i].c = tC;
                }

                flag = false; //이동 체크
                break;
            }

            if (flag){ //냄새가 없는 칸이 없는 경우
                for (int j = 0; j < 4; j++) {
                    int pd = dir[i][d][j]; //우선순위 방향

                    int tR = sharks[i].r + dr[pd];
                    int tC = sharks[i].c + dc[pd];

                    if (tR < 0 || tR >= N || tC <0 || tC >= N) continue; //경계초과하는 경우 통과
                    if (map[tR][tC].num != i) continue; //현재 상어가 지나온 칸이 아닌 경우 통과

                    if (copyMap[tR][tC].num == 0 || copyMap[tR][tC].num == i){ //상어가 겹치지 않는 경우
                        copyMap[tR][tC] = new Node(i, k); //카피맵에 상어냄새 표시
                        sharks[i].d = pd; //상어의 방향 변경
                        sharks[i].r = tR; //상어 위치 변경
                        sharks[i].c = tC;
                    }else {
                        if (copyMap[tR][tC].num > i){ //작은 번호 상어라서 이동가능한 경우
                            sharks[copyMap[tR][tC].num] = null; //기존 상어 죽이기
                            copyMap[tR][tC] = new Node(i, k); //카피맵에 상어냄새 표시
                            sharks[i].d = pd; //상어의 방향 변경
                            sharks[i].r = tR; //상어 위치 변경
                            sharks[i].c = tC;
                        }else {
                            sharks[i] = null; //현재 상어 죽이기
                        }
                    }

                    break;
                }
            }

        }
    }

    static boolean isOne(){
        int cnt = 0;
        for (int i = 1; i <= M; i++) {
            if (sharks[i] != null) cnt++;
        }

        return cnt == 1;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행 크기
        M = Integer.parseInt(st.nextToken()); //상어수
        k = Integer.parseInt(st.nextToken()); //처음 냄새 시간

        map = new Node[N][N]; //상어번호, 남은 냄새 시간 저장할 지도

        sharks = new Shark[M + 1]; //상어 위치 저장 배열 -> 1번부터 시작

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 0){ //빈칸인 경우
                    map[i][j] = new Node(0, 0);
                }else { //상어가 존재하는 경우
                    sharks[n] = new Shark(i, j, 0); //상어 위치 저장 -> 방향은 나중에 저장
                    map[i][j] = new Node(n, k); //지도에 상어 번호, 냄새 표시
                }
            }
        }

        st = new StringTokenizer(br.readLine()); //상어 방향
        for (int i = 1; i <= M; i++) {
            sharks[i].d = Integer.parseInt(st.nextToken()) - 1; //0~3번으로 변경
        }

        dir = new int[M + 1][4][4]; //방향 저장 배열
        for (int i = 1; i <= M; i++) { //각 상어마다 우선순위 방향
            for (int j = 0; j < 4; j++) { // 상하좌우 순서
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) { //우선순위
                    dir[i][j][l] = Integer.parseInt(st.nextToken()) - 1; //0~3번으로 변경
                }
            }
        }

    }
}