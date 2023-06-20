package bj1253;

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
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int start = 0;
            int end = N-1;
            while (start < end) {
                long num = arr[start] + arr[end];

                if (num < arr[i]) {
                    start++;
                } else if (num > arr[i]) {
                    end--;
                } else {
                    if (start == i) {
                        start++;
                    } else if (end == i) {
                        end--;
                    } else {
                        ans++;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
