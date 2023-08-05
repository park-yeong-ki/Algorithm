package bj1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //진실을 알 수 있는 그룹과 진실을 모르는 그룹으로 나누어 문제를 해결한다.
        input();
        makeSet();
        for (int num : truth) {
            parents[num] = 0;
        }

        for (int i = 0; i < M; i++) {
            int a = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(a, party[i].get(j));
            }
        }

        int ans = 0;
        for (int i = 0; i < M; i++) {
            boolean flag = true;
            for (int num : party[i]) {
                if (findSet(num) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }

        System.out.println(ans);
    }

    static int N, M;
    static int[] parents;
    static ArrayList<Integer> truth;
    static ArrayList<Integer>[] party;

    static void makeSet() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int num) {
        if (num == parents[num]) {
            return num;
        }
        return parents[num] = findSet(parents[num]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) {
            return false;
        }

        if (aRoot == 0) {
            parents[bRoot] = aRoot;
        } else {
            parents[aRoot] = bRoot;
        }
        return true;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        truth = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            truth.add(Integer.parseInt(st.nextToken()));
        }

        party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new ArrayList<>();
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }
    }
}
