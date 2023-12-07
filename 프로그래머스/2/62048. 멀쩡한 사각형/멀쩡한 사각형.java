class Solution {
    public long solution(int w, int h) {        
        long ans = 0;
        for(int i=0; i<w; i++){
            int start = (int)Math.floor((double)i * h / w);
            int end = (int)Math.ceil((double)(i+1) * h / w);
            ans += end - start;
        }
        
        return (long)w*h - ans;
    }
}