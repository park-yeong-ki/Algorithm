import java.util.*;

class Solution {
    static class Robot{
        int r, c, d;
        
        Robot(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
        
        @Override
        public String toString(){
            return "r: " + this.r + " c: " + this.c + " d: " + this.d;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N;
    static int[][] map;
    static int[] dd = {1,-1,-1,1};
    
    public int solution(int[][] board) {
        N = board.length;
        map = board;
        
        Robot robot = new Robot(0,0,1);
        return bfs(robot); 
    }
    
    static int bfs(Robot start){        
        Queue<Robot> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][4];
        
        queue.add(start);
        visited[start.r][start.c][start.d] = true;
        
        Robot current;
        int size;
        int time = 0;
        int r1, c1, r2, c2;
        while(!queue.isEmpty()){
            size = queue.size();
            
            for(int i=0; i<size; i++){
                current = queue.poll();
                r1 = current.r;
                c1 = current.c;
                r2 = current.r + dr[current.d];
                c2 = current.c + dc[current.d];
                
                if((r1 == N-1 && c1 == N-1) ||
                  (r2 == N-1 && c2 == N-1)){
                    return time;
                }
                
                for(int j=0; j<4; j++){
                    int nr1 = r1 + dr[j];
                    int nc1 = c1 + dc[j];
                    int nr2 = r2 + dr[j];
                    int nc2 = c2 + dc[j];
                    
                    if(!borderCheck(nr1, nc1)) continue;
                    if(!borderCheck(nr2, nc2)) continue;
                    if(map[nr1][nc1] == 1) continue;
                    if(map[nr2][nc2] == 1) continue;
                    if(visited[nr1][nc1][current.d]) continue;
                    
                    queue.add(new Robot(nr1, nc1, current.d));
                    visited[nr1][nc1][current.d] = true;
                }
                
                for(int j=0; j<4; j++){
                    int nd = current.d + dd[j];
                    if(nd == 4) nd = 0;
                    else if(nd == -1) nd = 3;
                    
                    int nr1 = r1 + dr[nd];
                    int nc1 = c1 + dc[nd];
                    int nr2 = r2 + dr[nd];
                    int nc2 = c2 + dc[nd];
                    
                    if(!borderCheck(nr1, nc1)) continue;
                    if(!borderCheck(nr2, nc2)) continue;
                    if(map[nr1][nc1] == 1) continue;
                    if(map[nr2][nc2] == 1) continue;
                    
                    if(j < 2){
                        if(visited[r1][c1][nd]) continue;
                        queue.add(new Robot(r1, c1, nd));
                        visited[r1][c1][nd] = true;
                    }else{
                        nd = (nd+2) % 4;
                        if(visited[nr2][nc2][nd]) continue;
                        queue.add(new Robot(nr2, nc2, nd));
                        visited[nr2][nc2][nd] = true;
                    }
                    
                }
            }
            
            time++;
        }
        
        return -1;
    }
    
    static boolean borderCheck(int r, int c){
        if(r < 0 || r >= N || c < 0 || c >= N) return false;
        else return true;
    }
}