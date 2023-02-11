package bj1966;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트케이스의 수 입력
        int T = Integer.parseInt(br.readLine());

        //테스트케이스의 수만큼 반복
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //문서의 개수 N, 몇 번째로 인쇄되었는지 궁금한 문서의 현재 위치 M
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            //중요도 큐와 초기 위치에 대한 큐를 생성
            Queue<Integer> queue = new ArrayDeque();
            Queue<Integer> idx = new ArrayDeque<>();

            //큐에 요소 삽입
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
                idx.offer(i);
            }

            //출력된 순서를 체크하기위한 변수 생성
            int count = 1;

            outer:
            while (!queue.isEmpty()) {
                //큐의 중요도 최대값을 구한다
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < queue.size(); i++) {
                    max = Math.max(max, queue.peek());
                    queue.offer(queue.poll());
                }

                for (int i = 0; i < queue.size(); i++) {
                    //최대값보다 작은 경우는 가장 뒤에 재배치
                    if (max > queue.peek()) {
                        queue.offer(queue.poll());
                        idx.offer(idx.poll());
                    }
                    //최대값과 같은 경우는 인쇄후 반복문 종료
                    else {
                        queue.poll();
                        //입력받은 M과 idx의 top이 같다면 결과 출력
                        if (M == idx.poll()) {
                            bw.write(count + "\n");
                            break outer;
                        }
                        count++;
                        break;
                    }
                }
            }
        }
        bw.close();
    }
}
