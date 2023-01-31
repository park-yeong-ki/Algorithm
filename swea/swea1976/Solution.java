package swea1976;

import java.util.Scanner;
import java.io.FileInputStream;


class Solution{
	public static void main(String args[]) throws Exception{
		
		//Scanner 선언
		Scanner sc = new Scanner(System.in);
		int T;
		//T입력
		T=sc.nextInt();
		
		//1부터 T까지 반복문
		for(int test_case = 1; test_case <= T; test_case++){
		
            //배열 생성 및 입력
            int[] arr = new int[4];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }

            //60분과 12시를 기준으로 나눠서 풀이
            int hour = arr[0] + arr[2];
            int minute = arr[1] + arr[3];

            if (minute > 60) {
                hour++;
                minute -= 60;
            }

            if (hour > 12) {
                hour -= 12;
            }

            //출력
            System.out.println("#" + test_case + " " + hour + " " + minute);

		}
	}
}