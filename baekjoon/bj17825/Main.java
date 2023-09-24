package bj17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Horse {
        int position, mNum; //위치와 맵 종류 표기

        public Horse(int position, int mNum) {
            this.position = position;
            this.mNum = mNum;
        }
    }
    static int[] arr;
    static int max;
    static Horse[] horses;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int idx, int sum) {
        if (idx == 10) { //10개의 주사위 값을 통해 이동을 마친 경우
            max = Math.max(max, sum); //최대값 구하기
            return;
        }

        for (int i = 0; i < 4; i++) { //말 4개 뽑기
            int current = horses[i].position;
            int sort = horses[i].mNum; //뽑힌 말의 맵 종류
            if (sort == 5 && current == 1) continue; //이미 도착한 말은 제외

            int next = horses[i].position + arr[idx]; //다음 위치
            int nextSort = sort; //다음 맵 종류
            if (sort == 0) { //원 경로
                if (next == 5){ //1번 맵으로 꺾이는 경우
                    nextSort = 1;
                    next = 0;
                } else if (next == 10) { //2번 맵으로 꺾이는 경우
                    nextSort = 2;
                    next = 0;
                } else if (next == 15){ //3번 맵으로 꺾이는 경우
                    nextSort = 3;
                    next = 0;
                } else if (next >= 20) { //5번 맵으로 꺾이는 경우
                    next = next > 20 ? 1 : 0; // 20보다 크면 도착, 20이면 40점 획득으로 감
                    nextSort = 5;
                }
            } else { //1번 ~ 5번 맵
                if (next >= map[sort].length) { //맵을 바꾸는 경우
                    if (sort == 4) { //4번맵
                        nextSort = 5;
                        next -= map[4].length;
                        if (next >= 1) next = 1;
                    } else if (sort == 5) { // 5번 맵
                        if (next >= 1) next = 1;
                    } else { //1~3번맵
                        next -= map[sort].length;
                        if (next < map[4].length) {
                            nextSort = 4;
                        } else {
                            nextSort = 5;
                            next -= map[4].length;
                            if (next >= 1) next = 1;
                        }
                    }
                }
            }

            if (visited[nextSort][next]) continue; //이동을 마칠 곳에 다른 말이 있는 경우 제외
            visited[sort][current] = false; //출발 위치 방문 해제
            horses[i].position = next; //말 이동
            horses[i].mNum = nextSort; //다음 맵 이동
            if (!(nextSort == 5 && next == 1)) visited[nextSort][next] = true; //도착점이 아니라면 이동할 위치 방문 체크
            dfs(idx + 1, sum + map[nextSort][next]);
            if (!(nextSort == 5 && next == 1)) visited[nextSort][next] = false; //백트래킹으로 이동할 위치 방문 표시 해제
            horses[i].mNum = sort; //이전 맵으로 이동
            horses[i].position = current; //말 원위치
            visited[sort][current] = true; //출발 위치 방문표시
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) { //10개의 주사위 값을 입력
            arr[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE; //최대값 초기화
        horses = new Horse[4]; //말의 위치 배열, 4개의 말
        for (int i = 0; i < 4; i++) {
            horses[i] = new Horse(0, 0);
        }

        map = new int[6][]; //점수가 적힌 맵
        map[0] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38}; // 꺾이지 않는 경우
        map[1] = new int[]{10, 13, 16, 19}; // 5번 위치에서 꺾이는 경우
        map[2] = new int[]{20, 22, 24}; // 10번 위치에서 꺾이는 경우
        map[3] = new int[]{30, 28, 27, 26}; // 15번 위치에서 꺽이는 경우
        map[4] = new int[]{25, 30, 35}; //5,10,15번에서 꺾인 맵들이 모이는 곳
        map[5] = new int[]{40, 0}; //마지막 모이는 경로
        visited = new boolean[6][]; //방문 표시 맵
        visited[0] = new boolean[map[0].length];
        visited[1] = new boolean[map[1].length];
        visited[2] = new boolean[map[2].length];
        visited[3] = new boolean[map[3].length];
        visited[4] = new boolean[map[4].length];
        visited[5] = new boolean[map[5].length];
    }
}
