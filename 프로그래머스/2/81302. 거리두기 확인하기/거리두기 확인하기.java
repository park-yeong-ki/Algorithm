import java.util.*;

class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static char[][][] map;
    static class Point{
        int r, c;
        
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static ArrayList<Point>[] pointList;
    static boolean flag;
    
    public int[] solution(String[][] places) {
        map = new char[5][5][5];
        pointList = new ArrayList[5];
        for(int i=0; i<5; i++){
            pointList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<places.length; i++){
            for(int j=0; j<places[i].length; j++){
                for(int k=0; k<places[i][j].length(); k++){
                    map[i][j][k] = places[i][j].charAt(k);
                    if(map[i][j][k] == 'P'){
                        pointList[i].add(new Point(j, k));
                    }
                }
            }
        }
        
        int[] ans = new int[5];
        for(int i=0; i<pointList.length; i++){
            flag = false;
            for(int j=0; j<pointList[i].size(); j++){
                bfs(i, pointList[i].get(j));
                if(flag) break;
            }
            if(!flag) ans[i] = 1;
        }
        
        return ans;
    }
    
    static void bfs(int idx, Point start){
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        
        queue.add(start);
        visited[start.r][start.c] = true;
        
        int size;
        int time = 0;
        Point current;
        while(!queue.isEmpty() && time <= 2){
            size = queue.size();
            
            for(int i=0; i<size; i++){
                current = queue.poll();
                
                if(time != 0 && map[idx][current.r][current.c] == 'P'){ //거리두기 실패
                    flag = true;
                    return;
                }
                
                for(int j=0; j<4; j++){
                    int tR = current.r + dr[j];
                    int tC = current.c + dc[j];
                    
                    if(tR < 0 || tR >= 5 || tC < 0 || tC >= 5) continue; //경계초과
                    if(map[idx][tR][tC] == 'X') continue; //파티션인 경우
                    if(visited[tR][tC]) continue; //방문한 곳인 경우
                    
                    queue.add(new Point(tR, tC));
                    visited[tR][tC] = true;
                }
            }
            
            time++;
        }
        
        return;
    }
}