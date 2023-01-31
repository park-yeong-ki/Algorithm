package swea1974;


import java.util.Scanner;
import java.io.FileInputStream;

class Solution{
	public static void main(String args[]) throws Exception{
	
		//Scanner 선언
		Scanner sc = new Scanner(System.in);
		int T;
		//T입력
		T=sc.nextInt();
	
		//T횟수만큼 반복문 돌리기 
		for(int test_case = 1; test_case <= T; test_case++){
		
			//배열 생성
            int[][] arr = new int[9][9];

            //배열 요소 입력
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            //풀이
            int result = 1;

            //행검사
            outer:
            for (int i = 0; i < 9; i++) {
                int[] count = new int[10];
                for (int j = 0; j < 9; j++) {
                    count[arr[i][j]]++;
                }

                for (int j = 1; j < count.length; j++) {
                    if (count[j] != 1) {
                        result = 0;
                        break outer;
                    }
                }
            }

            //열검사
            outer:
            for (int i = 0; i < 9; i++) {
                int[] count = new int[10];
                for (int j = 0; j < 9; j++) {
                    count[arr[j][i]]++;
                }

                for (int j = 1; j < count.length; j++) {
                    if (count[j] != 1) {
                        result = 0;
                        break outer;
                    }
                }
            }

            //내부 배열 검사
            outer:
            for (int i = 0; i < 9; i+=3) {
                for (int j = 0; j < 9; j+=3) {
                    int[] count = new int[10];
                    for (int k = i; k < 3+i; k++) {
                        for (int m = j; m < 3+j; m++) {
                            count[arr[k][m]]++;
                        }
                    }

                    for (int k = 1; k < count.length; k++) {
                        if (count[k] != 1) {
                            result = 0;
                            break outer;
                        }
                    }
                }
            }


            //출력
            System.out.println("#" + test_case + " " + result);

		}
	}
}