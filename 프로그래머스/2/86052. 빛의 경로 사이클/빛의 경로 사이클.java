import java.util.*;

class Solution {
    static char[][] map;
    static int R, C, sR, sC, sD;
    static int[] dr = {1, 0, -1, 0}; //하좌상우 -> 시계방향으로 이동
    static int[] dc = {0, -1, 0, 1};
    static List<Integer> list;
    static boolean[][][] visited;
    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        map = new char[R][C]; //map생성
        for(int i=0; i<R; i++){
            map[i] = grid[i].toCharArray();
        }
        
        visited = new boolean[R][C][4]; //방향 방문표시할 배열
        list = new ArrayList<>(); //길이를 저장할 리스트
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                for(int k=0; k<4; k++){
                    if(visited[i][j][k]) continue; //이미 방문한 경우
                    sR = i;
                    sC = j;
                    sD = k;
                    move(i, j, k); //i,j 좌표에서 사방 탐색 시작
                }
            }
        }
        
        Collections.sort(list); //길이 오름차순 정렬
        int[] ans = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            ans[i] = list.get(i);
        }
        
        return ans;
    }
    
    static int changeDir(int r, int c, int d){
        int tD = d;
        switch (map[r][c]){ //칸에 쓰여진 문자에 따른 방향 조건
            case 'S': //직진
                break;
            case 'L': //좌회전
                tD--;
                if(tD < 0) tD = 3;
                break;
            case 'R': //우회전
                tD++;
                if(tD > 3) tD = 0;
                break;
        }
        
        return tD;
    }
    
    static void move(int r, int c, int d){
        int tD = d;
        int tR = r;
        int tC = c;
        int length = 0;
        
        while(true){
            if(length != 0) tD = changeDir(tR, tC, tD); //처음 진입이 아닌 경우는 나가는 방향으로 변경
            
            if(length != 0 && tR == sR && tC == sC && tD == sD){ //시작과 같은 경우
                list.add(length);
                break; 
            }
            
            if(visited[tR][tC][tD]) break; //이미 방문한 경우
            visited[tR][tC][tD] = true; //방문 표시
            
            tR += dr[tD];
            tC += dc[tD];
            
            if(tR < 0) tR = R-1; //격자의 경계를 넘어가는 경우
            else if(tR >= R) tR = 0;
            else if(tC < 0) tC = C-1;
            else if(tC >= C) tC = 0;
            
            length++;
        } 
        
    }
}