package day18;

import java.util.Arrays;

/**
 *子矩阵最大累加和问题
 https://leetcode.cn/problems/max-submatrix-lcci/
 */
public class Code01_SubMatrix {
    public int[] getMaxMatrix(int[][] matrix) {
        //将子矩阵最大累加和转换为子数组最大累加和，其中需要对下标进行记录
        int n = matrix.length;
        int m = matrix[0].length;
        int[] nums = new int[m];
        int lx = -1,ly = -1,rx = -1,ry = -1,maxSum = Integer.MIN_VALUE;
        for(int i = 0;i < n;i++){
            Arrays.fill(nums, 0);
            for(int j = i;j < n;j++){
                //表示将[i,j]行的数组进行合并
                for(int k = 0;k < m;k++) nums[k] += matrix[j][k];
                int[] res = method(nums);
                if(res[2] > maxSum){
                    lx = i;ly = res[0];rx = j;ry = res[1];
                    maxSum = res[2];
                }
            }
        }
        return new int[]{lx,ly,rx,ry};
    }

    /*
     * 计算nums数组中最大子数组的值以及左右下标
     */
    public static int[] method(int[] nums){
        int n = nums.length;
        int left = -1,right = -1,sum = Integer.MIN_VALUE;
        int maxPre = Integer.MIN_VALUE;
        for(int l = 0,r = 0;r < n;r++){
            if(maxPre >= 0){
                //以r为结尾的最大子数组一定需要加上前面的子数组
                maxPre = maxPre + nums[r];
            }else{
                maxPre = nums[r];
                l = r;
            }
            if(maxPre > sum){
                sum = maxPre;
                left = l;
                right = r;
            }
        }
        return new int[]{left,right,sum};
    }
    
}
