package bj2580;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> position = new ArrayList<>();
    static int[][] sudoku;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) {
                    position.add(i * 9 + j);
                }
            }
        }

        setNum(0);
        bw.close();
    }

    static void setNum(int idx) throws IOException {
        if (idx == position.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(sudoku[i][j] + " ");
                }
                bw.newLine();
            }

            flag = true;
            return;
        }

        int r = position.get(idx) / 9;
        int c = position.get(idx) % 9;

        for (int i = 1; i <= 9; i++) {
            if (!check(r, c, i)) continue;
            sudoku[r][c] = i;
            setNum(idx + 1);
            if (flag) return;
            sudoku[r][c] = 0;
        }
    }

    static boolean check(int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[r][i] == num) {
                return false;
            }
            if (sudoku[i][c] == num) {
                return false;
            }
        }

        int sR = r / 3 * 3;
        int sC = c / 3 * 3;
        for (int i = sR; i < sR + 3; i++) {
            for (int j = sC; j < sC + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
