class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        //초기 택배 존재하는 가장 뒤 인덱스 저장
        int dIdx = -1;
        int pIdx = -1;
        for(int i=deliveries.length-1; i>=0; i--){ //배달
            if(deliveries[i] != 0){
                dIdx = i;
                break;
            }
        }
        
        for(int i=pickups.length-1; i>=0; i--){ //수거
            if(pickups[i] != 0){
                pIdx = i;
                break;
            }
        }
        
        long ans = 0;
        while(dIdx >= 0 || pIdx >= 0){ //배달, 수거 택배가 존재할때까지 반복
            //총 이동거리 갱신
            ans += 2*(Math.max(dIdx, pIdx) + 1);
            
            //택배 배달
            int dBox = cap;
            for(int i=dIdx; i>=0; i--){
                int cnt = deliveries[i]; //초기 박스 저장
                
                if(deliveries[i] > dBox){ //트럭 박스보다 많은 경우
                    deliveries[i] -= dBox;
                }else { //트럭 박스보다 같거나 적은 경우
                    deliveries[i] = 0;
                }

                dBox -= cnt;
                if(dBox <= 0 || i == 0){ //트럭 박스를 모두 배달하면 종료
                    int idx = i;
                    while(idx>=0){ //배달 택배가 존재하는 가장 마지막 인덱스 저장
                        if(deliveries[idx] != 0){
                            break;
                        }
                        idx--;
                    }
                    
                    dIdx = idx;
                    break;
                }
            }
            
            //택배 수거
            int pBox = cap;
            for(int i=pIdx; i>=0; i--){
                int cnt = pickups[i]; //초기 박스 저장
                
                if(pickups[i] > pBox){ //트럭 박스보다 많은 경우
                    pickups[i] -= pBox;
                }else { //트럭 박스보다 같거나 적은 경우
                    pickups[i] = 0;
                }
                
                pBox -= cnt;
                if(pBox <= 0 || i == 0){ //트럭 박스를 모두 수거하면 종료
                    int idx = i;
                    while(idx>=0){ //수거 택배가 존재하는 가장 마지막 인덱스 저장
                        if(pickups[idx] != 0){
                            break;
                        }
                        idx--;
                    }
                    
                    pIdx = idx;
                    break;
                }
            }
        }
        
        return ans;
    }
}