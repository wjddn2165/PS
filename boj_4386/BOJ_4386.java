package boj_4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4386 {

    static int[] parent;

    static void dfs(int depth, int start, List<Integer> number, Node[] nodes, PriorityQueue<Edge> edges) {
        if (depth == 2) {
            Edge edge = new Edge(nodes[number.get(0)], nodes[number.get(1)]);
            edges.add(edge);
            return;
        }

        for (int i = start; i <nodes.length; i++) {
            number.add(i);
            dfs(depth + 1, start + 1, number, nodes, edges);
            number.remove(number.size() - 1);
        }
    }

    static boolean union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) {
            return false;
        }

        parent[fromRoot] = parent[toRoot];
        return true;
    }

    static int find(int number) {
        if (number == parent[number]) {
            return number;
        }

        return parent[number] = find(parent[number]);
    }

    static double kruskal(PriorityQueue<Edge> edges, int n) {

        int count = 0;
        double result = 0;

        while(!edges.isEmpty()) {
            Edge edge = edges.remove();

            if (union(edge.getFrom().getNumber(), edge.getTo().getNumber())) {
                result += edge.getDist();
                count++;
                if (count == n - 1) {
                    return result;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n];
        parent = new int[n];



        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            nodes[i] = new Node(x, y, i);
            parent[i] = i;
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        dfs(0, 0, new ArrayList<>(), nodes, edges);

        double answer = kruskal(edges, n);
        System.out.println(answer);

    }
}

class Edge implements Comparable<Edge> {
    private final Node from;
    private final Node to;
    private final double dist;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
        this.dist = Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public double getDist() {
        return dist;
    }

    @Override
    public int compareTo(Edge o) {
        return (int)(dist - o.getDist());
    }
}

class Node {
    private final double x;
    private final double y;
    private final int number;

    public Node(double x, double y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }
}