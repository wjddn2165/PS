package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16236 {

    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int size = 2; // 처음 상어의 크기
    static int eating = 0;
    static int answer = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int x; // 상어의 위치
    static int y;

    static void solution() {
        while (true) {

            Point fish = find(); // 먹을 수 있는 물고기 중 가장 가까운 위치를 찾음.

            if (fish == null) { // 먹을 수 있는 물고기가 없으면 종료
                return;
            }

            answer += fish.getDistance();
            x = fish.getX(); // 상어 이동
            y = fish.getY();
            board[x][y] = 0;
            eating++;

            if (eating == size) { // 상어의 크기만큼 물고기를 먹으면 사이즈 증가
                size++;
                eating = 0;
            }

        }
    }

    static Point find() {
        visited = new boolean[N][N];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y, 0));
        visited[x][y] = true;

        boolean findMinDist = false;
        int minDist = Integer.MAX_VALUE;
        Point nextFish = null;

        while (!queue.isEmpty()) {
            Point cur = queue.remove();
            int curX = cur.getX();
            int curY = cur.getY();
            int curDist = cur.getDistance();

            if (findMinDist && 0 < board[curX][curY] && board[curX][curY] < size) { // 최단거리인 물고기 중 조건에 부합하는 물고기 찾기.
                if (minDist == curDist) {
                    if (nextFish.getX() == curX) {
                        if (nextFish.getY() > curY) {
                            nextFish = cur;
                        }
                    } else if (nextFish.getX() > curX) {
                        nextFish = cur;
                    }
                } else {
                    break;
                }
            }

            if (!findMinDist) { // 아직 최단거리인 물고기를 찾지 못했으면
                if (0 < board[curX][curY] && board[curX][curY] < size) { // 먹을 수 있는 물고기가 등장하면 최단거리 설정
                    findMinDist = true;
                    minDist = curDist;
                    nextFish = cur;
                }
            }



            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && board[nx][ny] <= size) {
                        queue.offer(new Point(nx, ny, curDist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return nextFish;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 9) {
                    x = i;
                    y = j;
                    board[i][j] = 0;
                }
            }
        }

        solution();
        System.out.println(answer);
    }
}

class Point {
    private final int x;
    private final int y;
    private final int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }
}