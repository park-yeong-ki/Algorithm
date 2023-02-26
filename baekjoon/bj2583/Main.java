package bj2583;

import java.io.*;
import java.util.*;

public class Main {
    //전역변수 설정
    static int[][] paper;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int M;
    static int N;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //M, N, K입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //모눈종이 생성
        paper = new int[M][N];

        //직사각형 입력
        int[][] rectangle = new int[K][4];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                rectangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //모눈종이에 직사각형 그리기
        for (int i = 0; i < K; i++) {
            for (int j = paper.length - rectangle[i][3]; j < paper.length - rectangle[i][1]; j++) {
                for (int k = rectangle[i][0]; k < rectangle[i][2]; k++) {
                    paper[j][k] = 1;
                }
            }
        }

        //dfs사용
        //영역의 넓이를 저장할 리스트
        List<Integer> result = new ArrayList<>();
        //방문 배열 생성
        boolean[][] visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (paper[i][j] == 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    result.add(cnt);
                    cnt = 0;
                }
            }
        }

        //영역의 넓이 오름차순 정렬
        Collections.sort(result);

        //출력
        bw.write(result.size() + "\n");
        for (int i = 0; i < result.size(); i++) {
            bw.write(result.get(i) + " ");
        }
        bw.newLine();
        bw.close();
    }

    static void dfs(int x, int y, boolean[][] visited) {
        //방문 체크
        visited[x][y] = true;
        cnt++;

        //깊이우선탐색
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] >= 0 && x + dx[i] < M && y + dy[i] >= 0 && y + dy[i] < N) {
                if (paper[x + dx[i]][y + dy[i]] == 0 && !visited[x + dx[i]][y + dy[i]]) {
                    dfs(x + dx[i], y + dy[i], visited);
                }
            }
        }
    }
}
