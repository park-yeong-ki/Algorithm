import java.util.*;

class Solution {
    static String[][] sArr;
    static String[][] sort = {{"cpp", "java", "python", "-"},
                              {"backend", "frontend", "-"}, 
                              {"junior", "senior", "-"},
                              {"chicken", "pizza", "-"}};
    static Map<String, List<Integer>> map;
    
    public int[] solution(String[] info, String[] query) {
        //문자열 쪼갠 후 저장
        sArr = new String[info.length][5];
        for(int i=0; i<info.length; i++){
            sArr[i] = info[i].split(" ");
        }
        
        map = new HashMap<>();
        for(int i=0; i<sArr.length; i++){ //4*3*3*3의 집합으로 저장
            dfs(0, sArr[i], "");
        }
        
        List<String> key = new ArrayList<>(map.keySet());
        for(String k : key){
            Collections.sort(map.get(k)); //각 집합별 점수 오름차순 정렬
        }
        
        int[] ans = new int[query.length];
        for(int i=0; i<query.length; i++){
            query[i] = query[i].replace(" and ", ""); //and제거
            String[] arr = query[i].split(" "); //집합 키, 점수로 구분
            if(map.containsKey(arr[0])){ //집합이 존재하는 경우
                ans[i] = binarySearch(map.get(arr[0]), Integer.parseInt(arr[1])); //이분탐색으로 같거나 가장 근사하게 큰 점수찾기
            }else{ //존재하지 않는 경우는 사람이 0명
                ans[i] = 0;
            }
        }
        
        return ans;
    }
    
    static int binarySearch(List<Integer> list, int score){
        int start = 0;
        int end = list.size()-1;
        while(start <= end){
            int mid = (start + end) / 2;
            
            if(list.get(mid) < score){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return list.size() - start;
    }
        
    static void dfs(int idx, String[] s, String str){
        if(idx == 4){ //언어, 직군, 경력, 소울푸드 비교하여 집합에 추가
            if(map.containsKey(str)){
                map.get(str).add(Integer.parseInt(s[4])); //점수 추가
            }else{
                map.put(str, new ArrayList<>()); //리스트 생성
                map.get(str).add(Integer.parseInt(s[4])); //점수 추가
            }
            return;
        }
        
        for(int i=0; i<sort[idx].length; i++){
            if(s[idx].equals(sort[idx][i]) || sort[idx][i].equals("-")){ //조건에 부합하는 경우
                dfs(idx+1, s, str+sort[idx][i]);
            }
        }
    }
}