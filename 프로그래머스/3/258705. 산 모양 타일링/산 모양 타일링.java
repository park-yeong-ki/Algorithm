class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = tops[0] == 1 ? 3 : 2;
        
        int mod = 10007;
        for(int i=1; i<tops.length; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % mod;
            dp[i][1] = (dp[i-1][0] * (1+tops[i]) + dp[i-1][1] * (2+tops[i])) % mod;
        }
        
        return (dp[n-1][0] + dp[n-1][1]) % mod;
    }
}