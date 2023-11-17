package day21;

import java.util.Arrays;

/**
 * 俄罗斯套娃问题，使用最长上升子序列的一道很高的题目
 * https://leetcode.cn/problems/russian-doll-envelopes/
 */
public class Code05_MaxEnvelops {
    /**
     * 先将二维数组先按照宽度由小到大进行排序，再按照高度进行排序，最后将高度由大到小抽取出来跑一个最长上升子序列
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if(n < 2) return n;
        Arrays.sort(envelopes,(a,b)->a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] end = new int[n];
        int r = 0;
        end[r++] = envelopes[0][1];
        for(int i = 1;i < n;i++){
            int index = search(end, 0, r-1, envelopes[i][1]);
            if(index == -1) end[r++] = envelopes[i][1];
            else end[index] = envelopes[i][1];
        }
        return r;
    }

    public static int search(int[] end,int l,int r,int target){
        if(target > end[r]) return -1;
        int res = r;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if(end[mid] >= target) {
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }
    
}
