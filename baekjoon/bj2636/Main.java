package bj2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //전역변수 설정
    static class Point{
        int r, c;

        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
    static int row;
    static int col;
    static int[][] arr;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        //판의 가장자리에서 부터 탐색을 시작하여 공기가 아닌 치즈를 만날경우 체크를 한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //사각형 모양 판의 세로와 가로의 길이 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        //판 입력
        arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //치즈가 모두 삭제될때까지 반복
        ArrayList<Integer> cheese = new ArrayList<>();
        int num;
        while ((num = bfs(new Point(0, 0))) != 0) {
            //외곽치즈 삭제
            removeCheese();
            //외곽치즈 개수 저장
            cheese.add(num);
        }



        //결과 출력
        int time = cheese.size();
        System.out.println(time);
        System.out.println(cheese.get(time-1));
    }

    //bfs 구현
    static int bfs(Point point) {
        //큐생성
        Queue<Point> queue = new ArrayDeque<>();

        //방문 배열생성
        boolean[][] visited = new boolean[row][col];

        //초기 입력 큐에 삽입
        queue.offer(point);
        visited[point.r][point.c] = true;

        //큐가 비어있을 때까지 반복
        Point current;
        int count = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();

            //각 좌표에서 4방탐색을 하며 치즈의 가장 외곽자리를 확인
            for (int i = 0; i < 4; i++) {
                if (current.r + dr[i]>=0 && current.r + dr[i] < row && current.c + dc[i] >= 0 && current.c + dc[i] < col) {
                    if (!visited[current.r + dr[i]][current.c + dc[i]]) {
                        //공기인 경우 큐에 삽입 후 방문 체크
                        if (arr[current.r + dr[i]][current.c + dc[i]] == 0) {
                            queue.offer(new Point(current.r + dr[i], current.c + dc[i]));
                            visited[current.r + dr[i]][current.c + dc[i]] = true;
                        }
                        //치즈를 만나는 경우 -> 외곽 치즈인 것을 표시
                        else if (arr[current.r + dr[i]][current.c + dc[i]] == 1){
                            arr[current.r + dr[i]][current.c + dc[i]] = 2;
                            count++;
                        }
                    }
                }
            }
        }

        //외곽 치즈 개수 반환
        return count;
    }

    //외곽 치즈 삭제
    static void removeCheese() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 2) {
                    arr[i][j] = 0;
                }
            }
        }
    }
}