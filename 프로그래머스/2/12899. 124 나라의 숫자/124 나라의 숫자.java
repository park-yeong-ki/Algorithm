class Solution {
    public String solution(int n) {
        int[] arr = {1,2,4};
        int num = n;
        
        StringBuilder sb = new StringBuilder();
        while(num != 0){ //num이 0일때까지 반복
            num -= 1; // 0,1,2로 맞추기 위해 -1을 빼준다.
            int mod = num % 3;
            sb.append(arr[mod]);
            num /= 3;
        }
        
        return sb.reverse().toString();
    }
}