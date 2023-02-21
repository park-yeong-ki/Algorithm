package bj1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //전역변수 설정
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N입력
        int N = Integer.parseInt(br.readLine());

        //행렬 입력
        arr = new int[N][N];
        //전부 0이거나 1인 경우를 생각해서 처음 배열의 합을 구한다.
        int sum = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = Character.getNumericValue(str.charAt(j));
                sum += arr[i][j];
            }
        }

        //출력
        if (sum == 0) {
            System.out.println(0);
        } else if (sum == N*N) {
            System.out.println(1);
        } else {
            System.out.println(quad(0, 0, N));
        }
    }

    //영역을 4분할 설정해주는 함수
    private static String quad(int r, int c, int size) {
        StringBuilder sb = new StringBuilder();

        //왼쪽 위 영역
        String f1 = compression(r, c, size/2);

        //오른쪽 위 영역
        String f2 = compression(r, c+size/2, size/2);

        //왼쪽 아래 영역
        String f3 = compression(r+size/2, c, size/2);

        //오른쪽 아래 영역
        String f4 = compression(r+size/2, c+size/2, size/2);

        return sb.append("(").append(f1).append(f2).append(f3).append(f4).append(")").toString();
    }

    //해당영역의 값을 구하는 함수
    private static String compression(int r, int c, int size) {
        //4분할한 배열의 칸에 적혀있는 합을 구한다.
        int sum = 0;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                sum += arr[i][j];
            }
        }

        //기저조건
        if (sum == size*size) { //모두 1인 경우
            return "1";
        } else if(sum == 0) {  //모두 0인 경우
            return "0";
        } else { //혼합되어있는 경우 -> 다시 영역을 분할
            return quad(r, c, size);
        }

    }
}