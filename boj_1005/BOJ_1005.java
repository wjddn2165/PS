package boj_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1]; // 건물을 짓기위해 필요한 시간을 저장하는 배열.
            int[] degree = new int[N + 1]; // 노드들의 진입차수를 저장하는 배열.
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

            for (int j = 0; j <= N; j++) { // 건물의 개수만큼 노드 생성.
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                time[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                degree[to]++;
            }

            int W = Integer.parseInt(br.readLine());

            Queue<Building> queue = new LinkedList<>();

            for (int j = 1; j <= N; j++) {
                if (degree[j] == 0) {
                    queue.offer(new Building(j, 1));
                }
            }

            int[] dp = new int[N + 1]; // 각 노드가 건설 시작하기까지 걸리는 시간 저장.

            while (!queue.isEmpty()) {
                Building building = queue.remove();
                int number = building.getNumber();
                int order = building.getOrder();
                ArrayList<Integer> list = graph.get(number);

                if (number == W) {
                    sb.append(dp[W] + time[W]).append("\n");
                    break;
                }

                for (int next : list) {
                    dp[next] = Math.max(dp[number] + time[number], dp[next]);

                    degree[next]--;

                    if (degree[next] == 0) {
                        queue.offer(new Building(next, order + 1));
                    }
                }
            }
        }
        System.out.print(sb);
    }
}

class Building {
    private final int number;
    private final int order;

    public int getNumber() {
        return number;
    }

    public int getOrder() {
        return order;
    }

    public Building(int number, int order) {
        this.number = number;
        this.order = order;
    }
}
