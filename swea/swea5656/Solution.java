package swea5656;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int H;
    static int W;
    static int[] result;
    static int[][] arr;
    static int count;
    static int max = Integer.MIN_VALUE;
    static int[] dx = new int[4];
    static int[] dy = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //테스트 케이스 개수 입력
        int T = Integer.parseInt(br.readLine());

        //테스트 케이스 입력
        for (int test_case = 1; test_case <= T; test_case++) {
            //N, W, H입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            //벽돌 정보 입력
            arr = new int[H][W];
            int total = 0;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] != 0) {
                        total++;
                    }
                }
            }

            //N개의 구슬을 쏠 열을 조합
            result = new int[N];
            perm(0);

            bw.write("#" + test_case + " " + (total - max) + "\n");
            max = Integer.MIN_VALUE;
        }

        bw.close();
    }

    //구슬 순열
    static void perm(int cnt) {
        //기저 조건: N개의 구슬을 뽑으면 종료
        if (cnt == N) {
            //원본 복사
            int[][] copy = new int[H][W];
            for (int i = 0; i < H; i++) {
                copy[i] = arr[i].clone();
            }

            //뽑은 구슬의 열에 있는 가장 위에 있는 벽돌을 제거한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < H; j++) {
                    if (arr[j][result[i]] != 0) {
                        removeBrick(j, result[i]);
                        break;
                    }
                }
                //빈공간 제거해주기
                emptySpace();
            }

            //최대한 많은 벽돌을 제거한 경우를 구한다.
            max = Math.max(max, count);
            //카운트 초기화
            count = 0;

            //배열 원본 복구
            arr = copy;

            return;
        }

        //유도부분: 중복순열
        for (int i = 0; i < W; i++) {
            result[cnt] = i;
            perm(cnt + 1);
        }
    }

    //벽돌 제거 -> bfs구현
    static void removeBrick(int row, int col) {
        //큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        //방문배열 생성
        boolean[][] visited = new boolean[H][W];

        //초기값 큐에 삽입
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        //큐가 비어있지 않을 때까지 반복
        int[] current;
        int r, c, range;
        while (!queue.isEmpty()) {
            current = queue.poll();
            r = current[0];
            c = current[1];

            //벽돌 값 저장
            range = arr[r][c];

            //해당 위치 벽돌 제거 후 제거횟수 세기
            arr[r][c] = 0;
            count++;

            //벽돌 값의 범위만큼 탐색
            for (int i = 1; i < range; i++) {
                dx = new int[]{-i, 0, i, 0};
                dy = new int[]{0, i, 0, -i};

                //사방탐색
                for (int j = 0; j < 4; j++) {
                    if (r + dx[j] >= 0 && r + dx[j] < H && c + dy[j] >= 0 && c + dy[j] < W) {
                        //사방 탐색 후 벽돌인 경우
                        if (!visited[r + dx[j]][c + dy[j]] && arr[r + dx[j]][c + dy[j]] != 0) {
                            queue.offer(new int[]{r + dx[j], c + dy[j]});
                            visited[r + dx[j]][c + dy[j]] = true;
                        }
                    }
                }
            }
        }
    }

    //빈공간 벽돌 채우기
    static void emptySpace() {
        for (int i = H-2; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                if (arr[i][j] != 0) {
                    for (int k = H-1; k > i; k--) {
                        if (arr[k][j] == 0) {
                            arr[k][j] = arr[i][j];
                            arr[i][j] = 0;
                        }
                    }
                }
            }
        }
    }
}
