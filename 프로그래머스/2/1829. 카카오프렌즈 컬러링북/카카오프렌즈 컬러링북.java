import java.util.*;

class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visited;
    static int max, M, N;
    static class Point{
        int r, c;
        
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        map = picture;
        M = m;
        N = n;
        
        visited = new boolean[m][n];
        int cnt = 0;
        max = Integer.MIN_VALUE;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == 0 || visited[i][j]) continue; //색칠하지 않은 경우와 이미 방문한 경우
                bfs(new Point(i, j));
                cnt++;
            }
        }
                
        return new int[]{cnt, max};
    }
    
    static void bfs(Point start){
        Queue<Point> queue = new ArrayDeque<>();
        
        queue.add(start);
        visited[start.r][start.c] = true;
        
        Point current;
        int num = 0; //영역 개수 체크
        while(!queue.isEmpty()){
            current = queue.poll();
            num++;
            
            for(int i=0; i<4; i++){
                int tR = current.r + dr[i];
                int tC = current.c + dc[i];
                
                if(tR < 0 || tR >= M || tC < 0 || tC >= N) continue; //경계 벗어나는 경우
                if(map[tR][tC] != map[current.r][current.c]) continue; //영역이 다른 경우
                if(visited[tR][tC]) continue; //방문한 경우
                
                queue.add(new Point(tR, tC));
                visited[tR][tC] = true;
            }
            
        }
        
        max = Math.max(max, num);
    }
}