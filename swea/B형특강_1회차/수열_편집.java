package B형특강_1회차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수열_편집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int idx = Integer.parseInt(st.nextToken());

                int[] nArr;
                int move = 0;
                switch (command) {
                    case "I":
                        int num = Integer.parseInt(st.nextToken());
                        nArr = new int[arr.length + 1];
                        for (int j = 0; j < arr.length; j++) {
                            if (j == idx) {
                                nArr[j] = num;
                                move++;
                            }
                            nArr[j+move] = arr[j];
                        }
                        arr = nArr;
                        break;
                    case "D":
                        nArr = new int[arr.length - 1];
                        for (int j = 0; j < arr.length; j++) {
                            if (j == idx) move--;
                            else nArr[j+move] = arr[j];
                        }
                        arr = nArr;
                        break;
                    case "C":
                        int cNum = Integer.parseInt(st.nextToken());
                        arr[idx] = cNum;
                        break;
                }
            }

            sb.append("#" + test_case + " ");
            if(arr.length > L) sb.append(arr[L] + "\n");
            else sb.append(-1 + "\n");
        }
        System.out.print(sb);
    }
}
