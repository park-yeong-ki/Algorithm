package bj1717;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[] set;

    //자신이 루트 노드인 집합 생성
    static void makeSet() {
        for (int i = 0; i <= n; i++) {
            set[i] = i;
        }
    }

    //루트 노드 찾아줌
    static int findSet(int num) {
        if (set[num] == num) {
            return num;
        }

        //경로압축
        return set[num] = findSet(set[num]);
    }

    //합집합 연산
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        //부모가 같은 경우 합집합x
        if (aRoot == bRoot) {
            return true;
        }

        set[bRoot] = aRoot;
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        set = new int[n+1];

        makeSet();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //합집합 만들기
            if (o == 0) {
                union(a, b);
            }
            //같은 집합인지 확인
            else {
                if (findSet(a) == findSet(b)) {
                    bw.write("YES");
                    bw.newLine();
                }else {
                    bw.write("NO");
                    bw.newLine();
                }
            }
        }
        bw.close();
    }
}