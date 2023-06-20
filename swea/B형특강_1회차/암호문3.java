package B형특강_1회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 암호문3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = Integer.parseInt(br.readLine());

            LinkedList<Integer> list = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                char order = st.nextToken().charAt(0);

                int x, y, s;
                switch (order) {
                    case 'I':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            s = Integer.parseInt(st.nextToken());
                            list.add(x + j, s);
                        }
                        break;
                    case 'D':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            list.remove(x + 1);
                        }
                        break;
                    case 'A':
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            s = Integer.parseInt(st.nextToken());
                            list.add(s);
                        }
                        break;
                }
            }

            sb.append("#" + test_case + " ");
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i) + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
