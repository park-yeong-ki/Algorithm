package bj15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int start = 0;
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        int[] count = new int[d + 1];
        for (int i = 0; i < N + k - 1; i++) {
            if (count[arr[i % N]] == 0) cnt++;
            count[arr[i % N]]++;

            if (i >= k - 1) {
                if (i >= k) {
                    count[arr[start]]--;
                    if (count[arr[start]] == 0) cnt--;

                    start++;
                }

                if (count[c] == 0) max = Math.max(max, cnt + 1);
                else max = Math.max(max, cnt);
            }
        }

        System.out.println(max);
    }
}
