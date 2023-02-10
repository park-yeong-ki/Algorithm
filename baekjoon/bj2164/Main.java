package bj2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N입력
        int N = sc.nextInt();

        //Queue 생성
        Queue queue = new LinkedList();

        //1부터 N까지 수를 순서대로 큐에 삽입
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        //마지막 남게 되는 카드를 구하면 되므로 1개 남으면 반복문 종료
        while (queue.size() > 1) {
            //큐에서 요소 한개 제거
            queue.poll();
            //큐에서 요소를 제거 후 다시 삽입
            queue.offer(queue.poll());
        }

        //출력
        System.out.println(queue.peek());
    }
}
