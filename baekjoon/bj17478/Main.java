package bj17478;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Scanner 생성
		Scanner sc = new Scanner(System.in);
		
		//N입력
		int N = sc.nextInt();
		//앞부분 문자열을 저장할 StringBuilder
		StringBuilder str1 = new StringBuilder();
		//뒷부분 문자열을 저장할 StringBuilder
		StringBuilder str2 = new StringBuilder();
		//언더바를 저장할 StringBuilder
		StringBuilder str3 = new StringBuilder();
		str1.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");

		//출력
		System.out.println(recursion(N, str1, str2, str3));
	}

	//재귀함수 생성
	public static String recursion(int N, StringBuilder str1, StringBuilder str2, StringBuilder str3) {
		//N은 0일때 재귀를 종료하고 문자열이 합쳐지도록 설정
		if (N == 0) {
			str1.append(str3)
			.append("\"재귀함수가 뭔가요?\"\n")
			.append(str3)
			.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n")
			.append(str3)
			.append("라고 답변하였지.\n")
			.append(str2);

			//문자열 반환
			return str1.toString();
		}

		//재귀 종료 후 합칠 앞부분 문자열
		str1.append(str3)
		.append("\"재귀함수가 뭔가요?\"\n")
		.append(str3)
		.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
		.append(str3)
		.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
		.append(str3)
		.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		
		//재귀 종료 후 합칠 뒷부분 문자열
		str2.insert(0, "라고 답변하였지.\n")
		.insert(0, str3);
		
		//재귀 호출 횟수에 따라 언더바의 길이가 늘어나도록 설정
		str3.append("____");
		
		//재귀 호출
		return recursion(N-1, str1, str2, str3);
	}
}
