package bj11286;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //연산의 개수 N
        int N = Integer.parseInt(br.readLine());

        //우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //절대값이 같다면 값이 작은 순으로 오름차순
                if (Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                }
                return Math.abs(o1) - Math.abs(o2);
            }
        });

        //연산
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            //배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거하는 경우
            if (num == 0) {
                //비어 있는 경우
                if (pq.isEmpty()) {
                    bw.write(0 + "\n");
                } else {
                    bw.write(pq.poll() + "\n");
                }
            }
            //x라는 값을 넣는(추가하는) 연산
            else {
                pq.offer(num);
            }
        }

        bw.close();
    }
}
