import java.util.*;

class Solution {
    static int[] arr, apeach, ans;
    static int max;
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        arr = new int[11]; //10 ~ 0 까지의 점수, 길이 11짜리 배열
        max = Integer.MIN_VALUE;
        ans = new int[]{-1};
        dfs(0,n); //화살의 분포 순열
        return ans;
    }
    
    static void dfs(int idx, int cnt){
        if(idx == arr.length-1){ //10개 뽑으면 종료, 마지막 과녁에는 남은 모든 화살 개수
            arr[idx] = cnt;
            //점수 계산
            int a = 0; //어피치
            int r = 0; //라이언
            for(int i=0; i<arr.length; i++){
                if(apeach[i] == 0 && arr[i] == 0){ //둘다 0인 경우 점수획득x
                    continue;
                } else if(apeach[i] < arr[i]){ //라이언이 이긴 경우
                    r += 10-i;
                } else{ //어피치가 이긴 경우
                    a += 10-i;
                }
            }
            
            if(a < r){ //라이언이 최종 승리한 경우
                int num = r - a;
                if(max < num){ 
                    max = num;
                    ans = arr.clone(); //정답 복사
                }else if(max == num){ //같은 점수라면 가장 낮은 점수를 가장 많이 맞혀야함
                    for(int i=10; i>=0; i--){
                        if(ans[i] < arr[i]){
                            ans = arr.clone();
                            break;
                        }else if(ans[i] > arr[i]){
                            break;
                        }
                    }
                }
            }
            
            return;
        }
        
        for(int i=0; i<=cnt; i++){ //남은 화살 개수를 고려해서 배열에 저장
            arr[idx] = i; //idx점수에 i발 획득
            dfs(idx+1, cnt-i);
        }
    }
}