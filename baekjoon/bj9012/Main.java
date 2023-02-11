package bj9012;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //입력데이터의 수 T입력
        int T = Integer.parseInt(br.readLine());

        //입력데이터의 수만큼 반복
        for (int i = 0; i < T; i++) {
            Stack<Character> stack = new Stack<>();

            //결과의 초기값은 올바른 괄호 문자열이라 가정
            String result = "YES";

            //문자열 입력
            String str = br.readLine();

            //문자열 길이만큼 반복
            for (int j = 0; j < str.length(); j++) {
                //여는 괄호일 경우
                if (str.charAt(j) == '(') {
                    stack.push(str.charAt(j));
                }
                //닫는 괄호일 경우
                else {
                    //닫는 괄호를 만났는데 스택이 비워있는 경우나 짝이 맞지않는 경우
                    if (stack.size() == 0 || stack.pop() != '(') {
                        result = "NO";
                        break;
                    }
                }
            }

            //반복문을 모두 돌고 stack의 크기가 0이 아니라면 짝이 맞지 않은 경우
            if (result.equals("YES") && stack.size() != 0) {
                result = "NO";
            }

            //출력
            bw.write(result + "\n");
        }

        bw.close();
    }
}
