import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int start, end;
        
        List<Integer> blank = new ArrayList<>(); 
        int prev = 1;
        for(int i=0; i<stations.length; i++){
            start = stations[i] - w;
            end = stations[i] + w;
            
            if(start < 1) start = 1;
            if(end > n) end = n;
            
            if(prev < start){
                blank.add(start - prev);   
            }
            
            prev = end + 1;
            if(prev > n) break;
        }
        
        if(prev <= n) blank.add(n+1 - prev);
        
        int ans = 0;
        int range = 2 * w + 1;
        for(int num : blank){
            ans += num / range;
            if(num % range != 0) ans++;
        }
        
        return ans;
    }
}