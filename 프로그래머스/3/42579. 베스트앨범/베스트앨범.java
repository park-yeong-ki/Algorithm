import java.util.*;

class Solution {
    static class Music implements Comparable<Music>{
        int n, play;
        
        Music(int n, int play){
            this.n = n;
            this.play = play;
        }
        
        @Override
        public int compareTo(Music m){
            if(m.play == this.play){ //재생횟수가 같은 경우
                return Integer.compare(this.n, m.n); //고유번호 오름차순 정렬    
            }
            return Integer.compare(m.play, this.play); //재생수 내림차순 정렬
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> g = new HashMap<>(); //장르별 재생횟수 저장
        for(int i=0; i<genres.length; i++){ 
            if(g.containsKey(genres[i])){
                g.put(genres[i], g.get(genres[i]) + plays[i]);
            }else{
                g.put(genres[i], plays[i]);
            }
        }
        
        Map<Integer, PriorityQueue<Music>> map = new HashMap();
        for(int i=0; i<genres.length; i++){
            int num = g.get(genres[i]); //장르를 재생횟수로 변환 -> 장르별 재생된 횟수는 다름
            Music music = new Music(i, plays[i]); //노래 객체 생성
            if(map.containsKey(num)){ //이미 저장해놓은 장르인 경우
                map.get(num).add(music); //pq에 추가
            }else{ //아닌 경우
                map.put(num, new PriorityQueue<Music>()); //pq생성 후 추가
                map.get(num).add(music);
            }
        }
        
        List<Integer> list = new ArrayList<>(map.keySet()); //장르별 재생횟수 내림차순 정렬
        Collections.sort(list, Collections.reverseOrder());
        
        List<Integer> bestAlbum = new ArrayList(); //베스트앨범
        for(int i=0; i<list.size(); i++){
            int idx = Math.min(2, map.get(list.get(i)).size()); //pq사이즈랑 2의 최소값만큼 반복
            for(int j=0; j<idx; j++){
                bestAlbum.add(map.get(list.get(i)).poll().n);
            }
        }
        
        int[] ans = new int[bestAlbum.size()];
        for(int i=0; i<bestAlbum.size(); i++){
            ans[i] = bestAlbum.get(i);
        }
        
        return ans;
    }
}