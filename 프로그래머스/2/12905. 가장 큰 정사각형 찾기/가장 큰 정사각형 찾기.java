class Solution
{
    public int solution(int [][]board)
    {
        int ans = 0;
        for(int i=0; i<board.length; i++){ 
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == 0) continue; //0인 부분은 통과
                if(i-1 < 0 || j-1 < 0) board[i][j] = 1; //외곽인 경우
                else board[i][j] = Math.min(board[i-1][j-1], Math.min(board[i-1][j], board[i][j-1])) + 1;
                ans = Math.max(ans, board[i][j]); //정사각형 한 변의 최대값 갱신
            }
        }
        
        return ans*ans;
    }
}