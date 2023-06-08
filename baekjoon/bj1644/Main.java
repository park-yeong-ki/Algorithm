package bj1644;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        boolean[] notPrime = new boolean[N + 1];

        notPrime[0] = true;
        notPrime[1] = true;

        for (int i = 2; i * i <= N; i++) {
            if (notPrime[i]) continue;
            for (int j = i * i; j <= N; j += i) {
                notPrime[j] = true;
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            if (!notPrime[i]) primeList.add(i);
        }

        int start = 0;
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < primeList.size(); i++) {
            sum += primeList.get(i);
            while (sum > N) {
                sum -= primeList.get(start++);
            }
            if (sum == N) cnt++;
        }

        System.out.println(cnt);
    }
}
