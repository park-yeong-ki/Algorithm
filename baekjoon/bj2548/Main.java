package bj2548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //자연수의 개수 N
        int N = Integer.parseInt(br.readLine());

        //자연수 배열
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //대표 자연수 계산
        int min = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        outer:
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += Math.abs(arr[i] - arr[j]);
                if (min < sum) continue outer;
            }
            if (min > sum) {
                min = sum;
                result = arr[i];
            } else {
                if (result > arr[i]) {
                    result = arr[i];
                }
            }
        }

        //결과 출력
        System.out.println(result);
    }
}
