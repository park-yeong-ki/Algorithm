import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, r, c, rb, total, score;
    static int[][] map, copyMap;
    static boolean[][] visited;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        while (true) {
            findGroup(); //가장 큰 블록그룹 찾기
            if (total < 2) break; //블록 그룹을 찾지 못한 경우
            remove(new Point(r, c)); //가장 큰 블록그룹 제거
            move(); //중력 적용
            rotate(); //반시계 방향으로 90돟 회전
            move(); //중력 적용
        }

        System.out.println(score);
    }

    static void findGroup() {
        //가장 큰 블록 그룹 정보 초기화
        r = 0;
        c = 0;
        rb = 0;
        total = Integer.MIN_VALUE;

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < 1) continue; //일반 블록이 아닌 경우
                if (visited[i][j]) continue; //방문한 블록인 경우
                bfs(new Point(i, j));
            }
        }
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited2 = new boolean[N][N]; //무지개 블록 방문 표시할 배열

        queue.add(start);
        visited[start.r][start.c] = true;

        Point current;
        int cR = Integer.MAX_VALUE; //기준블록 행
        int cC = Integer.MAX_VALUE; //기준블록 열
        int cRB = 0; //무지개 블록 수
        int cTotal = 0; //총 블록 수
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (map[current.r][current.c] == 0) cRB++; //무지개 블록 수 갱신
            else { //기준 블록 갱신
                if (cR > current.r) { //행의 번호가 작은 경우
                    cR = current.r;
                    cC = current.c;
                } else if (cR == current.r) { //행의 번호가 같은 경우
                    if (cC > current.c){ //열의 번호가 작은 경우
                        cC = current.c;
                    }
                }
            }

            cTotal++; //총 블록 수 갱신

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue; // 경계초과하는 경우
                if (map[tR][tC] != map[start.r][start.c] && map[tR][tC] != 0) continue; //같은 일반 블록이 아니고 무지개 블록도 아닌 경우
                if (visited[tR][tC] || visited2[tR][tC]) continue; //방문한 일반 블록이거나 방문한 무지개 블록인 경우

                queue.add(new Point(tR, tC));
                if (map[tR][tC] == map[start.r][start.c]) visited[tR][tC] = true; //일반 블록 방문체크
                else visited2[tR][tC] = true; //무지개 블록 방문체크
            }

        }

        if (total < cTotal){ //크기가 큰 경우 갱신
            total = cTotal;
            rb = cRB;
            r = cR;
            c = cC;
        } else if (total == cTotal) { //크기가 같은 경우
            if (rb < cRB){ //무지개 블록이 더 많은 경우
                rb = cRB;
                r = cR;
                c = cC;
            } else if (rb == cRB) { //무지개 블록 수가 같은 경우
                if (r < cR){ //기준블록 행이 큰 경우
                    r = cR;
                    c = cC;
                } else if (r == cR) { //기준블록 행이 같은 경우
                    if (c < cC){ //기준블록 열이 큰 경우
                        c = cC;
                    }
                }
            }
        }

    }

    static void remove(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited2 = new boolean[N][N];

        queue.add(start);
        visited2[start.r][start.c] = true;

        Point current;
        int cnt = 0;
        int sNum = map[start.r][start.c];
        while (!queue.isEmpty()) {
            current = queue.poll();
            map[current.r][current.c] = -2; //제거
            cnt++; //제거한 칸 수 체크

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue; //경계 초과
                if (visited2[tR][tC]) continue; //이미 방문한 경우
                if (map[tR][tC] != sNum && map[tR][tC] != 0) continue; //같은 일반 블록이 아니고 무지개 블록도 아닌 경우

                queue.add(new Point(tR, tC));
                visited2[tR][tC] = true;
            }
        }

        score += cnt * cnt; //제거한 블록 수의 제곱을 더해서 점수 갱신
    }

    static void move() {
        for (int i = N-1; i >= 0; i--) { //아래행부터 이동
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -2) continue; //빈공간인 경우
                if (map[i][j] == -1) continue; //검은색 블록인 경우
                int tR = i;
                while (true) {
                    tR++;
                    if (tR >= N) break; //경계를 초과하면 종료
                    if (map[tR][j] != -2) break; //빈공간이 아닌 다른 블록을 만나면 종료

                    map[tR][j] = map[tR - 1][j]; //이동
                    map[tR - 1][j] = -2; //이전칸 빈칸으로 초기화
                }
            }
        }
    }

    static void rotate() {
        copyMap = new int[N][N]; //회전 배열을 저장할 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[N - 1 - j][i] = map[i][j];
            }
        }

        map = copyMap; //기존 배열로 복사
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N]; //지도입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        score = 0;
    }
}