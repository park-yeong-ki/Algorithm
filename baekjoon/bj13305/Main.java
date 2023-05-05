package bj13305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[] distance, price;
    static int N;
    public static void main(String[] args) throws IOException {
        input();
        System.out.println(calculate());
    }

    static long calculate() {
        long sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += price[i] * distance[i];
            if (price[i] < price[i + 1]) price[i + 1] = price[i];
        }
        return sum;
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        distance = new long[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }
        price = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
    }
}
