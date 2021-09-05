import java.util.LinkedList;
import java.util.Queue;

public class PG_42587 {
	public static int[] priorCnt = new int[10];

	public int solution(int[] priorities, int location) {
		getPriorCnt(priorities);

		return getAnswer(priorities, location);
	}

	public static void getPriorCnt(int[] priorities) {
		int len = priorities.length;

		for (int index = 0; index < len; index++) {
			priorCnt[priorities[index]]++;
		}
	}

	public static int getMaxPrior() {
		for (int index = 9; index > 0; index--) {
			if (priorCnt[index] > 0)
				return index;
		}

		return 0;
	}

	public static int getAnswer(int[] pArray, int location) {
		Queue<Integer> q = new LinkedList<>();
		int printCnt = 0;
		int arrayLen = pArray.length;

		for (int index = 0; index < arrayLen; index++) {
			q.offer(pArray[index]);
		}

		while (!q.isEmpty()) {
			int top = q.poll();
			int maxPrior = getMaxPrior();

			// 중요도가 높은 게 남아 있는 경우
			if (top < maxPrior) {
				q.offer(top);
				if (location > 0)
					location--;
				else {
					location = q.size() - 1;
				}
			}
			// 인쇄하면 됨
			else {
				printCnt++;
				priorCnt[top]--;
				// 내가 인쇄하려고 한 인쇄물
				if (location == 0) {
					return printCnt;
				} else {
					location--;
				}
			}
		}

		return 0;
	}
}
