package bj11866;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //N, K입력
        int N = sc.nextInt();
        int K = sc.nextInt();

        //요세푸스 순열을 저장할 리스트
        List<Integer> list = new ArrayList<>();

        Queue<Integer> queue = new LinkedList();

        //1부터 N까지 값을 큐에 입력
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        //큐가 전부 비워질때 까지 반복
        while (!queue.isEmpty()) {
            //K-1까지는 큐를 제거 후 다시 삽입한다.
            for (int i = 0; i < K-1; i++) {
                queue.offer(queue.poll());
            }
            //제일 앞에 있는 큐를 제거 후 리스트에 저장  -> K번째 요소가 제거 되는 것과 동일
            list.add(queue.poll());
        }

        //출력
        System.out.println(list.toString().replace("[", "<").replace("]", ">"));

    }
}
