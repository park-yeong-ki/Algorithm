package bj2841;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        Stack<Integer>[] stack = new Stack[7];
        for (int i = 1; i <= 6; i++) {
            stack[i] = new Stack<>();
        }

        int rNum, pNum, cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            rNum = Integer.parseInt(st.nextToken());
            pNum = Integer.parseInt(st.nextToken());

            while (!stack[rNum].isEmpty() && stack[rNum].peek() > pNum) {
                stack[rNum].pop();
                cnt++;
            }
            if (stack[rNum].isEmpty() || stack[rNum].peek() < pNum) {
                stack[rNum].push(pNum);
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
