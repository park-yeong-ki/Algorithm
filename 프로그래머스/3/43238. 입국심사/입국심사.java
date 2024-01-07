class Solution {
    public long solution(int n, int[] times) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<times.length; i++){
            min = Math.min(min, times[i]);
            max = Math.max(max, times[i]);
        }
        
        long start = min;
        long end = (long)max * n;
        
        long mid, sum;
        while(start < end){
            mid = (start + end) / 2;
            
            sum = 0;
            for(int i=0; i<times.length; i++){
                sum += mid / times[i];
            }
            
            if(sum >= n) end = mid;
            else start = mid + 1;
        }
        
        return end;
    }
}