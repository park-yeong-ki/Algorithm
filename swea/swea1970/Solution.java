package swea1970;

import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {
		// Scanner 선언
		Scanner sc = new Scanner(System.in);
		int T;
		// T입력
		T = sc.nextInt();

		// 1부터 T까지 반복
		for (int test_case = 1; test_case <= T; test_case++) {

			// 입력
			int N = sc.nextInt();

			// 돈의 종류로 배열 생성
			int[] money = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };

			// 카운트 배열 생성
			int[] count = new int[money.length];

			// 돈의 종류만큼 반복하며 카운트 배열 입력
			for (int i = 0; i < money.length; i++) {
				while (N >= money[i]) {
					N -= money[i];
					count[i]++;
				}
			}

			// 출력
			System.out.println("#" + test_case);
			for (int i = 0; i < count.length; i++) {
				if (i != count.length - 1)
					System.out.print(count[i] + " ");
				else
					System.out.println(count[i]);

			}

		}
	}
}