package bj21921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int max = 0;
        int prev = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (i < X) {
                sum += arr[i];
            } else {
                sum -= arr[prev];
                sum += arr[i];
                prev++;
            }

            if (max < sum) {
                max = sum;
                cnt = 1;
            } else if (max == sum) {
                cnt++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
