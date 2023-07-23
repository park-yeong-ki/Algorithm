package bj1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static boolean[] button;
    static int[] move = {-1, 1};
    static class Node{
        int num;
        boolean flag;

        public Node(int num, boolean flag) {
            this.num = num;
            this.flag = flag;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        ans = 0;
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[101 + 2 * N][2];

        queue.add(new Node(0, false));

        queue.add(new Node(100, true));
        visited[100][1] = true;

        Node current;
        int size, cnt = 0;
        while (!queue.isEmpty()) {
            size = queue.size();

            for (int i = 0; i < size; i++) {
                current = queue.poll();
                if (!(current.num == 0 && cnt == 0) && current.num == N) {
                    ans = cnt;
                    return;
                }

                for (int j = 0; j < 10; j++) {
                    if (current.flag) break;
                    if (button[j]) continue;
                    int temp = current.num;
                    temp *= 10;
                    temp += j;

                    if (temp >= 101 + 2 * N) continue;
                    if (visited[temp][0]) continue;
                    queue.add(new Node(temp, false));
                    visited[temp][0] = true;
                }

                for (int j = 0; j < 2; j++) {
                    if (current.num == 0 && cnt == 0) break;
                    int temp = current.num + move[j];
                    if (temp >= 101 + 2 * N) continue;
                    if (temp < 0 || visited[temp][1]) continue;
                    queue.add(new Node(temp, true));
                    visited[temp][1] = true;
                }
            }
            cnt++;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        button = new boolean[10];

        if (M == 0) return;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            button[Integer.parseInt(st.nextToken())] = true;
        }
    }
}
