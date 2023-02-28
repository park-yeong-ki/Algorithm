package bj2567;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //4방향 탐색할 배열 생성
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        //색종이 수 입력
        int N = Integer.parseInt(br.readLine());
        //색종이 정보
        int[][] cPaper = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cPaper[i][0] = Integer.parseInt(st.nextToken())+1;
            cPaper[i][1] = Integer.parseInt(st.nextToken())+1;
        }

        int l =102;
        int[][] arr = new int[l][l];
        //색종이 붙이기
        for (int i = 0; i < N; i++) {
            int col = cPaper[i][0];
            int row = l - 1 - cPaper[i][1];
            for (int j = row; j > row - 10; j--) {
                for (int k = col; k < col + 10; k++) {
                    arr[j][k] = 1;
                }
            }
        }

        //둘레 계산하기
        int count = 0;
        //중복된 횟수 제거
        boolean[][] checkR = new boolean[l][l];
        boolean[][] checkC = new boolean[l][l];
        //색종이의 개수만큼 반복하기
        for (int i = 0; i < N; i++) {
            int col = cPaper[i][0];
            int row = l - 1 - cPaper[i][1];
            //가로 길이 계산학기
            for (int j = col; j < col+10; j++) {
                //상하 탐색해서 흰 공간이 보이면 카운트
                for (int k = 0; k < 2; k++) {
                    //아랫변
                    if (!checkR[row][j] && arr[row + dx[k]][j] == 0) {
                        count++;
                        checkR[row][j] = true;
                    }
                    //윗변
                    if (!checkR[row - 9][j] && arr[row - 9 + dx[k]][j] == 0) {
                        count++;
                        checkR[row - 9][j] = true;
                    }
                }
            }
            //세로 길이 계산하기
            for (int j = row; j > row-10; j--) {
                //좌우 탐색해서 흰 공간이 보이면 카운트
                for (int k = 2; k < 4; k++) {
                    //좌변
                    if (!checkC[j][col] && arr[j][col + dy[k]] == 0) {
                        count++;
                        checkC[j][col] = true;
                    }
                    //우변
                    if (!checkC[j][col+9] && arr[j][col + 9 + dy[k]] == 0) {
                        count++;
                        checkC[j][col + 9] = true;
                    }
                }
            }
        }

        //출력
        bw.write(count + "\n");
        bw.close();
    }
}