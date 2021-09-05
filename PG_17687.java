
public class PG_17687 {
	
	public static String[] mapNumList = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
			"F" };

	public String solution(int n, int t, int m, int p) {
		String answer = "";

		int minLen = t * m;
		StringBuilder sb = new StringBuilder();

		for (int num = 0;; num++) {
			if (sb.length() >= minLen)
				break;

			String result = getNary(num, n);
			sb.append(result);
		}

		for (int index = p - 1; index <= m * t - m + p - 1; index += m) {
			answer += sb.charAt(index);
		}

		return answer;
	}

	public static String getNary(int num, int nary) {
		if (num == 0)
			return "0";

		StringBuilder sb = new StringBuilder("");

		while (num != 0) {
			int share = num / nary;
			int mod = num % nary;

			sb.append(mapNumList[mod]);
			num = share;
		}

		return sb.reverse().toString();
	}
}
