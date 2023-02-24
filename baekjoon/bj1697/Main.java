package bj1697;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N, K입력
        int N = sc.nextInt();
        K = sc.nextInt();

        //bfs
        bfs(N);
    }

    static void bfs(int start) {
        //큐 생성
        Queue<Integer> queue = new ArrayDeque();

        //방문 배열 생성
        boolean[] visited = new boolean[100001];

        //처음 입력된 값을 큐에 넣고 방문 체크한다
        queue.offer(start);
        visited[start] = true;

        //큐가 비어있을 때까지 반복
        int current, size, count = 0;
        int[] abj;
        while (!queue.isEmpty()) {
            size = queue.size();

            //그래프의 각 레벨 사이즈만큼 반복한다
            for (int i = 0; i < size; i++) {
                current = queue.poll();

                //동생을 찾았다면 그래프의 레벨을 출력하고 종료
                if (current == K) {
                    System.out.println(count);
                    return;
                }

                //현재 위치에 따른 연결된 정점을 조건에 따라 설정한다.
                if (current == 0) abj = new int[]{current + 1};
                else if (current >= 100000) abj = new int[]{current - 1};
                else if(current > 50000) abj = new int[]{current - 1, current + 1};
                else abj = new int[]{current - 1, current + 1, current * 2};

                //연결된 정점을 탐색한다
                for (int j = 0; j < abj.length; j++) {
                    //방문되지 않은 경우 큐에 넣는다
                    if (!visited[abj[j]]) {
                        queue.offer(abj[j]);
                        visited[abj[j]] = true;
                    }
                }
            }

            //그래프 레벨을 알 수 있도록 1씩 증가한다.
            count++;
        }
    }
}
