package swea1289;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //테스트 케이스의 수 T 입력
        int T = sc.nextInt();

        //T까지 반복
        for (int i = 1; i <= T; i++) {
            //기존값 입력
            String str1 = sc.next();

            //배열로 변경
            String[] sArr1 = str1.split("");

            //초기값 배열 생성
            String[] sArr2 = new String[sArr1.length];

            //입력받은 메모리 길이만큼 비트 생성
            for (int j = 0; j < sArr2.length; j++) {
                sArr2[j] = "0";
            }

            //카운트 변수 선언
            int count = 0;
            //반복문을 통해서 문자열을 비교한 후 비트를 변경
            for (int j = 0; j < sArr2.length; j++) {
                if (!sArr2[j].equals(sArr1[j])) {
                    for (int j2 = j; j2 < sArr2.length; j2++) {
                        if (sArr2[j2].equals("1")) {
                            sArr2[j2] = "0";
                        } else {
                            sArr2[j2] = "1";
                        }
                    }
                    //비트를 변경한 경우 카운트 체크
                    count++;
                }
            }

            //출력
            System.out.println("#" + i + " " + count);

        }
    }
}
