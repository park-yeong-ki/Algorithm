package B형특강_5회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사탕가방 {
    static int N;
    static long[] arr;
    static long M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Long.parseLong(st.nextToken());
            arr = new long[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            long start = 1;
            long end = (long) Math.pow(10, 18);
            while (start <= end) {
                long mid = (start + end) / 2;

                long sum = 0;
                for (int i = 0; i < N; i++) {
                    sum += arr[i] / mid;
                }

                if (sum < M) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            sb.append("#" + test_case + " " + end + "\n");
        }
        System.out.print(sb);
    }
}