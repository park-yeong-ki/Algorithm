package bj1436;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //N입력
        int N = sc.nextInt();

        //카운트 변수를 통해서 N번째로 작은 종말의 숫자를 구한다.
        int count = 1;
        //(N-1)666가 초기 N번째 종말의 숫자였으므로 반복문의 범위를 (N-1)666까지로 설정한다.
        for (int i = 666; i <= Integer.parseInt((N-1) + "666"); i++) {
            //666을 포함하는 경우 종말의 숫자이다.
            if (String.valueOf(i).contains("666")) {
                //카운트 변수와 N이 같은 경우 반복문 종료 후 출력
                if (count == N) {
                    System.out.println(i);
                    break;
                }
                count++;
            }
        }

    }
}
