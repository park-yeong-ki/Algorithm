package bj1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //식 입력
        String str = br.readLine();

        //-연산자 뒤부터 여는 괄호 시작 후 다음 -연산자를 만나면 괄호를 닫는다
        //다음 -연산자를 만나지 못하면 식의 마지막 인덱스에서 괄호를 닫는다.

        //-연산자 인덱스를 저장
        ArrayList<Integer> list = new ArrayList<>();
        //첫 인덱스 저장
        list.add(0);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                list.add(i);
            }
        }
        //문자열 길이 저장
        list.add(str.length());

        //문자열 나누기
        String[] sArr = new String[list.size()-1];
        for (int i = 0; i < list.size()-1; i++) {
            sArr[i] = str.substring(list.get(i), list.get(i+1));
        }

        //나눠진 문자열을 각각 연산하여 결과값을 구한다.
        StringBuilder sb = new StringBuilder();
        int result = 0;
        for (int i = 0; i < sArr.length; i++) {
            int sum = 0;
            for (int j = 0; j < sArr[i].length(); j++) {
                //+연산자를 만나기 전까지 숫자를 이어붙인다.
                if (sArr[i].charAt(j) != '+') {
                    if (sArr[i].charAt(j) != '-') {
                        sb.append(sArr[i].charAt(j));
                    }
                }
                //+연산자를 만날 경우
                else {
                    //붙여온 문자를 숫자로 변환후 합을 구한다.
                    sum += Integer.parseInt(sb.toString());
                    //스트링빌더 초기화
                    sb.setLength(0);
                }
            }
            //마지막 숫자를 더한다
            sum += Integer.parseInt(sb.toString());
            //스트링빌더 초기화
            sb.setLength(0);

            //첫번째 문자열의 숫자합은 결과값에 더해준다
            if (i == 0) {
                result = sum;
            }
            //나머지 문자열의 숫자합은 결과값에 빼준다.
            else {
                result -= sum;
            }

        }

        //출력
        System.out.println(result);
    }
}
