package bj2239;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] sudoku;
    static List<Integer> list = new ArrayList<>();
    static int[][] result = new int[9][9];
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sudoku = new int[9][9];
        int num = 0;
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = str.charAt(j) - '0';
                if (sudoku[i][j] == 0) {
                    list.add(num);
                }
                num++;
            }
        }

        setNumber(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(result[i][j] + "");
            }
            bw.newLine();
        }
        bw.close();
    }

    static void setNumber(int cnt) {
        if (cnt == list.size()) {
            for (int i = 0; i < 9; i++) {
                result[i] = sudoku[i].clone();
            }
            flag = true;
            return;
        }

        int num = list.get(cnt);
        int r = num / 9;
        int c = num % 9;

        boolean[] isSelected = new boolean[10];

        for (int i = 0; i < 9; i++) {
            isSelected[sudoku[r][i]] = true;
        }

        for (int i = 0; i < 9; i++) {
            isSelected[sudoku[i][c]] = true;
        }

        int sR = r / 3 * 3;
        int sC = c / 3 * 3;
        for (int i = sR; i < sR + 3; i++) {
            for (int j = sC; j < sC + 3; j++) {
                isSelected[sudoku[i][j]] = true;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (isSelected[i]) continue;
            sudoku[r][c] = i;
            setNumber(cnt + 1);
            if (flag) return;
            sudoku[r][c] = 0;
        }
    }
}
