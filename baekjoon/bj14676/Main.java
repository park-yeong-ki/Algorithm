package bj14676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static boolean flag;
    static int[] count;
    public static void main(String[] args) throws IOException {
        input();
        check();
        if (flag) System.out.println("King-God-Emperor");
        else System.out.println("Lier!");
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            inDegree[to]++;
        }

        flag = true;
        count = new int[N + 1];
    }

    static void check() throws IOException {
        int type, num;
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());

            switch (type) {
                case 1:
                    if (inDegree[num] == 0) { //진입 차수 확인 -> 0일 경우 지을 수 있음
                        if (count[num] == 0) {
                            for (int to : adjList[num]) {  //처음 짓는 경우 인접리스트의 진입차수 1씩 감소
                                inDegree[to]--;
                            }
                        }
                        count[num]++;
                    } else { //진입 차수 0이 아니면 건설불가
                        flag = false;
                        return;
                    }
                    break;
                case 2:
                    if (count[num] == 0) {
                        flag = false;
                        return;
                    }

                    if (count[num] == 1) {
                        for (int to : adjList[num]) { //인접리스트의 진입차수 1증가
                            inDegree[to]++;
                        }
                    }

                    count[num]--;
                    break;
            }
        }
    }
}
