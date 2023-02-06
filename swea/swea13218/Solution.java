package swea13218;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		//Scanner 선언
		Scanner sc = new Scanner(System.in);
		
		//T입력
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			//학생 수 입력
			int N = sc.nextInt();
			
			//3으로 나눈 결과 출력
			System.out.println("#" + test_case + " " + N/3);
			
		}
	}
}
