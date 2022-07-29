package boj_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 {

    static int N;
    static int M;
    static int[] parent;

    static int kruskal(PriorityQueue<Edge> edges, int N) {
        int sum = 0;
        int count = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.remove();

            int from = edge.getFrom();
            int to = edge.getTo();

            if (union(from, to)) {
                sum += edge.getWeight();
                count++;

                if (count == N - 2) {
                    return sum;
                }
            }
        }
        return sum;
    }

    static boolean union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        if (rootA == rootB) {
            return false;
        }

        parent[rootA] = parent[rootB];
        return true;
    }

    static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, weight));
        }

        int answer = kruskal(edges, N);
        System.out.println(answer);

    }
}

class Edge implements Comparable<Edge> {
    private final int from;
    private final int to;
    private final int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}