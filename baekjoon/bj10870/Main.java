package bj10870;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Scanner 생성
		Scanner sc = new Scanner(System.in);
		
		//N 입력
		int N = sc.nextInt();
		
		//출력
		System.out.println(recursive(N));
	}
	
	//재귀함수 생성
	public static int recursive(int N) {
		//N이 1보다 작을경우 N 반환 
		if (N <= 1) {
			return N;
		}
		//피보나치 공식 사용
		return recursive(N-1) + recursive(N-2);
	}
}
