package swea2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K, A, B, sum;
    static int[] a, b;
    static class Customer implements  Comparable<Customer>{
        int num, t, reception, repair;

        public Customer(int num, int t, int reception, int repair) {
            this.num = num;
            this.t = t;
            this.reception = reception;
            this.repair = repair;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.t == o.t) {
                return Integer.compare(this.reception, o.reception);
            }

            return Integer.compare(this.t, o.t);
        }
    }

    static Customer[] customers;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            input();
            reception();
            repair();
            find();
            sb.append("#" + test_case + " " + sum + "\n");
        }
        System.out.print(sb);
    }

    static void find() {
        for (int i = 1; i <= K; i++) {
            if (customers[i].reception == A && customers[i].repair == B) {
                sum += customers[i].num;
            }
        }
        if (sum == 0) sum = -1;
    }

    static void repair() {
        Arrays.sort(customers);
        int[] waiting = new int[M + 1];

        for (int i = 1; i <= K; i++) {
            boolean flag = false;
            for (int j = 1; j <= M; j++) {
                if (waiting[j] <= customers[i].t) {
                    customers[i].t += b[j];
                    waiting[j] = customers[i].t;
                    customers[i].repair = j;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                int minIdx = 1;
                for (int j = 2; j <= M; j++) {
                    if (waiting[minIdx] > waiting[j]) {
                        minIdx = j;
                    }
                }
                customers[i].t = waiting[minIdx];
                customers[i].t += b[minIdx];
                waiting[minIdx] = customers[i].t;
                customers[i].repair = minIdx;
            }
        }
    }

    static void reception() {
        int[] waiting = new int[N + 1];

        for (int i = 1; i <= K; i++) {
            boolean flag = false;
            for (int j = 1; j <= N; j++) {
                if (waiting[j] <= customers[i].t) {
                    customers[i].t += a[j];
                    waiting[j] = customers[i].t;
                    customers[i].reception = j;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                int minIdx = 1;
                for (int j = 2; j <= N; j++) {
                    if (waiting[minIdx] > waiting[j]) {
                        minIdx = j;
                    }
                }
                customers[i].t = waiting[minIdx];
                customers[i].t += a[minIdx];
                waiting[minIdx] = customers[i].t;
                customers[i].reception = minIdx;
            }
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        a = new int[N + 1];
        b = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        customers = new Customer[K+1];
        customers[0] = new Customer(0, 0, 0, 0);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            customers[i] = new Customer(i, Integer.parseInt(st.nextToken()), 0, 0);
        }
        sum = 0;
    }
}
