package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PG_12978 {

	public static int[] dist;
	public static boolean[] visited;
	
	static class Edge implements Comparable<Edge> {
		
		int v, w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static List<Edge>[] edgeList;
	
	public int solution(int N, int[][] road, int K) {
        init(N, road);
        
        return dijkstra(N, K);
    }
	
	public static void init(int n, int[][] road) {
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		visited = new boolean[n + 1];
		edgeList = new ArrayList[n + 1];
		
		for(int index = 0; index < n + 1; index++) {
			edgeList[index] = new ArrayList<>();
		}
		
		int len = road.length;
		for(int index = 0; index < len; index++) {
			edgeList[road[index][0]].add(new Edge(road[index][1], road[index][2]));
			edgeList[road[index][1]].add(new Edge(road[index][0], road[index][2]));
		}
	}
	
	public static int dijkstra(int n, int k) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		dist[1] = 0;
		
		pq.offer(new Edge(1, dist[1]));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visited[cur.v]) continue;
			visited[cur.v] = true;
			
			for(Edge next : edgeList[cur.v]) {
				if(!visited[next.v] && dist[next.v] > dist[cur.v] + next.w) {
					dist[next.v] = dist[cur.v] + next.w;
					pq.offer(new Edge(next.v, dist[next.v]));
				}
			}
		}
		
		int cnt = 0;
		
		for(int index = 1; index <= n; index++) {
			if(dist[index] <= k) cnt++;
		}
		
		return cnt;
	}
}
