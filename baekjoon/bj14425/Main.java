package bj14425;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        Set<String> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            set.add(sc.next());
        }
        for (int i = 0; i < M; i++) {
            if (set.contains(sc.next())) cnt++;
        }
        System.out.println(cnt);
    }
}
