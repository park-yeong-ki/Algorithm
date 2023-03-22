package bj2527;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //4개의 줄
        for (int test_case = 0; test_case < 4; test_case++) {
            //첫번째 직사각형
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int p1 = sc.nextInt();
            int q1 = sc.nextInt();

            //두번째 직사각형
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int p2 = sc.nextInt();
            int q2 = sc.nextInt();

            //공통부분이 없는 경우
            if (p1 < x2 || q1 < y2 || x1 > p2 || y1 > q2) {
                System.out.println("d");
            }
            //점인 경우
            else if ((p1 == x2 && q1 == y2) || (x1 == p2 && y1 == q2) ||
                    (p1 == x2 && y1 == q2) || (x1 == p2 && q1 == y2)) {
                System.out.println("c");
            }
            //선분인 경우
            else if (y2 == q1 || y1 == q2 || p1 == x2 || p2 == x1) {
                System.out.println("b");
            }
            //직사각형인 경우
            else {
                System.out.println("a");
            }
        }
    }
}
