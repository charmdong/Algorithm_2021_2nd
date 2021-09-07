package Programmers;

import java.util.Arrays;

public class PG_42885 {
	public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int light = 0;
        int heavy = people.length - 1;
        
        while(light < heavy) {
            int sum = people[light] + people[heavy];
            
            if(sum <= limit) {
                light++;
                heavy--;
            }
            else {
                heavy--;
            }
            answer++;
            
            if(light == heavy) answer++;
        }
        
        return answer;
    }
}
