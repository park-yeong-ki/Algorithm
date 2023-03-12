package swea1224;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트케이스 10까지 반복
        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());

            char[] cArr = br.readLine().toCharArray();
            List<Character> list = new ArrayList<>();

            //연산자에 대한 우선순위 입력
            Map<Character, Integer> priority = new HashMap<>();
            priority.put('+', 1);
            priority.put('*', 2);
            priority.put('(', 0);

            //후위표기식 변환
            int size = 0;
            Stack<Character> stack1 = new Stack<>();
            for (int i = 0; i < n; i++) {
                //피연산자일 경우
                if ((int) cArr[i] >= 48 && (int) cArr[i] <= 57) {
                    list.add(cArr[i]);
                }
                //여는 괄호인 경우
                else if (cArr[i] == '(') {
                    stack1.push(cArr[i]);
                }
                //닫는 괄호인 경우
                else if (cArr[i] == ')') {
                    while (stack1.peek() != '(') {
                        list.add(stack1.pop());
                    }
                    stack1.pop();
                }
                //연산자인 경우
                else {
                    //스택이 비워져있거나 우선순위가 클 경우
                    if (stack1.isEmpty() || priority.get(cArr[i]) > priority.get(stack1.peek())) {
                        stack1.push(cArr[i]);
                    }
                    //우선순위가 같거나 작을 경우
                    else {
                        while (!stack1.isEmpty()) {
                            if (priority.get(cArr[i]) > priority.get(stack1.peek())) {
                                break;
                            }
                            list.add(stack1.pop());
                        }
                        stack1.push(cArr[i]);
                    }
                }
            }
            //남아있는 연산자 저장
            while (!stack1.isEmpty()) {
                list.add(stack1.pop());
            }

            //후위표기식 계산
            Stack<Integer> stack2 = new Stack<>();
            for (int i = 0; i < list.size(); i++) {
                //피연산자일 경우 스택에 저장
                if (list.get(i) >= 48 && list.get(i) <= 57) {
                    stack2.push(list.get(i) - '0');
                }
                //연산자일 경우
                else {
                    if (list.get(i) == '+') {
                        stack2.push(stack2.pop() + stack2.pop());
                    } else {
                        stack2.push(stack2.pop() * stack2.pop());
                    }
                }
            }

            bw.write("#" + test_case + " " + stack2.pop() + "\n");
        }
        bw.close();
    }
}
