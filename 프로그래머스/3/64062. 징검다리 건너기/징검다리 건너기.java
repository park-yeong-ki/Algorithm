import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int[] arr = stones.clone();
        Arrays.sort(arr);
        
        int start = 0;
        int end = stones.length-1;
        int mid, cnt;
        while(start < end){
            mid = (start + end) / 2;
            
            cnt = 0;
            for(int i=0; i<stones.length; i++){
                if(stones[i] - arr[mid] <= 0) cnt++;
                else cnt = 0;

                if(cnt >= k) break;
            }
            
            if(cnt < k) start = mid + 1;
            else end = mid;
        }
        
        return arr[end];
    }
}