import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, Q, len, sum, max, cLen;
    static int[][] map, copyMap;
    static int[] lArr, tArr;
    static boolean[][] visited;
    static class Point{
        int r, c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < lArr.length; i++) {
            cLen = (int) Math.pow(2, lArr[i]); //부분 격자의 크기
            copyMap = new int[len][len]; //회전한 값을 복사할 배열 생성
            tArr = new int[cLen]; //회전할때 행단위로 임시저장할 배열
            divide(0,0, len); //분할한 후 회전
            map = copyMap; //원배열로 복사
            reduce(); //조건에 따라 얼음 제거
        }

        visited = new boolean[len][len]; //bfs에 사용할 방문배열
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (visited[i][j]) continue; //이미 방문한 경우
                if (map[i][j] == 0) continue; //얼음이 없는 경우
                bfs(new Point(i, j));
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start.r][start.c] = true;

        int cnt = 0;
        Point current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            sum += map[current.r][current.c]; //얼음의 합
            cnt++; //얼음 덩어리의 크기

            for (int i = 0; i < 4; i++) {
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];

                if (tR < 0 || tR >= len || tC < 0 || tC >= len) continue; //경계초과하는 경우
                if (visited[tR][tC]) continue; //방문했던 경우
                if (map[tR][tC] == 0) continue; //얼음이 아닌 경우

                queue.add(new Point(tR, tC));
                visited[tR][tC] = true;
            }
        }

        max = Math.max(max, cnt); //최대 얼음 덩어리 크기 갱신
    }

    static void divide(int r, int c, int range) {
        if (range == cLen) { //주어진 l만큼의 크기로 분할한 경우
            rotate(r, c, range); //회전
            return;
        }

        divide(r, c, range / 2); //1사분면
        divide(r, c + range / 2, range / 2);//2사분면
        divide(r + range / 2, c, range / 2);//3사분면
        divide(r + range / 2, c + range / 2, range / 2);//4사분면
    }

    static void rotate(int r, int c, int range) {
        for (int i = r; i < r + range; i++) {
            for (int j = c; j < c + range; j++) {
                tArr[j - c] = map[i][j]; //행 저장
            }

            for (int j = 0; j < range; j++) {
                copyMap[r + j][c + range - 1 - (i - r)] = tArr[j]; //복사한 행을 열에 붙여넣기
            }
        }
    }

    static void reduce() {
        visited = new boolean[len][len]; //얼음의 양을 줄일 칸을 표시할 배열

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[i][j] == 0) continue; //이미 얼음이 아닌 경우는 통과
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int tR = i + dr[k];
                    int tC = j + dc[k];

                    if (tR < 0 || tR >= len || tC < 0 || tC >= len) continue; //경계초과하는 경우
                    if (map[tR][tC] == 0) continue; //얼음이 아닌 경우

                    cnt++;
                }
                if (cnt < 3) visited[i][j] = true; //상하좌우에 3개 미만의 얼음인 경우 표시
            }
        }

        //얼음 제거
        for (int i=0; i<len; i++){
            for (int j = 0; j < len; j++) {
                if (visited[i][j]) map[i][j]--;
            }
        }

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        len = (int) Math.pow(2, N);

        map = new int[len][len];
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        lArr = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            lArr[i] = Integer.parseInt(st.nextToken());
        }

        sum = 0;
        max = 0;
    }
}