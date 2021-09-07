package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class PG_42583 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
        int truckCnt = truck_weights.length;
        int curWeight = 0;
        int index = 0;
        int answer = 0;
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int iter = 0; iter < bridge_length - 1; iter++) {
            q.offer(0);
        }
        
        q.offer(truck_weights[index]);
        curWeight += truck_weights[index++];
        
        while(!q.isEmpty()) {
            // 트럭이 다 지나가지 않음.
            if(index < truckCnt) {
                curWeight -= q.poll();
                
                // 다리에 트럭이 더 올라갈 수 있다.
                if(curWeight + truck_weights[index] <= weight) {
                    q.offer(truck_weights[index]);
                    curWeight += truck_weights[index++];
                }
                // 다리 추가 트럭이 올라갈 수 없다.
                else {
                    q.offer(0);
                }
            }
            // 모든 트럭이 지나가거나 다리 위에 있음.
            else {
                q.poll();
            }
            
            answer++;
        }
        
        return answer + 1;
    }
}
