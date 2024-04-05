class Solution {
    public int solution(int[][] triangle) {
        int len = triangle.length;
        int[][] dp = new int[len][len+1];
        for(int i=0; i<len; i++){
            for(int j=0; j<triangle[i].length; j++){
                dp[i][j+1] = triangle[i][j];
            }
        }
        
        for(int i=1; i<len; i++){
            for(int j=1; j<=len; j++){
                dp[i][j] += Math.max(dp[i-1][j-1], dp[i-1][j]);
            }
        }
        
        int ans = 0;
        for(int j=1; j<=len; j++){
            ans = Math.max(ans, dp[len-1][j]);
        }
        
        return ans;
    }
}