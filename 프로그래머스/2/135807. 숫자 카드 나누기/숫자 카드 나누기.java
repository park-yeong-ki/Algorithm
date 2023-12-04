class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int numA = arrayA[0];
        for(int i=1; i<arrayA.length; i++){
            numA = GCD(numA, arrayA[i]);
            if(numA == 1) break; //최대공약수가 없는 경우
        }
        
        int numB = arrayB[0];
        for(int i=1; i<arrayB.length; i++){
            numB = GCD(numB, arrayB[i]);
            if(numB == 1) break; //최대공약수가 없는 경우
        }
        
        for(int i=0; i<arrayB.length; i++){
            if(arrayB[i] % numA == 0){ //만약 상대 배열에서 나눠지는 경우
                numA = 0;
                break;
            }
        }
        
        for(int i=0; i<arrayA.length; i++){
            if(arrayA[i] % numB == 0){ //만약 상대 배열에서 나눠지는 경우
                numB = 0;
                break;
            }
        }
        
        return Math.max(numA, numB); //가장 큰 값 반환
    }
    
    static int GCD(int a, int b){ //유클리드 호재법 logn 시간복잡도
        if(a % b == 0){
            return b;
        }
        return GCD(b, a%b);
    }
}