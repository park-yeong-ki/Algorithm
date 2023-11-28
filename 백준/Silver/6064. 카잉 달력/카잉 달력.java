import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            //M, N, x, y
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int min = Math.min(M, N); //두 수의 최소값만큼 반복

            int cnt = 0;
            if (min == M){ //M이 기준인 경우
                cnt = M - x;
            }else { //N이 기준인 경우
                cnt = N - y;
            }

            x += cnt; //기준값에 맞추기
            y += cnt;

            x %= M;
            y %= N;
            if (x == 0) x = M;
            if (y == 0) y = N;

            int num = Math.abs(M - N);
            int idx = 1;
            int tM = min;
            int tN = min;
            boolean flag = true;
            while (true) {
                if (idx > 1 && tM == min && tN == min) { //이미 나온 숫자인 경우
                    flag = false;
                    break;
                }

                if (tM == x && tN == y) break;

                if (min == M){
                    tN -= num;
                    while (tN <= 0){
                        tN += N;
                    }
                }else {
                    tM -= num;
                    while (tM <= 0){
                        tM += M;
                    }
                }

                idx++;
            }

            if (flag) sb.append(idx * min - cnt).append("\n");
            else sb.append(-1).append("\n");
        }

        System.out.print(sb);

    }
}