import java.util.Stack;

public class PG_60058 {
	public String solution(String str) {
		String answer = "";

		// 1.
		if ("".equals(str))
			return str;
		if (isRight(str))
			return str;

		// 2.
		String[] splited = split2Balance(str);

		// 3.
		if (isRight(splited[0])) {
			// 3-1.
			return splited[0] + solution(splited[1]);
		}

		// 4.
		else {
			answer += "(";
			answer += solution(splited[1]);
			answer += ")";

			for (int index = 1; index < splited[0].length() - 1; index++) {
				if (splited[0].charAt(index) == '(') {
					answer += ")";
				} else {
					answer += "(";
				}
			}

			return answer;
		}

	}

	public static String[] split2Balance(String str) {
		String[] res = new String[2];

		int l = 0;
		int r = 0;
		// 최소 균형잡힌 괄호 문자열 찾기
		int len = str.length();
		for (int index = 0; index < len; index++) {
			if (str.charAt(index) == '(')
				l++;
			else
				r++;

			if (l == r) {
				res[0] = str.substring(0, index + 1);
				res[1] = str.substring(index + 1);
				break;
			}
		}

		return res;
	}

	public static boolean isRight(String str) {
		Stack<Character> stack = new Stack<>();

		for (int index = 0; index < str.length(); index++) {
			char ch = str.charAt(index);

			// 여는 괄호
			if (ch == '(') {
				stack.push(ch);
			}

			// 닫는 괄호
			else {
				// 스택이 비어있으면
				if (stack.isEmpty()) {
					return false;
				}

				stack.pop();
			}
		}

		return stack.isEmpty();
	}
}
