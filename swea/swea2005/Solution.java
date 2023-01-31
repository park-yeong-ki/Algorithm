package swea2005;


import java.util.Scanner;
import java.io.FileInputStream;


class Solution{
	public static void main(String args[]) throws Exception{
		
		//Scanner 선언
		Scanner sc = new Scanner(System.in);
		int T;
		//T입력
		T=sc.nextInt();
		
		//1부터 T까지 반복
		for(int test_case = 1; test_case <= T; test_case++){
		
			//입력
            int N = sc.nextInt();
            int[][] arr = new int[N][N];

            //공식 적용
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i+1; j++) {
                    if (j == 0 || j == i) { //첫번째 열과 마지막 열은 무조건 1임
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
                    }
                }
            }

            //출력
            System.out.println("#" + test_case);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i+1; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
		}
	}
}