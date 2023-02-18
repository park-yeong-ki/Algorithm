package bj10866;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //N입력
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<Integer>();

        //명렁의 수만큼 반복
        for (int i = 0; i < N; i++) {
            //push의 경우 공백 뒤에 숫자가 있으므로 문자열 배열 사용
            String[] str = br.readLine().split(" ");

            //명령어에 따라 조건에 맞는 메서드 사용하도록 구현
            switch (str[0]) {
                case "push_front":
                    deque.offerFirst(Integer.parseInt(str[1]));
                    break;
                case "push_back":
                    deque.offerLast(Integer.parseInt(str[1]));
                    break;
                case "pop_front":
                    if (deque.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(deque.pollFirst() + "\n");
                    }
                    break;
                case "pop_back":
                    if (deque.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(deque.pollLast() + "\n");
                    }
                    break;
                case "size":
                    bw.write(deque.size() + "\n");
                    break;
                case "empty":
                    if (deque.isEmpty()) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case "front":
                    if (deque.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(deque.getFirst() + "\n");
                    }
                    break;
                case "back":
                    if (deque.isEmpty()) {
                        bw.write(-1 + "\n");
                    } else {
                        bw.write(deque.getLast() + "\n");
                    }
                    break;
                default:
                    break;
            }
        }

        bw.close();
    }
}
