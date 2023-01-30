package bj1259;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //무한 루프
        while (true) {
            int N  = sc.nextInt();

            //입력의 마지막 줄은 0
            if (N == 0) {
                break;
            }

            //문자열로 변환
            String str = String.valueOf(N);

            //만약 대칭되는 위치의 문자열이 일치하지 않는다면 반복문 종료
            String result = "yes";
            for (int i = 0; i < str.length()/2; i++) {
                if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                    result = "no";
                    break;
                }
            }

            //출력
            System.out.println(result);
        }
    }
}
