import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int m, s, d, cnt1, cnt2; //질량, 속도, 방향, 방향 홀수 개수, 방향 짝수 개수

        public Node(int m, int s, int d, int cnt1, int cnt2) {
            this.m = m;
            this.s = s;
            this.d = d;
            this.cnt1 = cnt1;
            this.cnt2 = cnt2;
        }
    }

    static Node[][] map;
    static Node[][] newMap;
    static int N, M, K, ans;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < K; i++) {
            newMap = new Node[N][N]; //이동할 파이어볼 저장할 새로운 지도
            move();
            map = newMap; //배열 새로 바꾸기
        }
        countM();

        System.out.println(ans);
    }

    static void countM() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) continue;
                if (map[i][j].cnt1 + map[i][j].cnt2 != 1) ans += (map[i][j].m / 5) * 4;
                else ans += map[i][j].m;
            }
        }
    }

    static void setNewMap(int i, int j, int m, int s, int d) {
        //경계 조건없이 1번 행은 N번 행과 연결, 1번 열은 N번 열과 연결
        int tR = (i + s * dr[d]) % N; //새로운 행
        int tC = (j + s * dc[d]) % N; //새로운 열

        if (tR < 0) tR += N;
        if (tC < 0) tC += N;

        int cnt1 = 0;
        int cnt2 = 0;
        if (isOdd(d)) cnt1++;
        else cnt2++;

        if (newMap[tR][tC] == null) { //이동할 위치가 겹치지 않는 경우
            newMap[tR][tC] = new Node(m, s, d, cnt1, cnt2);
        } else { //이동할 위치가 겹치는 경우
            newMap[tR][tC].m += m;
            newMap[tR][tC].s += s;
            newMap[tR][tC].cnt1 += cnt1;
            newMap[tR][tC].cnt2 += cnt2;
        }
    }

    static void move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) continue; //파이어볼이 없는 경우

                int cnt = map[i][j].cnt1 + map[i][j].cnt2;
                if (cnt == 1){ //1개인 경우
                    int m = map[i][j].m; //질량
                    int s = map[i][j].s; //속도
                    int d = map[i][j].d; //방향

                    setNewMap(i, j, m, s, d);

                }else { //2개 이상인 경우
                    int m = map[i][j].m / 5; //질량
                    int s = map[i][j].s / (cnt); //속력

                    if (m == 0) continue; //질량이 0이면 이동x

                    if (cnt == map[i][j].cnt1 || cnt == map[i][j].cnt2) { //방향이 모두 짝수거나 모두 홀수
                        for (int k = 0; k < 8; k += 2) {
                            setNewMap(i, j, m, s, k);
                        }
                    } else {
                        for (int k = 1; k < 8; k+=2) {
                            setNewMap(i, j, m, s, k);
                        }
                    }
                }
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Node[N][N]; // 0~N-1로 저장하기

        for (int i = 0; i < M; i++) { //초기 파이어볼 지도에 저장
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; //1부터 시작이니까 -1해줌
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int cnt1 = 0;
            int cnt2 = 0;
            if (isOdd(d)) cnt1++;
            else cnt2++;

            map[r][c] = new Node(m, s, d, cnt1, cnt2);
        }

        ans = 0;
    }

    static boolean isOdd(int num){
        return num % 2 == 1;
    }
}