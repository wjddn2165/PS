package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942 {

    static StringBuilder sb = new StringBuilder();
    static int[] nums;
    static int[][] dp;
    static boolean[][] visited;


    static int solution(int S, int E) { // top - down 재귀 방식으로 dp를 구현했다.
        if (visited[S][E]) {
            return dp[S][E];
        }

        if (S == E) {
            visited[S][E] = true;
            return dp[S][E] = 1;
        }

        if (S + 1 == E) {
            if (nums[S] == nums[E]) {
                visited[S][E] = true;
                return dp[S][E] = 1;
            }
            visited[S][E] = true;
            return dp[S][E] = 0;
        }

        if (solution(S + 1, E - 1) == 1 && nums[S] == nums[E]) {
            visited[S][E] = true;
            return dp[S][E] = 1;
        } else {
            visited[S][E] = true;
            return dp[S][E] = 0;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        nums = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(solution(S, E)).append("\n");
        }
        System.out.print(sb);
    }
}