package bj17135;

import java.io.*;
import java.util.*;

public class Main {
    //전역변수 설정
    static int N;
    static int M;
    static int[][] map;
    static int D;
    static int[] soldier;
    static int cnt;
    static int max = Integer.MIN_VALUE;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //행의 수 N, 열의 수 M, 거리 제한 D 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        //격자판 입력
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //궁수 3명 뽑기
        soldier = new int[3];
        comb(0, 0);

        //제거한 적의 최대 수 출력
        bw.write(max + "\n");
        bw.close();
    }

    //궁수를 배치할 조합
    static void comb(int start, int idx) throws IOException {
        //기저조건 -> 궁수 3명을 뽑으면 종료
        if (idx == 3) {
            //맵 복사
            int[][] copy = new int[N][M];
            for (int i = 0; i < N; i++) {
                copy[i] = map[i].clone();
            }

            //공격 시작
            attack(0);

            //최대값 갱신
            max = Math.max(max, cnt);

            //맵 복구
            map = copy;
            //제거횟수 초기화
            cnt = 0;

            return;
        }

        //유도부분 -> 각 열에 들어갈 궁수를 조합한다.
        for (int i = start; i < M; i++) {
            soldier[idx] = i;
            comb(i + 1, idx + 1);
        }
    }

    //적을 잡는 함수
    static void attack(int game) throws IOException {
        //행의 수만큼 게임을 한 경우 종료
        if (game == N) {
            return;
        }

        //궁수의 좌표는 N-game, soldier[0~2]
        List<int[]> result = new ArrayList<>();
        //궁수의 수만큼 반복
        for (int s = 0; s < 3; s++) {
            //현재 궁수가 공격할 수 있는 적을 저장할 리스트
            List<int[]> list = new ArrayList<>();
            int min = Integer.MAX_VALUE;
            for (int i = N - 1 - game; i >= 0; i--) {
                //전체 열 반복
                for (int j = 0; j < M; j++) {
                    //궁수의 공격 거리 안에 있는 값만 탐색
                    int distance = Math.abs(i - (N - game)) + Math.abs(j - soldier[s]);
                    if (distance <= D) {
                        //적이 있는지 확인
                        if (map[i][j] == 1 && distance <= min) {
                            //적의 위치와 거리를 저장
                            list.add(new int[]{i, j, distance});
                            min = Math.min(min, distance);
                        }
                    }
                }
            }

            //공격거리 내에 적이 존재하여 리스트에 저장된 경우
            if (!list.isEmpty()) {
                //리스트에 저장된 좌표 중 최소거리가 아닌 경우 삭제
                for (int i = list.size()-1; i >= 0; i--) {
                    if (list.get(i)[2] != min) {
                        list.remove(i);
                    }
                }

                //y축이 가장 작은 x, y좌표를 찾는다.
                int x = list.get(0)[0], y = list.get(0)[1];
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i)[1] < y) {
                        y = list.get(i)[1];
                        x = list.get(i)[0];
                    }
                }
                result.add(new int[]{x, y});
            }
        }

        //동시에 적이 죽는다.
        if (!result.isEmpty()) {
            for (int i = 0; i < result.size(); i++) {
                //궁수가 죽인 적이 같은 경우는 1번만 횟수로 설정
                if (map[result.get(i)[0]][result.get(i)[1]] == 1){
                    map[result.get(i)[0]][result.get(i)[1]] = 0;
                    cnt++;
                }
            }
        }

        //다음 공격
        attack(game + 1);
    }
}
