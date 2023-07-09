package bj2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Long[] arr = new Long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        //거리를 기준으로 결정문제로 바꾸어 생각한다
        //이분탐색으로 거리를 설정하면서 N개의 집에 C개의 공유기를 설치할 수 있는가?
        long start = 1;
        long end = arr[N - 1] - arr[0];
        while (start <= end) {
            long mid = (start + end) / 2;

            long prev = arr[0];
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                if (arr[i] - prev >= mid) {
                    prev = arr[i];
                    cnt++;
                }
            }

            if (cnt < C) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(end);
    }
}
