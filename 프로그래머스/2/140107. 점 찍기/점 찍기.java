class Solution {
    public long solution(int k, int d) {
        long ans = 0;
        for(int i=0; i<=d; i+=k){ //x좌표를 기준으로 최대 y거리를 구한다.
            int y = (int)Math.sqrt(Math.pow(d, 2) - Math.pow(i, 2));
            ans += y / k + 1; //구한 y좌표를 k로 나누고 0을 포함해야하므로 1을 더해준다.
        }
        
        return ans;
    }
}