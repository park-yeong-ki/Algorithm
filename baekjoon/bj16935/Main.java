package bj16935;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        //배열의 크기 N, M 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //연산의 수 R입력
        int R = Integer.parseInt(st.nextToken());

        //배열 생성
        int[][] arr = new int[N][M];

        //배열 요소 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //연산번호 입력
        int[] num = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        //연산번호에 따라 수행할 경우 나누기
        for (int count = 0; count < num.length; count++) {
            switch (num[count]) {
                //상하 반전
                case 1:
                    for (int i = 0; i < arr[0].length; i++) {
                        for (int j = 0; j < arr.length/2; j++) {
                            int temp = arr[arr.length-1-j][i];
                            arr[arr.length-1-j][i] = arr[j][i];
                            arr[j][i] = temp;
                        }
                    }
                    break;
                //좌우 반전
                case 2:
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length/2; j++) {
                            int temp = arr[i][arr[0].length-1-j];
                            arr[i][arr[0].length-1-j] = arr[i][j];
                            arr[i][j] = temp;
                        }
                    }
                    break;
                //오른쪽으로 90도 회전 -> 가장 첫 열에 있는 요소를 새로운 배열의 행으로 대입
                case 3:
                    int[][] arrR90 = new int[arr[0].length][arr.length];
                    for (int i = 0; i < arr[0].length; i++) {
                        for (int j = arr.length-1; j >= 0; j--) {
                            arrR90[i][arr.length-1 - j] = arr[j][i];
                        }
                    }
                    //배열의 얕은 복사 -> 2차원 배열은 clone으로 복사하기 번거로움, 어차피 반복할 경우 배열이 arrR90 배열은 새로 생성
                    arr = arrR90;
                    break;
                //왼쪽으로 90도 회전 -> 가장 마지막 열에 있는 요소를 새로운 배열의 행으로 대입
                case 4:
                    int[][] arrL90 = new int[arr[0].length][arr.length];
                    for (int i = arr[0].length-1; i >= 0; i--) {
                        for (int j = 0; j < arr.length; j++) {
                            arrL90[arr[0].length-1 - i][j] = arr[j][i];
                        }
                    }
                    //배열의 얕은 복사 -> 2차원 배열은 clone으로 복사하기 번거로움, 어차피 반복할 경우 배열이 arrL90 배열은 새로 생성
                    arr = arrL90;
                    break;
                //4개의 부분 배열로 나눈후 시계방향 회전
                case 5:
                    int[][] arrPR = new int[arr.length][arr[0].length];
                    for (int i = 0; i < arr.length/2; i++) {
                        for (int j = 0; j < arr[0].length/2; j++) {
                            //1번 -> 2번
                            arrPR[i][j+arr[0].length/2] = arr[i][j];
                            //2번 -> 3번
                            arrPR[i+arr.length/2][j+arr[0].length/2] = arr[i][j+arr[0].length/2];
                            //3번 -> 4번
                            arrPR[i+arr.length/2][j] = arr[i+arr.length/2][j+arr[0].length/2];
                            //4번 -> 1번
                            arrPR[i][j] = arr[i+arr.length/2][j];
                        }
                    }
                    //배열의 얕은 복사
                    arr = arrPR;
                    break;
                //4개의 부분 배열로 나눈후 시계반대방향 회전
                case 6:
                    int[][] arrPL = new int[arr.length][arr[0].length];
                    for (int i = 0; i < arr.length/2; i++) {
                        for (int j = 0; j < arr[0].length/2; j++) {
                            //1번 -> 4번
                            arrPL[i+arr.length/2][j] = arr[i][j];
                            //4번 -> 3번
                            arrPL[i+arr.length/2][j+arr[0].length/2] = arr[i+arr.length/2][j];
                            //3번 -> 2번
                            arrPL[i][j+arr[0].length/2] = arr[i+arr.length/2][j+arr[0].length/2];
                            //2번 -> 1번
                            arrPL[i][j] = arr[i][j+arr[0].length/2];
                        }
                    }
                    //배열의 얕은 복사
                    arr = arrPL;
                    break;
                default:
                    break;
            }
        }


        //출력
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.newLine();
        }

        bw.close();
    }
}