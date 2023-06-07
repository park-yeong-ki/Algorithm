package bj3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int start = 0;
        int end = n - 1;

        int cnt = 0;
        while (start < end) {
            if (arr[start] + arr[end] < x) {
                start++;
            } else if (arr[start] + arr[end] > x) {
                end--;
            } else {
                cnt++;
                start++;
                end--;
            }
        }

        System.out.println(cnt);
    }
}
