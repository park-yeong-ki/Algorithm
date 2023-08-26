package bj17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, k, ans, row ,col;
    static int[][] arr;
    static Node[] count;
    static Set<Integer> set;
    static ArrayList<Integer>[] lists;
    static List<Node> nodes;

    static class Node implements Comparable<Node> {
        int num, cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cnt == o.cnt) {
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        operate();
        System.out.println(ans);
    }

    static void operate() {
        ans = -1;
        int time = 0;
        boolean flag = false;
        while (time <= 100) {
            if (r-1 < arr.length && c-1 < arr[0].length && arr[r - 1][c - 1] == k) {
                flag = true;
                break;
            }
            row = arr.length;
            col = arr[0].length;
            if (row >= col) R();
            else C();

            time++;
        }

        if (flag) ans = time;
    }

    static void R() {
        lists = new ArrayList[row];
        for (int i = 0; i < row; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < row; i++) {
            //행 정렬하기
            count = new Node[101];
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 0) continue;
                if (count[arr[i][j]] == null) count[arr[i][j]] = new Node(arr[i][j], 1);
                else count[arr[i][j]].cnt++;
                set.add(arr[i][j]);
            }

            lists[i] = new ArrayList<>();
            lists[i].addAll(set);
            set.clear();

            nodes = new ArrayList<>();
            for (int num : lists[i]) {
                nodes.add(count[num]);
            }

            Collections.sort(nodes);

            lists[i].clear();
            for (Node node : nodes) {
                lists[i].add(node.num);
                lists[i].add(node.cnt);
            }
        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            max = Math.max(max, lists[i].size());
        }

        if (max > 100) max = 100;

        arr = new int[row][max];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                if (j >= 100) break;
                arr[i][j] = lists[i].get(j);
            }
        }
    }

    static void C() {
        lists = new ArrayList[col];
        for (int i = 0; i < col; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < col; i++) {
            //열 정렬하기
            count = new Node[101];
            for (int j = 0; j < row; j++) {
                if (arr[j][i] == 0) continue;
                if (count[arr[j][i]] == null) count[arr[j][i]] = new Node(arr[j][i], 1);
                else count[arr[j][i]].cnt++;
                set.add(arr[j][i]);
            }

            lists[i] = new ArrayList<>();
            lists[i].addAll(set);
            set.clear();

            nodes = new ArrayList<>();
            for (int num : lists[i]) {
                nodes.add(count[num]);
            }

            Collections.sort(nodes);

            lists[i].clear();
            for (Node node : nodes) {
                lists[i].add(node.num);
                lists[i].add(node.cnt);
            }
        }

        int max = 0;
        for (int i = 0; i < col; i++) {
            max = Math.max(max, lists[i].size());
        }

        if (max > 100) max = 100;

        arr = new int[max][col];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                if (j >= 100) break;
                arr[j][i] = lists[i].get(j);
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        set = new HashSet<>();
    }
}
