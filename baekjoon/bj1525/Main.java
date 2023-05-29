package bj1525;

import java.util.*;

public class Main {
    static int[][] puzzle;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static class Point{
        int r, c;
        StringBuilder puzzles;

        public Point(int r, int c, StringBuilder puzzles) {
            this.r = r;
            this.c = c;
            this.puzzles = puzzles;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        puzzle = new int[3][3];
        Point start = null;
        StringBuilder startPuzzle = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = sc.nextInt();
                startPuzzle.append(puzzle[i][j]);
                if (puzzle[i][j] == 0) start = new Point(i, j, null);
            }
        }
        start.puzzles = startPuzzle;

        System.out.println(bfs(start));
    }

    static int bfs(Point start) {
        Queue<Point> queue = new ArrayDeque<>();

        Set<String> isGenerated = new HashSet<>();

        queue.add(start);
        isGenerated.add(start.puzzles.toString());

        Point current;
        int size, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();

                if (current.r == 2 && current.c == 2) {
                    boolean flag = true;
                    for (int j = 0; j < current.puzzles.length() - 2; j++) {
                        if (Character.getNumericValue(current.puzzles.charAt(j)) + 1 != Character.getNumericValue(current.puzzles.charAt(j+1))) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) return cnt;
                }

                for (int j = 0; j < 4; j++) {
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];

                    if (tR < 0 || tR >= 3 || tC < 0 || tC >= 3) continue;

                    StringBuilder copy = new StringBuilder(current.puzzles);
                    swap(current.r, current.c, tR, tC, copy);
                    if (isGenerated.contains(copy.toString())) continue;

                    queue.add(new Point(tR, tC, copy));
                    isGenerated.add(copy.toString());
                }
            }
            cnt++;
        }
        return -1;
    }

    static void swap(int r, int c, int tR, int tC, StringBuilder copy) {
        char temp = copy.charAt(tR * 3 + tC);
        copy.setCharAt(tR * 3 + tC, '0');
        copy.setCharAt(r * 3 + c, temp);
    }
}
