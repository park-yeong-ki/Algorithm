import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, o;
    static int[][] map, mArr, nextMap;
    static int[] dr = {0, -1, 1, 0, 0}; //1,2,3,4
    static int[] dc = {0, 0, 0, -1, 1};
    static int[] mr = {0, 1, 0, -1}; //좌하우상
    static int[] mc = {-1, 0, 1, 0};
    static int[] count;
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < M; i++) {
            ice(mArr[i][0], mArr[i][1]);
            move();
            while (boom() != 0) {
                move();
            }
            change();
        }

        output();
    }

    static void output() {
        int sum = 0;
        for (int i = 1; i < count.length; i++) {
            sum += i * count[i];
        }
        System.out.println(sum);
    }

    static void printMap() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void change() {
        int d = 0; //왼쪽 방향부터 시작
        int size = 1;
        int cnt = 0;
        int tr = o;
        int tc = o;
        List<Point> list = new ArrayList<>(); //연속 좌표들을 저장할 리스트
        list.add(new Point(tr, tc));
        List<Integer> nums = new ArrayList<>();

        while (cnt < K) { //역 달팽이 탐색
            tr += mr[d];
            tc += mc[d];

            if (tr < o - size || tr > o + size || tc < o - size || tc > o + size) { //경계초과
                tr -= mr[d]; //원위치
                tc -= mc[d];

                d++;
                if (d == 4) { //한바퀴를 돈 경우
                    d = 0;
                    size++;
                }
                continue;
            }

            Point prev = list.get(list.size() - 1);
            if (map[prev.r][prev.c] == map[tr][tc]) { //연속되는 경우
                list.add(new Point(tr, tc));
            } else { //연속x
                if (map[list.get(0).r][list.get(0).c] != 0) { //초기 제외
                    nums.add(list.size());
                    nums.add(map[list.get(0).r][list.get(0).c]);
                }

                list.clear(); //리스트 초기화
                list.add(new Point(tr, tc));
            }

            cnt++;
        }

        if (K == 0) return; //구슬칸이 남아있지 않는 경우

        nums.add(list.size());
        nums.add(map[list.get(0).r][list.get(0).c]);

        nextMap = new int[N + 1][N + 1];
        d = 0; //왼쪽 방향부터 시작
        size = 1;
        tr = o;
        tc = o;
        int idx = 0;
        while (true) { //역 달팽이 전체 탐색
            tr += mr[d];
            tc += mc[d];

            if (tr < o - size || tr > o + size || tc < o - size || tc > o + size) { //경계초과
                tr -= mr[d]; //원위치
                tc -= mc[d];

                d++;
                if (d == 4) { //한바퀴를 돈 경우
                    d = 0;
                    size++;
                }
                continue;
            }

            if (idx >= nums.size()) break;
            nextMap[tr][tc] = nums.get(idx++);
            if (tr == 1 && tc == 1) break;
        }

        K = idx; //구슬칸수 새로 갱신
        map = nextMap;
    }

    static int boom() {
        int d = 0; //왼쪽 방향부터 시작
        int size = 1;
        int cnt = 0;
        int tr = o;
        int tc = o;
        List<Point> list = new ArrayList<>(); //연속 좌표들을 저장할 리스트
        list.add(new Point(tr, tc));
        int removeN = 0; //지운 숫자
        while (cnt < K) { //역 달팽이 탐색
            tr += mr[d];
            tc += mc[d];

            if (tr < o - size || tr > o + size || tc < o - size || tc > o + size) { //경계초과
                tr -= mr[d]; //원위치
                tc -= mc[d];

                d++;
                if (d == 4) { //한바퀴를 돈 경우
                    d = 0;
                    size++;
                }
                continue;
            }

            Point prev = list.get(list.size() - 1);
            if (map[prev.r][prev.c] == map[tr][tc]) { //연속되는 경우
                list.add(new Point(tr, tc));
            } else { //연속x
                if (list.size() >= 4) { //4개이상 연속된 경우
                    for (Point p : list) {
                        count[map[p.r][p.c]]++;
                        map[p.r][p.c] = 0; //지우기
                        removeN++;
                    }
                }

                list.clear(); //리스트 초기화
                list.add(new Point(tr, tc));
            }

            cnt++;
        }

        if (list.size() >= 4) { //마지막 부분에서 4개이상 연속된 경우
            for (Point p : list) {
                count[map[p.r][p.c]]++;
                map[p.r][p.c] = 0; //지우기
                removeN++;
            }
        }

        K -= removeN; //구슬칸수 갱신

        return removeN;
    }

    static void move() {
        int d = 0; //왼쪽 방향부터 시작
        int size = 1;
        int cnt = 0;
        int tr = o;
        int tc = o;
        List<Point> list = new ArrayList<>(); //앞의 좌표들을 저장할 리스트
        while (cnt < K) { //역 달팽이 탐색
            tr += mr[d];
            tc += mc[d];

            if (tr < o - size || tr > o + size || tc < o - size || tc > o + size) { //경계초과
                tr -= mr[d]; //원위치
                tc -= mc[d];

                d++;
                if (d == 4) { //한바퀴를 돈 경우
                    d = 0;
                    size++;
                }
                continue;
            }

            if (map[tr][tc] != 0) cnt++; //탐색 표시

            int cr = tr;
            int cc = tc;
            Point prev;
            for (int i = list.size() - 1; i >= 0; i--) { //앞의 빈공간 채우기
                prev = list.get(i);
                if (map[prev.r][prev.c] != 0) break;
                cr = prev.r;
                cc = prev.c;
            }

            if (cr != tr || cc != tc) { //빈공간이 있는 경우
                map[cr][cc] = map[tr][tc];
                map[tr][tc] = 0;
            }

            list.add(new Point(tr, tc)); //좌표 추가
        }
    }

    static void ice(int d, int s) {
        while (s > 0) {
            int tr = o + s * dr[d];
            int tc = o + s * dc[d];
            if (map[tr][tc] != 0) {
                map[tr][tc] = 0;
                K--;
            }
            s--;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        K = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) K++;
            }
        }

        mArr = new int[M][2]; //방향, 거리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            mArr[i][0] = Integer.parseInt(st.nextToken());
            mArr[i][1] = Integer.parseInt(st.nextToken());
        }

        o = (N + 1) / 2;
        count = new int[4];
    }
}