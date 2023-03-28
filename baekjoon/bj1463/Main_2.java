package bj1463;

import java.util.Scanner;

public class Main_2 {
    static int cnt;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        dfs(N, new boolean[N+1]);

        System.out.println(min);
    }

    static void dfs(int N, boolean[] visited) {
        if (cnt >= min) {
            return;
        }

        if(N == 1) {
            min = Math.min(min, cnt);
            return;
        }

        visited[N] = true;

        for (int i = 1; i <= 3; i++) {
            int value = operator(i, N);
            if(!visited[value]) {
                cnt++;
                dfs(value, visited);
                cnt--;
            }
        }

        visited[N] = false;
    }

    static int operator(int num, int value) {
        switch (num) {
            case 1:
                if(value % 3 == 0) {
                    value /= 3;
                }
                break;
            case 2:
                if(value % 2 == 0) {
                    value /= 2;
                }

                break;
            case 3:
                value -= 1;
                break;
            default:
                break;
        }

        return value;
    }
}
