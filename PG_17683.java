
public class PG_17683 {
	public static String[] origins = { "C#", "D#", "F#", "G#", "A#" };
	public static String[] substitutions = { "c", "d", "f", "g", "a" };
	public static final int BEGIN = 0;
	public static final int END = 1;
	public static final int TITLE = 2;
	public static final int SHEET = 3;

	public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		int maxTime = 0;

		int infoLen = musicinfos.length;
		for (int index = 0; index < infoLen; index++) {
			String[] parseInfo = musicinfos[index].split(",");
			int playTime = getPlayTime(parseInfo[BEGIN], parseInfo[END]);
			String musicSheet = getMusicSheet(parseInfo[SHEET], playTime);

			// 조건이 일치하는 경우
			if (checkCoincidence(musicSheet, origin2substitution(m))) {
				if (maxTime < playTime) {
					maxTime = playTime;
					answer = parseInfo[TITLE];
				}
			}
		}

		return answer;
	}

	public static int getPlayTime(String begin, String end) {
		String[] beginTime = begin.split(":");
		String[] endTime = end.split(":");

		int beginHour = Integer.parseInt(beginTime[0]);
		int beginMin = Integer.parseInt(beginTime[1]);

		int endHour = Integer.parseInt(endTime[0]);
		int endMin = Integer.parseInt(endTime[1]);

		return (endHour * 60 + endMin) - (beginHour * 60 + beginMin);
	}

	public static String getMusicSheet(String originSheet, int playTime) {
		String sheet = origin2substitution(originSheet);
		String res = "";
		int sheetLen = sheet.length();

		if (sheetLen < playTime) {
			int share = playTime / sheetLen;
			int mod = playTime % sheetLen;

			for (int iter = 0; iter < share; iter++) {
				res += sheet;
			}

			res += sheet.substring(0, mod);
		} else {
			res = sheet.substring(0, playTime);
		}

		return res;
	}

	public static String origin2substitution(String origin) {
		String converted = new String(origin);

		for (int index = 0; index < 5; index++) {
			converted = converted.replaceAll(origins[index], substitutions[index]);
		}

		return converted;
	}

	public static boolean checkCoincidence(String sheet, String melody) {
		return (sheet.indexOf(melody) != -1);
	}
}
