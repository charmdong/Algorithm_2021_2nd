package Test;

import java.util.Arrays;

public class KKB_03 {

	public static int[][] money;
	public static int[][] stone;
	public static final int NOPE = 0;
	public static final int BUY = 1;
	public static final int SELL = 2;
	
	public static void main(String[] args) {
		int[] prices = {0, 2, 1, 1, 2};
		
		System.out.println(solution(prices));
		
		for(int row = 0; row < prices.length; row++) {
			System.out.println(Arrays.toString(money[row]));
		}
	}
	
	public static int solution(int[] prices) {
		return dp(prices);
	}
	
	public static int dp(int[] prices) {
		money = new int[prices.length][3];
		stone = new int[prices.length][3];
		
		money[0][NOPE] = money[0][BUY] = money[0][SELL] = 0;
		stone[0][NOPE] = stone[0][BUY] = stone[0][SELL] = 0;
		
		for(int day = 1; day < prices.length; day++) {
			// 돌을 구매하지 않는 경우
			money[day][NOPE] = Math.max(money[day - 1][SELL] , Math.max(money[day - 1][NOPE], money[day - 1][BUY]));
			stone[day][NOPE] = stone[day - 1][NOPE];
			
			// 돌을 구매하는 경우
			int case1 = (prices[day] <= money[day - 1][NOPE]) ? (money[day - 1][NOPE] - prices[day]) : money[day - 1][NOPE];
			int case2 = (prices[day] <= money[day - 1][BUY]) ? (money[day - 1][BUY] - prices[day]) : money[day - 1][BUY];
			int case3 = (prices[day] <= money[day - 1][SELL]) ? (money[day - 1][SELL] - prices[day]) : money[day - 1][SELL];
			
			money[day][BUY] = Math.max(Math.max(case1, case2), case3);
			stone[day][BUY] = stone[day - 1][BUY] + 1;
			
			// 돌을 파는 경우
			case1 = (stone[day - 1][NOPE] > 0) ? (money[day - 1][NOPE] + (prices[day] * stone[day - 1][NOPE])) : money[day - 1][NOPE];
			case2 = (stone[day - 1][BUY] > 0) ? (money[day - 1][BUY] + (prices[day] * stone[day - 1][BUY])) : money[day - 1][BUY];
			case3 = (stone[day - 1][SELL] > 0) ? (money[day - 1][SELL] + (prices[day] * stone[day - 1][SELL])) : money[day - 1][SELL];
			
			money[day][SELL] = Math.max(Math.max(case1, case2), case3);
			stone[day][SELL] = 0;
		}
		
		int len = prices.length;
		return Math.max(Math.max(money[len - 1][NOPE], money[len - 1][BUY]), money[len - 1][SELL]);
	}
}
