import java.util.*;

class Solution {
    static String str;
    static int num, max;
    static ArrayList<String>[] subsetList;
    
    public String[] solution(String[] orders, int[] course) {
        
        //orders 문자순서대로 정렬
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<orders.length; i++){
            char[] cArr = orders[i].toCharArray();
            Arrays.sort(cArr);
            
            for(int j=0; j<cArr.length; j++){
                sb.append(cArr[j]);
            }
            
            orders[i] = sb.toString();
            sb.setLength(0);
        }
        
        
        max = course[course.length-1];
        subsetList = new ArrayList[orders.length];
        for(int i=0; i<orders.length; i++){
            subsetList[i] = new ArrayList<>();
        }
        
        //각 주문별 부분집합 생성 -> 길이 2이상 ~ course 마지막 원소 수까지
        for(int i=0; i<orders.length; i++){
            num = i;
            str = orders[i];
            subset(0, "");
            Collections.sort(subsetList[i], new Comparator<String>(){ //길이 내림차순 정렬
                @Override
                public int compare(String s1, String s2){
                    return s2.length() - s1.length();
                }
            });
        }
        
        List<String[]> list = new ArrayList<>();
        int[] count = new int[course[course.length-1]+1]; //코스의 메뉴당 최대 손님 수를 저장할 배열
        //주문별로 같은 요리를 가지고 있는지 판별 후 저장
        for(int i=0; i<subsetList.length; i++){
            for(int j=0; j<subsetList[i].size(); j++){
                int cnt = 1; //손님수
                String current = subsetList[i].get(j);
                for(int k=i+1; k<subsetList.length; k++){
                    if(subsetList[k].contains(current)){ 
                        cnt++;
                    }
                }
                                
                if(cnt >= 2){ //2명 이상의 손님을 가지고 있는지
                    //손님 수와 메뉴를 모두 저장
                    list.add(new String[]{String.valueOf(cnt), current});
                    count[current.length()] = Math.max(count[current.length()], cnt);
                } 
                
                sb.setLength(0);
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<course.length; i++){
            set.add(course[i]);
        }
       
        List<String> ans = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            int num = Integer.parseInt(list.get(i)[0]); //손님수
            String current = list.get(i)[1]; //코스
            if(count[current.length()] == num && set.contains(current.length())){ //코스당 손님수가 최대인 경우와 주어진 메뉴수가 일치하는 경우
                ans.add(current);
            }
        }
        
        Collections.sort(ans);
        
        String[] answer = new String[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
    
    static void subset(int depth, String subStr){
       if(depth == str.length()){ //문자열 길이만큼 도달하였을 때 종료
           if(subStr.length() >= 2 && subStr.length() <= max){ //2개부터 course에서 가장 큰 수까지만 저장
               subsetList[num].add(subStr);
           }
           return;
       }
        
        subset(depth+1, subStr + str.charAt(depth)); 
        subset(depth+1, subStr);
    }
}