package swea1926;

import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {
		// Scanner선언
		Scanner sc = new Scanner(System.in);

		// N입력
		int N = sc.nextInt();
		// 배열 생성
		String[] arr = new String[N];

		// 배열 요소 입력
		for (int i = 0; i < N; i++) {
			arr[i] = String.valueOf(i + 1);
		}

		// 3,6,9 배열
		String[] arr369 = { "3", "6", "9" };

		// 배열 각 자리 수와 3,6,9 배열과 비교하여 횟수 체크
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < arr[i].length(); k++) {
					if (arr[i].charAt(k) == arr369[j].charAt(0)) {
						count++;
					}
				}
			}
			// 체크한 횟수에 따라서 결과값 출력
			if (count == 0) {
				System.out.print(arr[i] + " ");
			} else if (count == 1) {
				System.out.print("- ");
			} else {
				for (int j = 0; j < count; j++) {
					System.out.print("-");
				}
				System.out.print(" ");
			}
		}
		System.out.println();
	}
}