import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] students;
    static int[] order;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int score;
    public static void main(String[] args) throws IOException {
        input();
        setPosition();
        calculate();
        System.out.println(score);
    }

    static void calculate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = map[i][j];
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int tR = i + dr[k];
                    int tC = j + dc[k];

                    if (tR <0 || tR >= N || tC < 0 || tC >= N) continue; //경계 초과

                    for (int l = 0; l < 4; l++) {
                        if (students[num].get(l) == map[tR][tC]) {
                            cnt++;
                            break;
                        }
                    }
                }

                switch (cnt) {
                    case 0:
                        score += 0;
                        break;
                    case 1:
                        score += 1;
                        break;
                    case 2:
                        score += 10;
                        break;
                    case 3:
                        score += 100;
                        break;
                    case 4:
                        score += 1000;
                        break;
                }
            }
        }
    }

    static void setPosition(){
        for (int i = 0; i < order.length; i++) { //전체 학생
            int num = order[i]; //학생 번호

            int maxS = 0; //학생이 가질 수 있는 인접한 최대 좋아하는 학생 수
            int maxE = -1; //좋아하는 학생 수에 따른 최대 빈칸 수
            int row = 0;
            int col = 0;
            for (int j = 0; j < N; j++) { //행
                for (int k = 0; k < N; k++) { //열
                    if (map[j][k] != 0) continue; //이미 학생이 지정된 위치면 통과

                    int sCnt = 0; //좋아하는 학생수
                    int eCnt = 0; //빈칸 수

                    for (int l = 0; l < 4; l++) { //상하좌우
                        int tR = j + dr[l];
                        int tC = k + dc[l];

                        if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue; //경계초과하면 통과

                        if (map[tR][tC] == 0){ //빈칸인 경우
                            eCnt++;
                            continue;
                        }

                        for (int m = 0; m < 4; m++) { //좋아하는 학생수
                            if (map[tR][tC] == students[num].get(m)) {
                                sCnt++;
                                break;
                            }
                        }
                    }

                    if (maxS < sCnt) {
                        maxS = sCnt; //좋아하는 최대 학생수 갱신
                        maxE = eCnt;

                        row = j; //위치 저장
                        col = k;
                    } else if (maxS == sCnt) {
                        if (maxE < eCnt) { //좋아하는 학생수가 같으면 빈칸 수로 비교
                            maxE = eCnt;

                            row = j; //위치 저장
                            col = k;
                        }
                    }
                    //행 작은 순, 열 작은 순 갱신은 반복문 순서로 해결
                }
            }
            map[row][col] = num; //배열에 학생 배치
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        students = new ArrayList[N * N + 1]; //N^2학생 생성
        for (int i = 1; i <= N*N; i++) {
            students[i] = new ArrayList<>();
        }

        order = new int[N * N]; //순서 저장
        StringTokenizer st;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); //1번 부터 N*N번 학생까지
            order[i] = num;
            for (int j = 0; j < 4; j++) { //좋아하는 학생 입력
                students[num].add(Integer.parseInt(st.nextToken()));
            }
        }

        map = new int[N][N];
        score = 0;
    }
}