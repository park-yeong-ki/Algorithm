package bj5014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int F;
    static int U;
    static int D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //F, S, G, U, D 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        //bfs 사용
        bfs(S, G);

    }

    static void bfs(int start, int end) {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque<>();

        //방문 배열 생성
        boolean[] visited = new boolean[F + 1];

        //처음 값을 큐에 삽입 후 방문 배열에 체크
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있지 않을 때까지 반복
        int current, size, count = 0;
        int[] V;
        while (!queue.isEmpty()) {
            size = queue.size();

            //각 레벨별로 반복문 수행
            for (int i = 0; i < size; i++) {
                //가장 첫번째 저장된 값을 제거
                current = queue.poll();

                //도착지점과 같다면 버튼의 수 출력 후 종료
                if (current == end) {
                    System.out.println(count);
                    return;
                }

                //각 경우의 수를 설정하여 ArrayIndexOutOfBoundsException 방지
                if (current - D < 1 && current + U > F) V = new int[0];
                else if (current + U > F) V = new int[]{current - D};
                else if (current - D < 1) V = new int[]{current + U};
                else V = new int[]{current + U, current - D};

                //연결된 정점을 탐색
                for (int j = 0; j < V.length; j++) {
                    //방문하지 않은 경우 큐에 저장후 방문체크
                    if (!visited[V[j]]) {
                        queue.offer(V[j]);
                        visited[V[j]] = true;
                    }
                }
            }

            //버튼 누른 수 체크하기
            count++;
        }
        //엘리베이터로 이동할 수 없는 경우 -> 도착지점에 도달할 수 없는 경우
        System.out.println("use the stairs");
    }
}
