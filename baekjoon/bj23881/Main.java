package bj23881;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //배열 A의 크기 N, 교환 횟수 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //배열 A입력
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //선택정렬
        boolean flag = false;
        int count = 0;
        int num1 = 0, num2 = 0;
        for (int i = N-1; i >= 1; i--) {
            int max = i;
            for (int j = i-1; j >= 0; j--) {
                if (arr[max] < arr[j]) {
                    max = j;
                }
            }

            if (max != i) {
                //스왑
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
                count++;

                //K번째 교환인 경우
                if (count == K) {
                    flag = true;
                    num1 = arr[max];
                    num2 = arr[i];
                    break;
                }
            }
        }

        //출력
        if (flag) System.out.println(num1 + " " + num2);
        else System.out.println(-1);
    }
}
