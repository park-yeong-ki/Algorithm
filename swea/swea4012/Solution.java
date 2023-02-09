package swea4012;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    //전역변수 선언
    static int N;
    static int[][] S;
    static int[] arr;
    static List<Integer> taste = new ArrayList<>();

    //조합을 이용하여 해결
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //테스트 케이스 입력
        int T = sc.nextInt();

        //T만큼 반복
        for (int test_case = 1; test_case <= T; test_case++) {

            //재료수 N입력
            N = sc.nextInt();

            //시너지 배열 생성
            S = new int[N][N];

            //조합할 개수만큼의 배열 생성
            arr = new int[N/2];

            //배열 요소 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    S[i][j] = sc.nextInt();
                }
            }
            //재귀함수 사용
            comb(0, 0);

            //A음식의 맛과 B음식의 맛의 최소값을 구해준다
            int min = Integer.MAX_VALUE;
            //조합한 결과의 대칭적인 성질을 이용하여 A음식의 맛과 B음식의 맛의 차를 구할 수 있다.
            for (int i = 0; i < taste.size()/2; i++) {
                min = Math.min(min, Math.abs(taste.get(i) - taste.get(taste.size() - 1 - i)));
            }
            //리스트 초기화
            taste.clear();

            //최소값 출력
            System.out.println("#" + test_case + " " + min);
        }
    }
    //재귀함수 생성
    public static void comb(int start, int idx){
        //기저조건: N/2개씩 조합을 마치면 재귀 종료
        if (idx == N/2) {
            //식재료를 N/2개 고른 조합에서 다시 식재료를 2개씩 조합하여 음식의 맛을 구한다.
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i+1; j < arr.length; j++) {
                    sum += S[arr[i]][arr[j]] + S[arr[j]][arr[i]];
                }
            }
            //음식의 맛을 리스트에 추가
            taste.add(sum);

            return;
        }

        //유도부분
        for (int i = start; i < N; i++) {
            arr[idx] = i;
            comb(i+1, idx+1);
        }
    }
}
