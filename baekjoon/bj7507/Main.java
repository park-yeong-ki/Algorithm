package bj7507;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 개수 입력
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            //경기의 수 입력
            int m = Integer.parseInt(br.readLine());

            //경기 정보를 담을 배열 생성
            int[][] mArr = new int[m][3];

            //배열 요소 입력
            for (int j = 0; j < mArr.length; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                mArr[j][0] = Integer.parseInt(st.nextToken());
                mArr[j][1] = Integer.parseInt(st.nextToken());
                mArr[j][2] = Integer.parseInt(st.nextToken());
            }

            //1.날짜, 2.종료시간을 오름차순으로 정렬한다
            Arrays.sort(mArr, Comparator.comparingInt((int[] arr) -> arr[0]).thenComparingInt((int[] arr) -> arr[2]));

            //볼 수 없는 경기의 개수를 체크
            int count = 0;
            for (int j = 1; j < mArr.length; j++) {
                //같은 날짜인 경우
                if (mArr[j-1][0] == mArr[j][0]) {
                    //정렬한 배열에서 이전 경기의 종료시간이 다음 경기의 시작시간보다 큰 경우는 못보는 경기로 체크
                    if (mArr[j-1][2] > mArr[j][1]) {
                        mArr[j][1] = mArr[j-1][1];
                        mArr[j][2] = mArr[j-1][2];
                        count++;
                    }
                }
            }

            //출력
            bw.write("Scenario #" + i  + ":" + "\n" + (mArr.length - count) + "\n");
            bw.newLine();
        }
        bw.close();
    }
}
