import java.util.*;

class Solution {    
    public int[] solution(int n) {
        int[][] map = new int[n][n]; //n*n배열 생성
        
        int[] dr = {1, 0, -1, 0}; //아래쪽, 오른쪽, 위쪽, 왼쪽
        int[] dc = {0, 1, 0, -1};
        
        int d = 0;
        int r = 0;
        int c = 0;
        int idx = n;
        int num = 1;

        while(idx != 0){ //범위가 0이 아닐때까지 반복
            if(idx == n){ //처음 시작만 뒤로 이동해놓음 
                r -= dr[d];
                c -= dc[d];
            }
            
            for(int i=0; i<idx; i++){ //범위만큼 이동
                if(d == 2){ //대각선으로 이동해야되는 경우
                    r += dr[d] + dr[d+1];
                    c += dc[d] + dc[d+1];
                    map[r][c] = num++;
                }else{
                    r += dr[d];
                    c += dc[d];
                    map[r][c] = num++;
                }
            }  
            
            d++; //방향 이동
            d %= 3; //0, 1, 2, 3
            idx--; //범위 1씩 줄이기
        }   
        
        //첫 행부터 마지막 행까지 값이 있는 순서대로 리턴
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<i+1; j++){
                list.add(map[i][j]);
            }
        }
        
        int[] ans = new int[list.size()];
        int size = 0;
        for(int v : list){
            ans[size++] = v;
        }
        
        return ans;
    }
}