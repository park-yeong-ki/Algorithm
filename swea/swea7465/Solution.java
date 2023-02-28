package swea7465;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int[] disjointSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 입력
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //사람의 수, 관계 수 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());


            //각각의 서로소 집합 생성
            makeSet(N);

            //관계수를 유니온 함수에 입력
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            //무리의 개수를 파악하기 위해 set사용
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                set.add(find(disjointSet[i]));
            }

            //출력
            bw.write("#" + test_case + " " + set.size() + "\n");
        }
        bw.close();


    }

    //초기 서로소 집합 생성 -> 자기자신이 루트노드
    static void makeSet(int N) {
        disjointSet = new int[N+1];
        for (int i = 1; i <= N; i++) {
            disjointSet[i] = i;
        }
    }

    //루트 노드를 찾는 함수
    static int find(int p) {
        if (p == disjointSet[p]) {
            return p;
        } else {
            return disjointSet[p] = find(disjointSet[p]);
        }
    }

    //합집합을 해주는 함수
    static void union(int i, int j) {
        int p1 = find(i);
        int p2 = find(j);

        if (p1 < p2) {
            disjointSet[p2] = p1;
        }else {
            disjointSet[p1] = p2;
        }
    }
}