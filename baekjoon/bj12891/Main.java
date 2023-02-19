package bj12891;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        //문자열 길이 S와 부분문자열 길이 P입력
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        //문자열 입력
        String str = br.readLine();

        //A, C, G, T의 최소개수 입력
        char[] DNA = {'A', 'C', 'G', 'T'};
        int[] count = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        //알파벳 카운트 배열 생성 -> 아스키코드 이용
        int[] aCount = new int[26];
        //비밀번호로 설정할 수 있는 개수에 대한 변수
        int password = 0;

        int i = 0;
        while (true) {
            //부분문자열이 완성된 경우
            if (i >= P) {
                //DNA 최소 개수를 만족하는지 검사
                boolean isPassword = true;
                for (int j = 0; j < DNA.length; j++) {
                    if (aCount[DNA[j] - 65] < count[j]){
                        isPassword = false;
                        break;
                    }
                }

                //검사를 통과한 경우 비밀번호 개수 추가
                if (isPassword) password++;

                //i가 입력받은 문자열 길이와 같은 경우 반복문 종료
                if (i == str.length()) break;

                //부분문자열 크기만큼의 알파벳 개수를 구하기 위한 조건 설정
                aCount[str.charAt(i - P) - 65]--;
            }

            //알파벳 개수를 센다
            aCount[str.charAt(i) - 65]++;

            i++;
        }

        //출력
        bw.write(password + "\n");
        bw.close();
    }
}
