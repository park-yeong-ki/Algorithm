import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Point taxi;
    static Map<Integer, Point> customers;
    static int N, M, amount, cNum;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        while (M != 0 && amount >= 0){ //모든 손님이 탑승할때까지
            inCustomer(); //가장 가까운 손님 태우기
            outCustomer(); //목적지에 손님 내리기
            M--; //완료 손님 제거
        }

        System.out.println(amount);
    }

    static void outCustomer() { //목적지에 손님 내리기
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(taxi);
        visited[taxi.r][taxi.c] = true;

        Point current;
        int size;
        int dist = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            if (amount < 0 ) return; // 연료를 모두 소모한 경우

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                if (current.r == customers.get(cNum).r && current.c == customers.get(cNum).c) { //목적지에 도달한 경우
                    amount += 2 * dist; //목적지까지 이동한 만큼의 2배 연료 추가
                    taxi.r = current.r; //택시 위치 변경
                    taxi.c = current.c;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue; //경계를 초과하는 경우
                    if (map[tR][tC] == 1) continue; //벽에 부딪히는 경우
                    if (visited[tR][tC]) continue; //이미 방문한 경우

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }

            dist++;
            amount--; //연료 소모
        }

        amount = -1; //손님을 목적지로 데려가지 못하는 경우 amount를 -1로 초기화
        return;
    }

    static void inCustomer() { //손님 찾기
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N]; //방문 배열 생성

        queue.add(taxi); //택시 위치 넣기
        visited[taxi.r][taxi.c] = true;

        Point current;
        int size;
        Point customer = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE); //최대값으로 초기화
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (map[current.r][current.c] >= 2){ //손님을 찾은 경우
                    if (customer.r > current.r){ //행번호가 작은 경우
                        customer.r = current.r;
                        customer.c = current.c;
                    } else if (customer.r == current.r) { //행번호가 같은 경우
                        if (customer.c > current.c){ //열번호가 작은 경우
                            customer.c = current.c;
                        }
                    }
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= N || tC < 0 || tC >= N) continue; //경계초과하는 경우
                    if (map[tR][tC] == 1) continue; //벽에 부딪히는 경우
                    if (visited[tR][tC]) continue; //이미 방문한 경우

                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }

            if (customer.r != Integer.MAX_VALUE && customer.c != Integer.MAX_VALUE) { //해당 거리에서 손님을 찾은 경우
                cNum = map[customer.r][customer.c]; //태운 손님 번호
                map[customer.r][customer.c] = 0; //빈 공간으로 초기화
                taxi.r = customer.r; //택시 위치 변경
                taxi.c = customer.c;
                return;
            }

            amount--; //연료 소모
        }

        amount = -1; //승객을 찾지 못하는 경우는 연료를 -1로 초기화
        return;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //한 행의 크기
        M = Integer.parseInt(st.nextToken()); //승객 수
        amount = Integer.parseInt(st.nextToken()); //초기연료

        map = new int[N][N]; //지도 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()); //운전 시작 위치
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        customers = new HashMap<>();//승객의 출발지와 목적지를 저장할 맵
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int sR = Integer.parseInt(st.nextToken()) - 1;
            int sC = Integer.parseInt(st.nextToken()) - 1;
            int eR = Integer.parseInt(st.nextToken()) - 1;
            int eC = Integer.parseInt(st.nextToken()) - 1;

            map[sR][sC] = i + 1; //손님 번호를 지도에 표시 -> 지도에서 1번은 벽이니까 2번부터 표기
            customers.put(i + 1, new Point(eR, eC)); //손님 번호와 도착지 정보를 저장
        }

        cNum = 0;
    }
}