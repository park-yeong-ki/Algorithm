package bj2812;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[N];
        char[] cArr = sc.next().toCharArray();
        for (int i = 0; i < N; i++) {
            arr[i] = cArr[i] - '0';
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.size() > i - K && stack.peek() < arr[i]) {
                stack.pop();
            }
            if (stack.size() < N - K) stack.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse());
    }
}
