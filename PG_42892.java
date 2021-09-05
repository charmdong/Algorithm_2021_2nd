import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PG_42892 {
	static class Node {
		int y;
		int x;
		int index;
		Node left;
		Node right;

		public Node() {
		}

		public Node(int y, int x, int index) {
			this.y = y;
			this.x = x;
			this.index = index;
		}
	}

	public static List<Node> nodeList = new ArrayList<>();
	public static List<Integer> prePath = new ArrayList<>();
	public static List<Integer> postPath = new ArrayList<>();

	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length];

		sortNodeList(nodeinfo);
		setTree();
		preorder(nodeList.get(0));
		postorder(nodeList.get(0));

		for (int index = 0; index < prePath.size(); index++) {
			answer[0][index] = prePath.get(index);
		}

		for (int index = 0; index < postPath.size(); index++) {
			answer[1][index] = postPath.get(index);
		}

		return answer;
	}

	public static void sortNodeList(int[][] nodeinfo) {
		int R = nodeinfo.length;
		int index = 1;

		for (int row = 0; row < R; row++) {
			nodeList.add(new Node(nodeinfo[row][1], nodeinfo[row][0], index++));
		}

		Collections.sort(nodeList, new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				if (node1.y == node2.y) {
					return Integer.compare(node1.x, node2.x);
				}

				return Integer.compare(node2.y, node1.y);
			}
		});
	}

	public static void setTree() {
		Node root = nodeList.get(0);

		int len = nodeList.size();
		for (int index = 1; index < len; index++) {
			Node next = nodeList.get(index);

			Node cur = root;

			while (true) {

				if (next.x < cur.x) {
					if (cur.left == null) {
						cur.left = next;
						break;
					}
					cur = cur.left;
				} else {
					if (cur.right == null) {
						cur.right = next;
						break;
					}
					cur = cur.right;
				}
			}
		}
	}

	public static void preorder(Node cur) {
		if (cur == null)
			return;

		prePath.add(cur.index);
		preorder(cur.left);
		preorder(cur.right);
	}

	public static void postorder(Node cur) {
		if (cur == null)
			return;

		postorder(cur.left);
		postorder(cur.right);
		postPath.add(cur.index);
	}
}
