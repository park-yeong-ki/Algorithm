class Solution {
    public int[] solution(long begin, long end) {
        int[] ans = new int[(int)(end-begin+1)];
        int size = 0;
        for(long i=begin; i<=end; i++){
            if(i == 1) { 
                ans[size++] = 0;
            } else{
                long num = 1; //소수인 경우 기본값
                for(long j=2; j<=Math.sqrt(i); j++){ //소수인지 판별
                    if(i % j == 0){
                        num = i / j; //최대 약수
                        if(num > 10000000) num = j;
                        else break;
                    }
                }
                ans[size++] = (int)num;
            }
        }
        
        return ans;
    }
}