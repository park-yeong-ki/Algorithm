class Solution {
    static int[][] map;
    static int[] ans;
    static int size;
    static int[] dr = {0, 1, 0, -1}; //우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        ans = new int[queries.length]; //쿼리의 개수만큼 정답 배열 크기 설정
        size = 0;
        
        for(int i=0; i<rows; i++){ //지도 입력
            for(int j=0; j<columns; j++){
                map[i][j] = (i) * columns + j+1; // i, j는 0, 0부터 시작이므로 주어진 식에서 1씩 각각 더해줌
            }
        }
        
        for(int i=0; i<queries.length; i++){ //쿼리 실행
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            
            rotate(x1, y1, x2, y2); //시계방향으로 한 칸씩 회전
        }
        
        return ans;
    }
    
    static void rotate(int x1, int y1, int x2, int y2){
        int min = Integer.MAX_VALUE; //회전하는 칸 중 가장 작은 값 구하기
        
        int d = 0;
        int tR = x1;
        int tC = y1;
        int prev = map[tR][tC]; //이전 위치 저장
        while(true){ //우, 하, 좌, 상 순으로 한 칸씩 시계방향으로 회전
            
            tR += dr[d]; //회전 방향 만큼 이동
            tC += dc[d];
            
            if(tR < x1 || tR > x2 || tC < y1 || tC > y2){ //경계를 초과하는 경우
                tR -= dr[d]; //원위치로 이동
                tC -= dc[d];
                d++; //방향을 바꿔줌
                
                if(d == 4){ //한 바퀴를 모두 돌면 종료
                    ans[size++] = min; //최소값 저장 
                    return;
                }
                
                continue; //통과
            }
            
            int current = map[tR][tC]; //현재 위치에 있는 값 저장
            
            map[tR][tC] = prev; //이전 위치의 값을 현재 위치로 이동
            min = Math.min(min, prev); //회전한 칸 중 최소값을 갱신
            
            prev = current; //바뀌기 전의 값을 이전 위치 값으로 저장
        }
               
    }
}