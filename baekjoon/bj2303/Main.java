package bj2303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //사람의 수를 나타내는 정수 N
        int N = Integer.parseInt(br.readLine());

        //각 사람당 다섯 장의 카드입력
        int[][] arr = new int[N][5];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //사람 수만큼 반복
        int[] max = new int[N];
        for (int i = 0; i < N; i++) {

            //카드 3장 조합
            int sum = 0;
            for (int j = 0; j < 5; j++) {
                for (int k = j+1; k < 5; k++) {
                    for (int l = k+1; l < 5; l++) {
                        sum = arr[i][j] + arr[i][k] + arr[i][l];
                        int num = sum % 10;
                        //사람별 최대값 계산
                        if (max[i] < num) {
                            max[i] = num;
                        }
                    }
                }
            }
        }

        //최대값이 가장 큰 사람을 구함
        int maxP = 0;
        for (int i = 1; i < N; i++) {
            //숫자가 같으면 번호가 가장 큰 사람 출력
            if (max[maxP] <= max[i]) {
                maxP = i;
            }
        }

        //출력 -> 0번 인덱스부터 사용했으니까 1을 더한다
        System.out.println(maxP+1);
    }
}
