package bj4949;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //닫는 괄호와 여는 괄호 배열을 같은 인덱스 맞게 생성한다
        char[] open = {'(', '['};
        char[] close = {')', ']'};

        //무한반복
        while (true) {
            Stack<Character> stack = new Stack<>();
            String result = "yes";

            String str = br.readLine();

            //마지막 문자열일 경우 반복문 종료
            if (str.equals(".")) {
                break;
            }

            //문자열 공백 제거
            str = str.replace(" ", "");

            //문자열 길이만큼 반복
            outer:
            for (int i = 0; i < str.length(); i++) {
                for (int j = 0; j < 2; j++) {
                    //문자열이 여는 괄호일 경우
                    if (str.charAt(i) == open[j]) {
                        stack.push(str.charAt(i));
                    }
                    //문자열이 닫는 괄호일 경우
                    else if (str.charAt(i) == close[j]) {
                        //스택의 사이즈가 0이거나 짝이 맞지 않는 경우는 결과값을 no로 설정
                        if (stack.size() == 0 || stack.pop() != open[j]) {
                            result = "no";
                            break outer;
                        }
                    }
                }
            }

            //문자열 길이만큼 반복문을 돌은 결과값이 yes인 경우 스택의 사이즈가 0이아니라면 짝이 맞지 않은 것
            if (result.equals("yes") && stack.size() != 0) {
                result = "no";
            }

            //출력
            bw.write(result + "\n");
        }
        bw.close();
    }
}
