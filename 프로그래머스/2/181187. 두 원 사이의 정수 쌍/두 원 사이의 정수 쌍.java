class Solution {
    public long solution(int r1, int r2) {
        long sum2 = 0;
        for(int i=0; i<r2; i++){ //r2에 맞는 x좌표별 최대 y값으로 좌표 구하기
            int maxY = (int)Math.sqrt(Math.pow(r2,2) - Math.pow(i, 2));
            sum2 += maxY;
        }
        
        long sum1 = 0;
        for(int i=0; i<r1; i++){
            double y = Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2)); 
            if(y == (int)y && y >= 1) y -= 1; //원 위의 점은 제외
            int maxY = (int) y;
            sum1 += maxY;
        }
        
        return (sum2 - sum1) * 4;
    }
}