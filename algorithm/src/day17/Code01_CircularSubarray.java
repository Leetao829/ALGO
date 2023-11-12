package day17;
/**
 * 环形子数组最大值问题：想法：将环形数组链式化，这与洛谷上的区间dp很像
 */
public class Code01_CircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] arr = new int[2 * n];
        for(int i = 0;i < n;i++){
            arr[i+n] = nums[i];
        }
        //然后在取得的所有区间上使用dp
        int[] dp = new int[n];
        int res = Integer.MIN_VALUE;
        for(int start = 0,end;start <= n-1;start++){
            end = start + n - 1;
            //在[start,end]之间使用dp
            dp[0] = arr[start];
            int max = dp[0];
            for(int i = 1;i < n;i++){
                dp[i] = Math.max(arr[i+start], arr[i+start]+dp[i-1]);
                max = Math.max(max, dp[i]);
            }
            res = Math.max(res, max);
        }
        return res;
    }

    //将环形数组链式化是能够解决问题的，但是显然这样的时间复杂度较高，
    //考虑到我们所求的是连续子数组，只用求出最大与最小即可
    public int maxSubarraySumCircular1(int[] nums){
        int n = nums.length;
        int sum = 0;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        sum += nums[0];
        int max = dp1[0];
        int min = dp2[0];
        for(int i = 1;i < n;i++){
            sum += nums[i];
            dp1[i] = Math.max(nums[i], nums[i] + dp1[i-1]);
            dp2[i] = Math.max(nums[i], nums[i] + dp2[i-1]);
            max = Math.max(max, dp1[i]);
            min = Math.min(min, dp2[i]);
        }
        return min == sum ? max : Math.max(max, sum - min);

        
    }

    //写了这么多还没有写过空间压缩的写法
    public int maxSubarraySumCircular2(int[] nums){
        int n = nums.length;
        int sum = nums[0];
        int maxPre = nums[0];
        int minPre = nums[0];
        int max = maxPre;
        int min = minPre;
        for(int i = 1;i < n;i++){
            maxPre = Math.max(nums[i], maxPre + nums[i]);
            max = Math.max(max, maxPre);
            minPre = Math.min(nums[i], nums[i] + minPre);
            min = Math.min(min, minPre);
            sum += nums[i];
        }
        return min == sum ? max : Math.max(max, sum - min);

        
    }

    //想一个问题：求出子数组的最大值并求出该子数组的左下标和右下标
    
    
}
