
public class PG_12949 {
	public int[][] solution(int[][] arr1, int[][] arr2) {

		int R1 = arr1.length;

		int R2 = arr2.length;
		int C2 = arr2[0].length;

		int[][] answer = new int[R1][C2];

		for (int row1 = 0; row1 < R1; row1++) {

			for (int col2 = 0; col2 < C2; col2++) {
				int sum = 0;
				for (int row2 = 0; row2 < R2; row2++) {
					sum += arr1[row1][row2] * arr2[row2][col2];
				}

				answer[row1][col2] = sum;
			}
		}

		return answer;
	}
}
