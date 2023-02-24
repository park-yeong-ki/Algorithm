package bj2667;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static List<Integer> complex = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //지도의 크기 입력
        int N = Integer.parseInt(br.readLine());

        //지도 생성
        map = new int[N+2][N+2];
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j-1));
            }
        }

        //bfs사용
        visited = new boolean[N+2][N+2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) bfs(i, j);
            }
        }

        //오름차순 정렬 후 출력
        Collections.sort(complex);
        bw.write(complex.size() + "\n");
        for (int i = 0; i < complex.size(); i++) {
            bw.write(complex.get(i) + "\n");
        }
        bw.close();
    }

    static void bfs(int x, int y) {
        //큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        //입력받은 위치를 큐에 넣는다.
        queue.offer(new int[]{x, y});
        //방문 배열에 추가
        visited[x][y] = true;

        //큐가 비어있을 때까지 반복하여 단지를 찾는다.
        int count = 0;
        int r = 0;
        int c = 0;
        int[] current = new int[2];
        while(!queue.isEmpty()) {
            //큐의 가장 처음값을 꺼낸다
            current = queue.poll();
            //집의 개수를 센다
            count++;

            r = current[0];
            c = current[1];

            //상하좌우를 탐색한 후 방문하지 않은 집이 연결되어있으면 큐에 추가한다.
            for (int i = 0; i < 4; i++) {
                if (map[r + dx[i]][c + dy[i]] == 1 && !visited[r + dx[i]][c + dy[i]]) {
                    queue.offer(new int[]{r + dx[i], c + dy[i]});
                    //방문 배열에 체크
                    visited[r + dx[i]][c + dy[i]] = true;
                }
            }
        }

        //단지 리스트에 추가
        complex.add(count);
    }
}