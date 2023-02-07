package swea1210;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        //Scanner 생성
        Scanner sc = new Scanner(System.in);

        //10회 반복
        for (int test_case = 1; test_case <= 10; test_case++) {
            //테스트 케이스 번호 입력
            int N = sc.nextInt();

            //100x100 배열 생성 -> 열을 102개 생성하여 ArrayIndexOutOfBoundsException가 발생하지 않게 함
            int[][] arr = new int[100][102];

            //100x100 배열 요소 입력 -> 1열씩 앞으로 땡겨받음
            for (int i = 0; i < 100; i++) {
                for (int j = 1; j < 101; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            //목적지에서부터 시작
            int column = 0;
            for (int i = 1; i < 101; i++) {
                //목적지의 열을 찾는다.
                if (arr[99][i] == 2) {

                    //재귀함수를 통해서 출발지를 찾는다.
                    column = move(arr, 98, i);

                    //출력 -> 1열씩 땡겨서 받았기때문에 결과 값에서는 1을 빼준다.
                    System.out.println("#" + N + " " + (column-1));
                }
            }

        }
    }

    //재귀함수
    public static int move(int[][] arr, int row, int column) {
        //기저조건: 행이 0일경우 재귀 종료
        if (row == 0) {
            return column;
        }

        //아래로 내려가면서 양 옆에 1인 배열 요소를 탐색하며 이동
        for (int j = row; j > 0; j--) {
            //조건이 참일경우 반복문 종료
            if (arr[j][column-1] == 1 || arr[j][column+1] == 1) {
                row = j;
                break;
            }
            row = j;
        }

        //양 옆에 1인 배열 요소를 탐색하였다면 좌측인지 우측인지 확인 후 이동
        if (arr[row][column-1] == 1) {
            for (int j = column-1; j >= 0; j--) {
                if (arr[row][j] != 1) {
                    column = j+1;
                    break;
                }
            }
        }else if (arr[row][column+1] == 1) {
            for (int j = column+1; j < 102; j++) {
                if (arr[row][j] != 1) {
                    column = j-1;
                    break;
                }
            }
        }


        //재귀호출 -> 행은 1만큼 감소하며 호출
        return move(arr, row-1, column);
    }
}