import java.util.*;

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][] map = new int[m+1][n+1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                map[i][j] = cityMap[i-1][j-1];
            }
        }
        
        int[][][] dp = new int[m+1][n+1][2];
        dp[1][1][0] = 1;
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if((i == 1 && j == 1) || map[i][j] == 1) continue;
                dp[i][j][0] = dp[i][j-1][0];
                dp[i][j][1] = dp[i-1][j][1];
                if(map[i][j-1] != 2) dp[i][j][0] += dp[i][j-1][1];
                if(map[i-1][j] != 2) dp[i][j][1] += dp[i-1][j][0];
                dp[i][j][0] %= MOD;
                dp[i][j][1] %= MOD;
            }
        }
        
        return (dp[m][n][0] + dp[m][n][1]) % MOD;
    }
}