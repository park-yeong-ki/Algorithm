package swea2382;

import java.io.*;
import java.util.*;

public class Solution {
    static class Point{
        int r,c, cnt, d;
        public Point(int r, int c, int cnt, int d) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.d = d;
        }
    }
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                points.add(new Point(r, c, cnt, d));
            }

            for (int time = 0; time < M; time++) {
                Collections.sort(points, new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {
                        return Integer.compare(o1.cnt, o2.cnt);
                    }
                });
                for (int i = 0; i < points.size(); i++) {
                    points.get(i).r += dr[points.get(i).d];
                    points.get(i).c += dc[points.get(i).d];

                    int tR = points.get(i).r;
                    int tC = points.get(i).c;

                    //미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다.
                    if (tR == 0 || tR == N - 1 || tC == 0 || tC == N - 1) {
                        points.get(i).cnt /= 2;
                        if (points.get(i).d % 2 == 1) {
                            points.get(i).d += 1;
                        } else {
                            points.get(i).d -= 1;
                        }
                    }

                    //이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.
                    for (int j = 0; j < i; j++) {
                        if (points.get(j).r == tR && points.get(j).c == tC) {
                            points.get(i).cnt += points.get(j).cnt;
                            points.get(j).cnt = 0;
                        }
                    }
                }

                //군집에 미생물이 한 마리 있는 경우 살아남은 미생물 수가 0이 되기 때문에, 군집이 사라지게 된다,
                int p = points.size();
                for (int i = p-1; i >= 0; i--) {
                    if (points.get(i).cnt == 0) {
                        points.remove(i);
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < points.size(); i++) {
                result += points.get(i).cnt;
            }

            bw.write("#" + test_case + " " + result + "\n");
        }
        bw.close();
    }
}
