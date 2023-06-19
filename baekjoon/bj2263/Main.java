package bj2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] inOrder, postOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        postOrder = new int[n];
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        getPreOrder(0, inOrder.length - 1, 0, postOrder.length - 1);
        System.out.println(sb);
    }

    static void getPreOrder(int iStart, int iEnd, int pStart, int pEnd) {
        if (iStart > iEnd || pStart > pEnd) return;
        int root = postOrder[pEnd];
        sb.append(root + " ");

        int rIdx = 0;
        for (int i = iStart; i <= iEnd; i++) {
            if (inOrder[i] == root) {
                rIdx = i;
                break;
            }
        }
        int left = rIdx - iStart;

        getPreOrder(iStart, rIdx - 1, pStart, pStart + left - 1);
        getPreOrder(rIdx + 1, iEnd, pStart + left, pEnd - 1);
    }
}
