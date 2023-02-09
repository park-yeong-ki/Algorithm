package swea6808;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    //전역변수 설정
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] card = new int[9];
    static int[] nCard = new int[9];
    static int[] arr = new int[9];
    static boolean[] isChecked = new boolean[9];
    static int win = 0;
    static int lose = 0;

    public static void main(String[] args) throws IOException {
        //테스트 케이스 개수 입력
        int T = Integer.parseInt(br.readLine());

        //T만큼 반복
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //카드 배열 요소 입력
            for (int i = 0; i < 9; i++) {
                card[i] = Integer.parseInt(st.nextToken());
            }

            //인영이 카드배열 요소 등록
            int size = 0;
            outer:
            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i + 1 == card[j]) {
                        continue outer;
                    }
                }
                nCard[size++] = i+1;
            }

            //재귀함수 사용
            perm(0);

            //출력
            bw.write("#" + test_case + " " + win + " " + lose + "\n");

            //승리 횟수와 패배 횟수 초기화
            win = lose = 0;
        }
        bw.close();
    }

    //재귀함수 생성
    public static void perm(int cnt) throws IOException {
        //기저조건: 9개가 전부 채워지면 재귀 종료
        if (cnt == 9) {
            //각 게임 별 결과에 따라서 주어진 조건대로 점수를 설정함
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > card[i]) {
                    sum1 += arr[i] + card[i];
                } else {
                    sum2 += card[i] + arr[i];
                }
            }

            //승리수와 패배수 갱신
            if (sum1 < sum2) {
                win++;
            } else if (sum1 > sum2) {
                lose++;
            }

            return;
        }

        //유도부분: 순열
        for (int i = 0; i < 9; i++) {
            if (isChecked[i]) continue;
            arr[cnt] = nCard[i];
            isChecked[i] = true;
            perm(cnt + 1);
            isChecked[i] = false;
        }
    }
}
