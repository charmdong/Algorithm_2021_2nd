import java.util.*;

class Solution {
    
    public static Map<String, Integer> dict = new HashMap<>();
    public static int dictIndex = 26;
    
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        
        initDictionary();
        
        int msgLen = msg.length();
        for(int index = 0; index < msgLen; index++) {
            boolean flag = true;
            for(int endIndex = index + 1; endIndex <= msgLen; endIndex++) {
                String curStr = msg.substring(index, endIndex);
                
                if(!dict.containsKey(curStr)) {
                    String longWord = msg.substring(index, endIndex - 1);
                    index = endIndex - 2;
                    
                    dict.put(curStr, ++dictIndex);
                    answer.add(dict.get(longWord));
                    
                    break;
                }
                
                if(endIndex == msgLen) {
                    answer.add(dict.get(curStr));
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
        }
        
        int[] answerArray = new int[answer.size()];
        for(int index = 0; index < answer.size(); index++) {
            answerArray[index] = answer.get(index);
        }
        
        return answerArray;
    }
    
    public static void initDictionary() {
        for(int index = 0; index < 26; index++) {
            dict.put(Integer.toString('A' + index), index + 1);
        }
    }
}