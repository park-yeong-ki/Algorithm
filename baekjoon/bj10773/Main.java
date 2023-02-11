package bj10773;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Integer> stack = new Stack<>();

        //정수K 입력
        int K = Integer.parseInt(br.readLine());

        //0이 입력되면 최근에 저장된 값 삭제, 그 이외에는 값 저장
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                stack.pop();
            } else {
                stack.push(num);
            }
        }

        int sum = 0;
        //pop을 사용하면 size가 계속 변경되기 때문에 초기 size를 변수로 저장한다.
        int size = stack.size();
        //최근 저장된 값을 하나씩 제거하면서 합을 구한다.
        for (int i = 0; i < size; i++) {
            sum += stack.pop();
        }
        //출력
        bw.write(sum + "\n");
        bw.close();
    }
}
