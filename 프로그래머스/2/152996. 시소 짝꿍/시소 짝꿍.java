import java.util.*;

class Solution {
    public long solution(int[] weights) {
        Map<Integer, Integer> map = new HashMap<>(); //숫자의 범위를 사용하여 시간복잡도를 줄인다.
        for(int i=0; i<weights.length; i++){
            if(map.containsKey(weights[i])){ //이미 압축된 숫자
                map.put(weights[i], map.get(weights[i]) + 1); //압축
            }else{ //압축되지 않은 숫자인 경우
                map.put(weights[i], 1);
            }
        }
        
        long ans = 0;
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        for(int key : keys){ //압축된 숫자 내에서 시소 짝꿍을 구한다.
            int num = map.get(key);
            if(num > 1){
                ans += ((long)num * (num-1)) / (2 * 1); //numC2 -> 2개를 조합
            }
        }
        
        int[] arr = {2,3,4};
        for(int i=0; i<keys.size(); i++){ //압축된 숫자끼리 2개를 조합
            for(int j=i+1; j<keys.size(); j++){
                outer:
                for(int m=0; m<arr.length; m++){ // 거리를 옮겨가며 시소 짝꿍인지 확인
                    for(int n=0; n<arr.length; n++){
                        if(keys.get(i) * arr[m] == keys.get(j) * arr[n]){
                            ans += (long)map.get(keys.get(i)) * map.get(keys.get(j)); //각 숫자별 압축한 수만큼 곱하여 갱신
                            break outer; // 시소 짝꿍이라면 정답 갱신 후 반복문 종료
                        }
                    }
                }
            }
        }
        
        return ans;
    }
}