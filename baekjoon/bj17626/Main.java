package bj17626;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        ans = Integer.MAX_VALUE;
        bfs((int) Math.sqrt(n), n);
        System.out.println(ans);
    }

    static void bfs(int start, int target) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(new int[]{start, target});
        visited[target] = true;

        int[] current;
        int size, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                if (current[1] == 0){
                    ans = cnt;
                    return;
                }

                for (int j = current[0]; j >= 1; j--) {
                    if (visited[current[1] - (int) Math.pow(j, 2)]) continue;
                    queue.add(new int[]{(int) Math.sqrt(current[1] - (int) Math.pow(j, 2)), current[1] - (int) Math.pow(j, 2)});
                    visited[current[1] - (int) Math.pow(j, 2)] = true;
                }
            }

            cnt++;
        }
    }
}
