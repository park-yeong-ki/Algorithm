package swea12368;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		//Scanner 생성
		Scanner sc = new Scanner(System.in);
		
		//테스트 케이스 T입력
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			//A, B 입력
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			//A+B의 합이 24이상일 경우 24를 빼고 출력
			if (A + B >= 24) {
				System.out.println("#" + test_case + " " + (A+B-24));
			}else {
				System.out.println("#" + test_case + " " + (A+B));
			}
		}
		
	}
}
