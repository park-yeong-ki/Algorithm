import java.util.*;

class Solution {
    static List<Integer> list;
    public double[] solution(int k, int[][] ranges) {
        list = new ArrayList<>(); //리스트에 x,y 좌표 저장
        collatz(k);
        int n = list.size() - 1; //k가 1이 될때까지의 횟수
        
        double[] arr = new double[list.size()]; //좌표 개수만큼 배열 생성
        for(int i=1; i<list.size(); i++){ //구간별 넓이를 누적합으로 저장하기
            int y1 = list.get(i-1); //이전 좌표
            int y2 = list.get(i); //현재 좌표
            
            int min = Math.min(y1, y2);
            int max = Math.max(y1, y2);
            
            double space = min * 1; //아래 사각형 구하기
            space += (max - min) * 1 / (double)2; // 위 삼각형 구하기
            arr[i] = arr[i-1]; //이전값을 누적하여 저장
            arr[i] += space;
        }
        
        double[] ans = new double[ranges.length]; 
        for(int i=0; i<ranges.length; i++){
            int a = ranges[i][0];
            int b = ranges[i][1];
            
            if(n+b >= a){
                ans[i] = arr[n+b] - arr[a];
            }else{ //시작점이 끝점보다 커서 유효하지 않은 구간
                ans[i] = -1;
            }
        }
        
        return ans;
    }
    
    
    static void collatz(int y){ //각 좌표 구하기
        int num = y;
        list.add(num);
        if(num % 2 == 0){ //짝수인 경우
            num /= 2;
        }else{ //홀수인 경우
            num *= 3;
            num += 1;
        }
        
        if(num > 1) collatz(num);
        else list.add(num);
    }
}