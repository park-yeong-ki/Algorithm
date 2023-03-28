package bj2992;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] arr;
    static int N;
    static int[] pArr;
    static boolean[] isSelected;
    static String X;
    static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        X = sc.next();
        N = X.length();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = X.charAt(i) - '0';
        }
        Arrays.sort(arr);

        isSelected = new boolean[N];
        pArr = new int[N];
        perm(0);

        System.out.println(result);
    }

    static void perm(int idx) {
        if (idx == N) {
            int num = makeNum(pArr);
            if (num > Integer.parseInt(X)) {
                result = num;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (result != 0) break;
            if (isSelected[i]) continue;
            pArr[idx] = arr[i];
            isSelected[i] = true;
            perm(idx + 1);
            isSelected[i] = false;
        }
    }

    static int makeNum(int[] pArr) {
        String str = "";
        for (int i = 0; i < pArr.length; i++) {
            str += pArr[i];
        }
        return Integer.parseInt(str);
    }
}
