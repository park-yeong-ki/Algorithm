package bj1927;

import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //연산의 개수 N
        int N = Integer.parseInt(br.readLine());

        //우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        //연산
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            //배열에서 가장 작은 값을 출력하고 그 값을 배열에서 제거
            if (num == 0) {
                //비어 있는 경우인데 가장 작은 값을 출력하라고 한 경우에는 0을 출력
                if (pq.isEmpty()) {
                    bw.write(0 + "\n");
                } else {
                    bw.write(pq.poll() + "\n");
                }
            }
            // 배열에 x라는 값을 넣는(추가하는) 연산
            else {
                pq.offer(num);
            }
        }

        bw.close();
    }
}
