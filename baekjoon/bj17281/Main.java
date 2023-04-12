package bj17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isSelected;
    static int N;
    static int[][] arr;
    static int[] hitter;
    static int max = Integer.MIN_VALUE;
    static boolean[] base = new boolean[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][9];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isSelected = new boolean[9];
        hitter = new int[9];
        perm(0);

        System.out.println(max);
    }

    static void perm(int idx) {
        if (idx == 9) {
            int next = 0;
            int score = 0;
            for (int i = 0; i < N; i++) {
                int out = 0;
                Arrays.fill(base, false);
                while (out < 3) {
                    switch (arr[i][hitter[next]]) {
                        case 0:
                            out++;
                            break;
                        case 1:
                            for (int j = 3; j >= 1; j--) {
                                if (base[j]) {
                                    if (j == 3) {
                                        score++;
                                        base[j] = false;
                                        continue;
                                    }
                                    base[j + 1] = true;
                                    base[j] = false;
                                }
                            }
                            base[1] = true;
                            break;
                        case 2:
                            for (int j = 3; j >= 1; j--) {
                                if (base[j]) {
                                    if (j >= 2) {
                                        score++;
                                        base[j] = false;
                                        continue;
                                    }
                                    base[j + 2] = true;
                                    base[j] = false;
                                }
                            }
                            base[2] = true;
                            break;
                        case 3:
                            for (int j = 3; j >= 1; j--) {
                                if (base[j]) {
                                    score++;
                                    base[j] = false;
                                }
                            }
                            base[3] = true;
                            break;
                        case 4:
                            for (int j = 3; j >= 1; j--) {
                                if (base[j]) {
                                    score++;
                                    base[j] = false;
                                }
                            }
                            score++;
                            break;
                    }

                    next = (next + 1) % 9;
                }
            }

            max = Math.max(max, score);

            return;
        }

        if (idx == 3) {
            hitter[idx] = 0;
            perm(idx + 1);
        }

        for (int i = 1; i < 9; i++) {
            if (isSelected[i]) continue;
            hitter[idx] = i;
            isSelected[i] = true;
            perm(idx + 1);
            isSelected[i] = false;
        }
    }
}