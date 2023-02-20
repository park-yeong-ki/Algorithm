package bj14503;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int[][] room;
    static int N;
    static int M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int count = 0;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N, M입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //r, c, d입력
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        //방 상태 입력
        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //재귀함수 사용
        clean(r, c, d);

        //출력
        bw.write(count + "\n");
        bw.close();
    }

    //재귀함수 생성
    static void clean(int r, int c ,int d) throws IOException {
        //현재 칸을 청소
        if (room[r][c] == 0) {
            room[r][c] = -1;
            count++;
        }

        //4칸 중 청소되지 않은 칸이 있는지 검사
        boolean flag = false;
        for (int i = 0; i < dx.length; i++) {
            //현재 방향을 반시계 방향으로 90도 회전
            if (d == 0) d=3;
            else d--;

            //검사
            if (room[r + dx[d]][c + dy[d]] == 0) {
                flag = true;
                break;
            }
        }

        //청소되지 않은 빈 칸이 있는 경우 -> 방향대로 전진
        if (flag) {
            clean(r + dx[d], c + dy[d], d);
        }
        //청소되지 않은 빈 칸이 없는 경우
        else {
            //한 칸 후진할 수 있다면 한 칸 후진
            if (room[r - dx[d]][c - dy[d]] != 1) clean(r - dx[d], c - dy[d], d);
            //후진을 못하는 경우 종료
            else return;
        }
    }
}
