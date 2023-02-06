package bj25501;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Scanner 생성
		Scanner sc = new Scanner(System.in);
		
		//T 입력
		int T = sc.nextInt();
		
		//T만큼 반복
		for (int test_case = 0; test_case < T; test_case++) {
			//문자열 입력
			String str = sc.next();
					
			//결과 출력
			System.out.println(recursion(str, 0, 1));
		}
	}
	
	//재귀함수 생성
	public static String recursion(String str, int num, int result) {
		//결과값이 0이거나 재귀호출횟수가 문자열 길이의 절반보다 많은 경우 종료
		if (result == 0 || num > str.length()/2) {
			return result + " " + num;
		}
		
		//문자열 비교
		if (str.charAt(num) != str.charAt(str.length()-1-num)) {
			result = 0;
		}
		//재귀함수 호출
		return recursion(str, num+1, result);
	}
}
