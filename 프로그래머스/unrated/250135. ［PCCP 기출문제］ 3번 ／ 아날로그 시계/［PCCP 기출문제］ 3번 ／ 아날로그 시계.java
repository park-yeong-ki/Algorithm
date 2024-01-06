class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;
        
        int ans = 0;
        int s, m, h, currentS, currentM, currentH;
        int prevS = 1, prevM = 0, prevH = 0;
        while(start <= end){
            s = start % 60;
            m = start % 3600;
            h = start % 43200;
            
            currentS = s * 720;
            currentM = m * 12;
            currentH = h;
            
            if(s == 0){
                currentS = 43200;
                if(m == 0){
                    currentM = 43200;
                    if(h == 0){
                        currentH = 43200;
                    }
                }
            }
            
            if(currentS == currentM) ans++;
            if(currentS == currentH) ans++;
            if(currentS == currentM && currentS == currentH) ans--;
            
            if(prevS < prevM && currentS > currentM) ans++;
            if(prevS < prevH && currentS > currentH) ans++;
            
            prevS = s * 720;
            prevM = m * 12;
            prevH = h;
            
            start++;
        }
        
        return ans;
    }
}