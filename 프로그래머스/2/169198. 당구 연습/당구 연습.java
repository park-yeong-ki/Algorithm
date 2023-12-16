class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] ans = new int[balls.length];
        for(int i=0; i<balls.length; i++){
            int x = balls[i][0];
            int y = balls[i][1];
            int dist = Integer.MAX_VALUE;
            int tX = 0;
            int tY = 0;
            //상 대칭
            if(!(startX == x && startY < y)){
                tX = x;
                tY = y + 2 * (n - y);
                dist = Math.min(dist, (int)(Math.pow(startX - tX, 2) + Math.pow(startY - tY, 2)));
            } 
            
            //하 대칭
            if(!(startX == x && startY > y)){
                tX = x;
                tY = y - 2 * y;
                dist = Math.min(dist, (int)(Math.pow(startX - tX, 2) + Math.pow(startY - tY, 2)));
            }
            
            //좌 대칭
            if(!(startY == y && startX > x)){
                tX = x - 2 * x;
                tY = y;
                dist = Math.min(dist, (int)(Math.pow(startX - tX, 2) + Math.pow(startY - tY, 2)));
            }
            
            //우 대칭
            if(!(startY == y && startX < x)){
                tX = x + 2 * (m - x);
                tY = y;
                dist = Math.min(dist, (int)(Math.pow(startX - tX, 2) + Math.pow(startY - tY, 2)));
            }
            
            ans[i] = dist;
        }
        
        return ans;
    }
    
}