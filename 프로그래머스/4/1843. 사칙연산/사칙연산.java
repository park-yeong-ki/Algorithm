class Solution {
    static int[][][] memo;
    static String[] sArr;
    public int solution(String arr[]) {
        int len = arr.length;
        memo = new int[len][len][2];        
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                memo[i][j][0] = Integer.MIN_VALUE;
                memo[i][j][1] = Integer.MAX_VALUE;
            }
            if(i % 2 == 0){
                memo[i][i][0] = Integer.parseInt(arr[i]);
                memo[i][i][1] = Integer.parseInt(arr[i]);
            }
        }
        sArr = arr;
    
        return recursion(0, len-1)[0];
    }
    
    static int[] recursion(int start, int end){
        if(memo[start][end][0] != Integer.MIN_VALUE && memo[start][end][1] != Integer.MAX_VALUE){
            return memo[start][end];
        }
        
        for(int i=start; i<=end; i++){
            if(i % 2 == 1){
                int[] part1 = recursion(start, i-1);
                int[] part2 = recursion(i+1, end);
                
                if(sArr[i].equals("-")){
                    memo[start][end][0] = Math.max(memo[start][end][0], part1[0] - part2[1]);
                    memo[start][end][1] = Math.min(memo[start][end][1], part1[1] - part2[0]);
                }else if(sArr[i].equals("+")){
                    memo[start][end][0] = Math.max(memo[start][end][0], part1[0] + part2[0]);
                    memo[start][end][1] = Math.min(memo[start][end][1], part1[1] + part2[1]);
                }
            }
        }
        
        return memo[start][end];
    }
}