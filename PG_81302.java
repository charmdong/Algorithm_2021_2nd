import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PG_81302 {
	public static final int CNT = 5;
    public static final int R = 5;
    public static final int C = 5;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[CNT];
        
        for(int index = 0; index < CNT; index++) {
            String[] place = places[index];
            List<int[]> posList = getPosition(place);
            
            boolean flag = true;
            for(int[] pos: posList) {
                if(!bfs(place, pos)) {
                    flag = false;
                    break;
                }
            }
            
            answer[index] = flag ? 1 : 0; 
        }
        
        return answer;
    }
    
    public static boolean bfs(String[] place, int[] start) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        
        q.offer(start);
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(place[cur[0]].charAt(cur[1]) == 'P') {
                if(cur[0] != start[0] || cur[1] != start[1]) {
                    if(dist[cur[0]][cur[1]] <= 2) return false; 
                }
            }
            
            for(int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                
                if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                if(visited[ny][nx]) continue;
                if(place[ny].charAt(nx) == 'X') continue;
                
                visited[ny][nx] = true;
                dist[ny][nx] = dist[cur[0]][cur[1]] + 1;
                q.offer(new int[] {ny, nx});
            }
        }
        
        return true;
    }
    
    public static List<int[]> getPosition(String[] place) {
        List<int[]> posList = new ArrayList<>();
        
        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++) {
                if(place[row].charAt(col) == 'P') {
                    posList.add(new int[] {row, col});
                }
            }
        }
        
        return posList;
    }
}
