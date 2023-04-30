package bj1174;

import com.sun.org.apache.xpath.internal.operations.Neg;

import java.util.Scanner;

public class Main {
    static int[] arr;
    static int cnt;
    static int N;
    static boolean flag;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        //가장 큰 줄어드는 수: 9876543210
        for (int i = 1; i <= 10; i++) {
            arr = new int[i];
            dfs(0, 10);
        }

        if (flag) System.out.println(result);
        else System.out.println(-1);
    }

    static void dfs(int idx, int end) {
        if (idx == arr.length) {
            cnt++;
            if (cnt == N) {
                for (int i = 0; i < arr.length; i++) {
                    result.append(arr[i]);
                }
                flag = true;
            }
            return;
        }

        for (int i = 0; i < end; i++) {
            arr[idx] = i;
            dfs(idx+1, arr[idx]);
            if (flag) return;
        }
    }
}
