package bj5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Node{
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
        }

        void insert(int num, Node node) {
            if (node.key > num) {
                if (node.left == null) {
                    node.left = new Node(num);
                } else {
                    insert(num, node.left);
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(num);
                } else {
                    insert(num, node.right);
                }
            }
        }
    }

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node tree = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) break;

            tree.insert(Integer.parseInt(str), tree);
        }

        sb = new StringBuilder();
        dfs(tree);

        System.out.print(sb);
    }

    static void dfs(Node node) {
        if (node == null) return;

        dfs(node.left);
        dfs(node.right);

        sb.append(node.key + "\n");
    }
}
