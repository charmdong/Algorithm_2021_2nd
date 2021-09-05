import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG_72411 {

	public static Map<String, Integer> map = new HashMap<>();
	public static List<String> resList = new ArrayList<>();

	public String[] solution(String[] orders, int[] course) {

		int courseLen = course.length;
		for (int index = 0; index < courseLen; index++) {
			getCombinationeCase(course[index], orders);
			List<String> menuList = getBestMenus();
			for (String menu : menuList) {
				resList.add(menu);
			}
			map.clear();
		}

		Collections.sort(resList);

		return resList.toArray(new String[resList.size()]);
	}

	public static void getCombinationeCase(int cbCnt, String[] orders) {

		int orderLen = orders.length;

		for (int index = 0; index < orderLen; index++) {
			char[] stringToChar = orders[index].toCharArray();
			Arrays.sort(stringToChar);
			String order = new String(stringToChar);
			doCombination(cbCnt, 0, "", order);
		}
	}

	public static void doCombination(int cbCnt, int curIndex, String res, String order) {
		if (res.length() == cbCnt) {
			if (map.containsKey(res)) {
				int cnt = map.get(res);
				map.put(res, cnt + 1);
			} else {
				map.put(res, 1);
			}

			return;
		}

		for (int index = curIndex; index < order.length(); index++) {
			doCombination(cbCnt, index + 1, res + order.charAt(index), order);
		}
	}

	public static List<String> getBestMenus() {
		if (map.size() == 0) {
			return new ArrayList<>();
		}

		List<String> menuList = new ArrayList<>();
		List<String> keyList = new ArrayList<>(map.keySet());

		Collections.sort(keyList, (key1, key2) -> (map.get(key2).compareTo(map.get(key1))));

		String bestMenu = keyList.get(0);
		int bestCnt = map.get(bestMenu);

		if (bestCnt == 1) {
			return new ArrayList<>();
		}

		menuList.add(bestMenu);
		keyList.remove(0);

		for (String key : keyList) {
			if (map.get(key) == bestCnt) {
				menuList.add(key);
			}
		}

		return menuList;
	}
}
