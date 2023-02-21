package bj14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static int[][] arr;
    static char[][] gear = new char[6][8];
    static boolean[] isRotated = new boolean[6];
    static int K;

    public static void main(String[] args) throws IOException {
        //같은 극이면 회전x
        //다른 극이면 반대방향 회전
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //톱니바퀴 입력
        for (int i = 1; i < 5; i++) {
            gear[i] = br.readLine().toCharArray();
        }
        //ArrayIndexOutOfBoundsException 방지용으로 설정
        isRotated[0] = true;
        isRotated[5] = true;

        //회전 횟수 K입력
        K = Integer.parseInt(br.readLine());

        //회전 방법 입력
        arr = new int[K][2];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //K번 회전한다.
        for (int i = 0; i < K; i++) {
            //재귀함수 사용
            rotate(arr[i][0], arr[i][1]);
        }

        //점수 합 출력
        int score = 0;
        for (int i = 1; i <= 4; i++) {
            //톱니바퀴의 12시방향이 S극인 경우
            if (gear[i][0] == '1') {
                score += Math.pow(2, i - 1);
            }
        }
        System.out.println(score);

    }
    //회전하는 함수
    private static void rotate(int num, int direction) {
        //회전한 경우는 표시를 해준다.
        isRotated[num] = true;

        //톱니바퀴와 앞에 연결된 톱니바퀴의 극이 다른 경우
        if (!isRotated[num+1] && gear[num][2] != gear[num+1][6]) {
            rotate(num + 1, direction * -1);
        }

        //톱니바퀴와 뒤에 연결된 톱니바퀴의 극이 다른경우
        if (!isRotated[num-1] && gear[num][6] != gear[num - 1][2]) {
            rotate(num - 1, direction * -1);
        }

        //반시계 방향일 경우
        if (direction == -1) {
            char temp = gear[num][0];
            for (int i = 0; i < 7; i++) {
                gear[num][i] = gear[num][i + 1];
            }
            gear[num][7] = temp;
        }
        //시계 방향일 경우
        else {
            char temp = gear[num][7];
            for (int i = 7; i >= 1; i--) {
                gear[num][i] = gear[num][i-1];
            }
            gear[num][0] = temp;
        }

        //마지막에는 회전여부를 다시 초기화
        isRotated[num] = false;
    }
}
