package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {

    static int[][] board;
    static int[][] diffusion;
    static int R;
    static int C;
    static int T;
    static int[] dx = {0, 0, 1, -1}; // 동서남북
    static int[] dy = {1, -1, 0, 0};
    static int top; // 공기청정기 위쪽
    static int bottom; // 공기청정기 아래쪽

    static int solution() {
        for (int i = 0; i < T; i++) {
            diffuse();
            clean();
        }

        int answer = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    answer += board[i][j];
                }
            }
        }

        return answer;
    }

    static void diffuse() {
        diffusion = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) { // 미세먼지가 있는 칸이라면

                    for (int k = 0; k < dx.length; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                            if (board[nx][ny] != -1) {
                                diffusion[nx][ny] += board[i][j] / 5;
                                diffusion[i][j] -= board[i][j] / 5;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] += diffusion[i][j];
            }
        }
    }

    static void clean() {
        // 0 : 동, 1 : 서, 2 : 남, 3 : 북

        int x = top - 1;
        int y = 0;
        int dir = 3; // 바람이 오는 방향

        while (true) { // 윗 부분 순환
            if (x == 0 && y == 0) {
                dir = 0;
            }

            if (x == 0 && y == C - 1) {
                dir = 2;
            }

            if (x == top && y == C - 1) {
                dir = 1;
            }

            board[x][y] = board[x + dx[dir]][y + dy[dir]];

            x += dx[dir];
            y += dy[dir];

            if (x == top && y == 1) {
                board[x][y] = 0;
                break;
            }
        }

        x = bottom + 1;
        y = 0;
        dir = 2;

        while (true) { // 아랫 부분 순환
            if (x == R - 1 && y == 0) {
                dir = 0;
            }

            if (x == R - 1 && y == C - 1) {
                dir = 3;
            }

            if (x == bottom && y == C - 1) {
                dir = 1;
            }

            board[x][y] = board[x + dx[dir]][y + dy[dir]];

            x += dx[dir];
            y += dy[dir];

            if (x == bottom && y == 1) {
                board[x][y] = 0;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) { // 공기청정기 위치 저장
            if (board[i][0] == -1) {
                top = i;
                bottom = i + 1;
                break;
            }
        }

        int answer = solution();
        System.out.println(answer);

    }
}