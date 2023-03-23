package bj16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] ladder;
    static int[][] snake;
    static int N;
    static int M;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //사다리 입력
        ladder = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }

        //뱀 입력
        snake = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }

        //bfs 사용
        bfs(1);

        //출력
        System.out.println(count);
    }

    static void bfs(int start) {
        //큐생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문배열 생성
        boolean[] visited = new boolean[101];

        //초기값 큐에 삽입
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있을 때까지 반복
        int current, size;
        while (!queue.isEmpty()) {
            size = queue.size();

            //주사위 굴린 회수를 구하기 위해 큐의 사이즈(노드 레벨)별로 반복
            for (int i = 0; i < size; i++) {
                current = queue.poll();

                //마지막 칸에 도착한 경우 종료
                if (current == 100) {
                    return;
                }

                //사다리나 뱀이 있는지 확인
                current = ladderOrSnake(current);

                //주사위 굴리기
                for (int j = 1; j <= 6; j++) {
                    if (current + j <= 100 && !visited[current + j]) {
                        queue.offer(current + j);
                        visited[current + j] = true;
                    }
                }
            }

            //주사위 굴린 회수 체크
            count++;
        }
    }

    static int ladderOrSnake(int position) {
        //사다리확인
        for (int i = 0; i < N; i++) {
            if (ladder[i][0] == position) {
                return ladder[i][1];
            }
        }

        //뱀확인
        for (int i = 0; i < M; i++) {
            if (snake[i][0] == position) {
                return snake[i][1];
            }
        }

        //아무것도 없을경우 원래값 반환
        return position;
    }
}
