package swea1966;
import java.util.Scanner;
import java.util.Arrays;


class Solution
{
    public static void main(String args[]) throws Exception {
		//Scanner 생성
        Scanner sc = new Scanner(System.in);
        int T;
        //테스트 케이스 입력
        T=sc.nextInt();

        //1부터 T까지 반복
        for(int test_case = 1; test_case <= T; test_case++)
        {

            //입력
            int N = sc.nextInt();

            //입력받은 크기의 배열 생성
            int[] arr = new int[N];

            //배열 요소 입력
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }

            //배열 오름차순 정렬
            Arrays.sort(arr);

            //결과 출력
            System.out.print("#" + test_case + " ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}