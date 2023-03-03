package bj3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //갈 수 있는 방향을 설정
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static char[][] map;
    static int R;
    static int C;
    static boolean[][] visited;
    static int count;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //R, C 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //빵집 근처 모습 입력
        map = new char[R][C];
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //dfs사용
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
            flag = false;
        }

        //출력
        System.out.println(count);
    }

    static void dfs(int x, int y) {
        //방문 체크
        visited[x][y] = true;

        //끝에 도달한 경우
        if (y == C-1) {
            count++;
            flag = true;
            return;
        }

        //3방향에 대해서 탐색
        for (int i = 0; i < 3; i++) {
            //ArrayIndexOutOfBoundsException방지
            if (x+dx[i] >= 0 && x+dx[i]<R && y+dy[i] >= 0 && y+dy[i] < C) {
                //방문하지 않은 빈 공간일 경우 dfs 실행
                if (!visited[x+dx[i]][y+dy[i]] && map[x+dx[i]][y+dy[i]] == '.') {
                    //도달한 후에 남은 반복문을 실행하지 않기 위해 flag조건 설정
                    if(!flag) dfs(x+dx[i], y+dy[i]);
                }
            }
        }
    }
}