import java.util.*;

class Solution {
    static List<int[]> list;
    public int[][] solution(int n) {
        list = new ArrayList<>();
        
        recursion(n, 1, 2, 3);
        
        int[][] ans = new int[list.size()][2];
        for(int i=0; i<ans.length; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
    
    static void recursion(int n, int start, int mid, int end){
        if(n == 0){ //원판을 모두 이동한 경우
            return;
        }
        
        recursion(n-1, start, end, mid); //n-1개의 원판을 경유지로 이동
        
        list.add(new int[]{start, end}); //n번째 원판 이동
        
        recursion(n-1, mid, start, end); //경유지로 이동한 n-1개의 원판을 원래 목적지로 이동 
    }
}