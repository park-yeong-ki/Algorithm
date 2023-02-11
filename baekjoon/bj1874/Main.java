package bj1874;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        //N입력
        int N = sc.nextInt();

        //입력될 수열을 저장할 배열
        int[] arr = new int[N+1];

        //배열 요소 입력
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        int num = 1;
        //수열 길이만큼 반복을 해야 스택을 통해 입력된 수열을 만들 수 있다.
        outer:
        for (int i = 1; i < N+1; i++) {
            while (true){
                //오름차순으로 증가되는 수가 수열의 i번째 원소보다 작거나 같을 경우는 스택에 push를 한다
                if (num <= arr[i]) {
                    stack.push(num++);
                    sb.append("+\n");
                }
                //오름차순으로 증가되는 수가 i번째 원소보다 클 경우는 더이상 push하지 않고 pop을 한다.
                else {
                    //스택의 top에 있는 자료를 꺼냈을때 수열의 i번째 원소와 같다면 만들 수 있는 수열이다.
                    if (stack.pop() == arr[i]) {
                        sb.append("-\n");
                        break;
                    }
                    //스택의 top에 있는 자료를 꺼냈을때 수열의 i번째 원소와 같지않다면 만들 수 없는 수열이다.
                    else {
                        //StringBuilder에 저장된 값을 초기화 하고 NO를 입력한다.
                        sb.setLength(0);
                        sb.append("NO");
                        break outer;
                    }
                }
            }
        }

        //출력
        System.out.println(sb);
    }
}
