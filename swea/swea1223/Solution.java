package swea1223;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 10개
        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            char[] cArr = br.readLine().toCharArray();

            HashMap<Character, Integer> priority = new HashMap<>();
            priority.put('*', 2);
            priority.put('+', 1);

            //후위 표기식 변환
            Stack<Character> stack1 = new Stack<>();
            int size = 0;
            for (int i = 0; i < n; i++) {
                //피연산자일 경우
                if (cArr[i] != '+' && cArr[i] != '*') {
                    cArr[size++] = cArr[i];
                }
                //연산자일 경우
                else {
                    //스택이 비어있거나 연산자의 우선순위가 스택의 top보다 높을 경우
                    if (stack1.isEmpty() || priority.get(cArr[i]) > priority.get(stack1.peek())) {
                        stack1.push(cArr[i]);
                    }
                    //연산자의 우선순위가 스택의 top보다 낮을경우
                    else {
                        while (!stack1.isEmpty()) {
                            if (priority.get(cArr[i]) > priority.get(stack1.peek())) {
                                break;
                            }
                            cArr[size++] = stack1.pop();
                        }
                        stack1.push(cArr[i]);
                    }
                }
            }
            while (!stack1.isEmpty()) {
                cArr[size++] = stack1.pop();
            }

            //후위 표기식 계산
            Stack<Integer> stack2 = new Stack<>();
            for (int i = 0; i < n; i++) {
                //피연산자일 경우
                if (cArr[i] != '+' && cArr[i] != '*') {
                    stack2.push(cArr[i] - '0');
                }
                //연산자일 경우
                else {
                    if (cArr[i] == '+') {
                        stack2.push(stack2.pop() + stack2.pop());
                    } else if (cArr[i] == '*') {
                        stack2.push(stack2.pop() * stack2.pop());
                    }
                }
            }

            //출력
            bw.write("#" + test_case + " " + stack2.pop() + "\n");
        }
        bw.close();
    }
}
