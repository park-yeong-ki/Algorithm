package bj16235;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
    static PriorityQueue<Tree> trees;
    static Queue<Tree> deadTrees;
    static Queue<Tree> newTrees;
    static int N, M, K;
    static int[][] map;
    static int[][] A;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(trees.size());
    }

    static void spring() {
        Tree current;
        while (!trees.isEmpty()) {
            current = trees.poll();
            if (map[current.r][current.c] >= current.age) {
                map[current.r][current.c] -= current.age;
                current.age++;
                newTrees.add(current);
            } else {
                deadTrees.add(current);
            }
        }

        trees.addAll(newTrees);
        newTrees.clear();
    }

    static void summer() {
        Tree current;
        while (!deadTrees.isEmpty()) {
            current = deadTrees.poll();
            map[current.r][current.c] += current.age / 2;
        }
    }

    static void fall(){
        Tree current;
        while (!trees.isEmpty()) {
            current = trees.poll();
            newTrees.add(current);
            if (current.age % 5 != 0) continue;
            for (int j = 0; j < 8; j++) {
                int tR = current.r + dr[j];
                int tC = current.c + dc[j];

                if (tR < 1 || tR > N || tC < 1 || tC > N) continue;
                newTrees.add(new Tree(tR, tC, 1));
            }
        }

        trees.addAll(newTrees);
        newTrees.clear();
    }

    static void winter(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = 5;
            }
        }

        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        trees = new PriorityQueue<>();
        int r, c, age;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            age = Integer.parseInt(st.nextToken());

            trees.add(new Tree(r, c, age));
        }

        deadTrees = new ArrayDeque<>();
        newTrees = new ArrayDeque<>();
    }
}
