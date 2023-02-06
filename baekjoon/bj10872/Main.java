package bj10872;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Scanner 생성
		Scanner sc = new Scanner(System.in);
		
		//N입력
		int N = sc.nextInt();
		
		//결과 출력
		System.out.println(recursive(N));
	}
	
	//재귀함수 생성
	public static int recursive(int N) {
		//N=0이면 1반환
		if (N == 0) {
			return 1;
		}
		//1씩 감소시키며 재귀호출
		return N * recursive(N-1);
	}
}
