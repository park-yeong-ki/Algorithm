import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int num;
        String oper;

        Node(int num, String oper){
            this.num = num;
            this.oper = oper;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            sb.append(bfs(A, B));
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static String bfs(int start, int end) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10000]; //숫자에 대한 방문 배열 생성

        queue.add(new Node(start, ""));
        visited[start] = true;

        Node current;
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.num == end) { //최종값과 같은 경우
                return current.oper;
            }

            num = D(current.num);
            if (!visited[num]) {
                sb.append(current.oper).append("D");
                queue.add(new Node(num, sb.toString()));
                visited[num] = true;
                sb.setLength(0);
            }

            num = S(current.num);
            if (!visited[num]) {
                sb.append(current.oper).append("S");
                queue.add(new Node(num, sb.toString()));
                visited[num] = true;
                sb.setLength(0);
            }

            num = L(current.num);
            if (!visited[num]) {
                sb.append(current.oper).append("L");
                queue.add(new Node(num, sb.toString()));
                visited[num] = true;
                sb.setLength(0);
            }

            num = R(current.num);
            if (!visited[num]) {
                sb.append(current.oper).append("R");
                queue.add(new Node(num, sb.toString()));
                visited[num] = true;
                sb.setLength(0);
            }

        }

        return "Not Found";
    }

    static int D(int n) {
        int num = n * 2;
        if (num > 9999) num %= 10000;
        return num;
    }

    static int S(int n) {
        int num = n - 1;
        if (num < 0) num = 9999;
        return num;
    }

    static int L(int n) {
        int[] arr = divideNum(n);
        int num = ((arr[1] * 10 + arr[2]) * 10 + arr[3]) * 10 + arr[0];
        return num;
    }

    static int R(int n) {
        int[] arr = divideNum(n);
        int num = ((arr[3] * 10 + arr[0]) * 10 + arr[1]) * 10 + arr[2];
        return num;
    }

    static int[] divideNum(int n) {
        int num = n;
        int n1 = num / 1000;
        num %= 1000;
        int n2 = num / 100;
        num %= 100;
        int n3 = num / 10;
        num %= 10;
        int n4 = num;

        return new int[]{n1, n2, n3, n4}; //자리수를 나눠서 반환
    }
}