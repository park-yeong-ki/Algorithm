package bj2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        long[] ans = new long[3];
        for (int i = 0; i < N; i++) {
            int start = 0;
            int end = N - 1;

            while (start < end) {
                long sum = arr[i] + arr[start] + arr[end];
                if (i != start && i != end && min > Math.abs(sum)) {
                    ans[0] = arr[i];
                    ans[1] = arr[start];
                    ans[2] = arr[end];

                    min = Math.abs(sum);
                }

                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                    end--;
                }
            }
        }

        Arrays.sort(ans);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
