package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> resList= new ArrayList<>();
        int ia = 0, ib = 0, n = A.length, m = B.length;

        int[] temp = new int[2];
        temp[0] = -1;
        temp[1] = -1;
        while (ia < n && ib < m) {
            int[] intervalA = A[ia], intervalB = B[ib];
            if (intervalA[0] <= intervalB[0]) {
                ia++;
                if (intervalA[0] > temp[1]) {
                    temp = new int[]{intervalA[0], intervalA[1]};
                } else {
                    resList.add(intersection(temp, intervalA));
                    temp = merge(temp, intervalA);
                }
            } else {
                ib++;
                if (intervalB[0] > temp[1]) {
                    temp = new int[]{intervalB[0], intervalB[1]};
                } else {
                    resList.add(intersection(temp, intervalB));
                    temp = merge(temp, intervalB);
                }
            }
        }

        while (ia < n) {
            int[] intervalA = A[ia];
            if (intervalA[0] > temp[1]) {
                temp = new int[]{intervalA[0], intervalA[1]};
            } else {
                resList.add(intersection(temp, intervalA));
                temp = merge(temp, intervalA);
            }
            ia++;
        }

        while (ib < m) {
            int[] intervalB = B[ib];
            if (intervalB[0] > temp[1]) {
                temp = new int[]{intervalB[0], intervalB[1]};
            } else {
                resList.add(intersection(temp, intervalB));
                temp = merge(temp, intervalB);
            }
            ib++;
        }

        int[][] res = new int[resList.size()][2];
        for (int i = 0; i < resList.size(); i++) {
            res[i][0] = resList.get(i)[0];
            res[i][1] = resList.get(i)[1];
        }
        return res;
    }

    private int[] intersection(int[] temp, int[] curr) {
        return new int[]{Math.max(temp[0], curr[0]), Math.min(temp[1], curr[1])};
    }

    private int[] merge(int[] temp, int[] curr) {
        return new int[]{Math.min(temp[0], curr[0]), Math.max(temp[1], curr[1])};
    }
}