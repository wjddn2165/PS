package boj_12738;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12738 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        result[0] = nums[0];
        int length = 1;

        for (int i = 1; i < N; i++) {
            int key = nums[i];
            /* 현재 알고있는 최장 수열의 마지막원소보다 큰 원소가 등장하면
             최장 수열의 길이가 증가하고 마지막에 원소 삽입.*/
            if (result[length - 1] < key) {
                result[length++] = key;
            }
            /* 현재 알고있는 수열의 끝 값을 더 작게 만들기 위해
             lowerBound 이용하여 대체 할 위치를 찾음.*/
            else {
                int low = 0; int high = length - 1;

                while (low <= high) {
                    int mid = (low + high) / 2;

                    if (result[mid] < key) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                result[low] = key;
            }
        }
        System.out.println(length);
    }
}

