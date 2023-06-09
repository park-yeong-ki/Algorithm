package bj1094;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();

        PriorityQueue<Integer> stick = new PriorityQueue<>();
        stick.add(64);

        int sum = 64;
        while (sum > X) {
            int num = stick.poll();
            num /= 2;

            if (sum - num >= X) {
                stick.add(num);
                sum -= num;
            } else {
                stick.add(num);
                stick.add(num);
            }
        }

        System.out.println(stick.size());
    }
}
