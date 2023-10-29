import java.util.*;

class Solution {
    static int[] sale = {10, 20, 30, 40}; //지정된 할인율
    static int[] pArr, price;
    static int m, mPlus, mSum;
    static int[][] arr;
    
    public int[] solution(int[][] users, int[] emoticons) {
        arr = users;
        price = emoticons;
        m = emoticons.length;
        mPlus = 0;
        mSum = 0;
        //m개의 이모티콘별 할인율 중복순열 생성
        pArr = new int[m];
        perm(0);
        
        return new int[]{mPlus, mSum};
    }
    
    static void perm(int idx){
        if(idx == m){            
            int pCnt = 0; //플러스 가입자수
            int tSum = 0; //총 구매 가격
            //중복순열별 플러스 서비스 가입자수와 이모티콘 판매액수 확인
            for(int i=0; i<arr.length; i++){
                int sum = 0; //구매한 이모티콘 액수
                for(int j=0; j<pArr.length; j++){
                    if(arr[i][0] <= pArr[j]){ //회원이 이모티콘을 구매하는 경우
                        sum += price[j] * ((100 - pArr[j]) / (double)100);
                    }
                }
                
                if(sum >= arr[i][1]){ //구매비용이 일정가격 이상이 되어 이모티콘 플러스를 가입하는 경우
                    pCnt++;
                }else{ //구매하는 경우
                    tSum += sum;
                }
            }
                        
            if(mPlus < pCnt){ //이모티콘 플러스 가입자수와 구매액수 모두 갱신되는 경우
                mPlus = pCnt;
                mSum = tSum;
            }else if(mPlus == pCnt){ //구매액수만 갱신되는 경우
                if(mSum < tSum){ 
                    mSum = tSum;
                }
            }
                        
            return;
        }
        
        for(int i=0; i<sale.length; i++){
            pArr[idx] = sale[i];
            perm(idx+1);
        }
    }
}