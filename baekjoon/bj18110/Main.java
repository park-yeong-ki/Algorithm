package bj18110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] count = new int[31];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            count[arr[i]]++;
        }

        int size = 0;
        for (int i = 1; i <= 30; i++) {
            while (count[i] != 0) {
                arr[size++] = i;
                count[i]--;
            }
        }

        int exclude = (int) Math.round(N * 0.15);

        double sum = 0;
        for (int i = exclude; i < N - exclude; i++) {
            sum += arr[i];
        }

        System.out.println(Math.round(sum / (N - 2 * exclude)));
    }
}
