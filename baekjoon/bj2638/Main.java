package bj2638;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] paper;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        //흰색 바깥 공기영역을 완전탐색하여 외곽 치즈와 맞닿으면 +1해준다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //모눈 종이 입력
        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dfs 구현
        int time = 0;
        while (true) {
            dfs(new Point(0, 0), new boolean[N][M]);
            //제거할 치즈가 없는 경우 반복문 종료
            if (removeCheese() == 0) break;
            time++;
        }

        //출력
        bw.write(time + "\n");
        bw.close();
    }

    //dfs구현
    static void dfs(Point start, boolean[][] visited) {
        int r = start.r;
        int c = start.c;

        //방문 배열 체크
        visited[r][c] = true;

        //4방향 탐색
        for (int i = 0; i < 4; i++) {
            if (r + dr[i] >= 0 && r + dr[i] < N && c + dc[i] >= 0 && c + dc[i] < M) {
                if (!visited[r + dr[i]][c + dc[i]]) {
                    //공기인 경우 계속 탐색
                    if (paper[r + dr[i]][c + dc[i]] == 0) {
                        dfs(new Point(r + dr[i], c + dc[i]), visited);
                    }
                    //치즈인 경우 해당 배열 요소값 1증가
                    else {
                        paper[r + dr[i]][c + dc[i]]++;
                    }
                }
            }
        }
    }

    //2변 이상이 실내온도의 공기와 접촉한 치즈 삭제
    static int removeCheese() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //2변 이상 공기와 접촉한 치즈는 삭제
                if (paper[i][j] >= 3) {
                    paper[i][j] = 0;
                    count++;
                }
                //1변만 공기와 접촉한 치즈는 원상복귀
                else if (paper[i][j] == 2) {
                    paper[i][j]--;
                }
            }
        }
        //제거한 치즈의 수를 반환한다.
        return count;
    }
}
