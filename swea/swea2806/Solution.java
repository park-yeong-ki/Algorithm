package swea2806;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static int N;
    static int[] queens;
    static List<Integer> position;
    static int qCount;

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();

            position = new ArrayList<Integer>();
            int num = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    position.add(num++);
                }
            }

            queens = new int[N];
            setQueen(0, 0);

            sb.append("#" + test_case + " " + qCount + "\n");
            qCount = 0;
        }
        System.out.println(sb);
    }

    static void setQueen(int start, int cnt) {
        if (!isPossible(cnt-1)) {
            return;
        }

        if (cnt == N) {
            qCount++;
            return;
        }

        for (int i = start; i < position.size(); i++) {
            queens[cnt] = i;
            setQueen(i/N*N+N, cnt + 1);
        }
    }

    static boolean isPossible(int cnt) {

        for (int i = 0; i < cnt; i++) {
            int cRow = queens[cnt] / N;
            int cCol = queens[cnt] % N;
            int pRow = queens[i] / N;
            int pCol = queens[i] % N;

            if (pCol == cCol || Math.abs(pRow - cRow) == Math.abs(pCol - cCol)) {
                return false;
            }
        }

        return true;
    }
}