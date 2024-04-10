class Solution {
    public int solution(int[] money) {
        int len = money.length;
        int[][] dp = new int[2][len];
        dp[0][0] = money[0];
        dp[0][1] = Math.max(money[0], money[1]);
        dp[1][0] = 0;
        dp[1][1] = money[1];
        for(int i=2; i<len; i++){
            if(i < len-1){
                dp[0][i] = Math.max(dp[0][i-1], dp[0][i-2] + money[i]);
            }
            dp[1][i] = Math.max(dp[1][i-1], dp[1][i-2] + money[i]);
        }
        
        return Math.max(dp[0][len-2], dp[1][len-1]);
    }
}