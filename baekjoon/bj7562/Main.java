package bj7562;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int l;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            //체스판 한 변의 길이 입력
            l = Integer.parseInt(br.readLine());
            //체스판 생성
            int[][] chess = new int[l][l];

            //출발점 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] start = new int[2];
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());

            //도착점 입력
            st = new StringTokenizer(br.readLine());
            int[] end = new int[2];
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            //bfs사용
            bfs(start, end);
        }
        bw.close();
    }

    static void bfs(int[] start, int[] end) throws IOException {
        //큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        //방문 배열 생성
        boolean[][] visited = new boolean[l][l];

        //큐 입력
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        //큐가 비어있을 때까지 반복
        int[] current;
        int size, r, c, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            //레벨을 구별할 수 있도록 반복문 설정
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                r = current[0];
                c = current[1];

                //도착점과 일치하면 레벨 수를 출력하고 종료
                if (r == end[0] && c == end[1]) {
                    bw.write(cnt + "\n");
                    return;
                }

                //방문하지 않은 정점이면 큐에 넣고 방문 체크한다
                for (int j = 0; j < 8; j++) {
                    if (r + dx[j] >= 0 && r + dx[j] < l && c + dy[j] >= 0 && c + dy[j] < l) {
                        if (!visited[r+dx[j]][c+dy[j]]) {
                            queue.offer(new int[]{r+dx[j], c+dy[j]});
                            visited[r+dx[j]][c+dy[j]] = true;
                        }
                    }
                }
            }

            //레벨을 센다.
            cnt++;
        }
    }
}
