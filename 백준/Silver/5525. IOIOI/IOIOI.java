import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String S = sc.next();

        S = S.replace("IO", "X"); //IO만 찾으면 되기때문에 X로 변경

        int cnt = 0;
        int ans = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'X') {
                cnt++;
            } else { //연속된 문자열이 끝난 경우
                if (cnt >= N) { //연속된 IO의 개수가 N보다 크다면
                    if (S.charAt(i) != 'I') { //연속된 IO를 찾은 후 현재 문자열이 I가 아니라면
                        cnt--;
                    }

                    if (cnt >= N) ans += cnt - N + 1; //결과적으로 Pn이상을 찾은 경우 정답 갱신
                }

                cnt = 0; //연속되지 않았기 때문에 다시 초기화
            }
        }

        if (cnt >= N) { //IO로 문자열이 끝난 경우에서 연속된 IO의 개수가 N보다 크다면
            cnt--; //IO로 끝났기 때문에 -1 감소

            if (cnt >= N) ans += cnt - N + 1; //결과적으로 Pn이상을 찾은 경우 정답 갱신
        }

        System.out.println(ans);
    }
}