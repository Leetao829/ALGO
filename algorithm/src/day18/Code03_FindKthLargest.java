package day18;

import java.util.PriorityQueue;

/**
 * 寻找数组中第k大的数字，
 * 使用小根堆，复杂度为O(n)
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class Code03_FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->a-b);
        int n = nums.length;
        int i = 0;
        for(;i < k;i++) heap.add(nums[i]);
        for(;i < n;i++){
            int num = heap.peek();
            if(nums[i] > num){
                heap.poll();
                heap.add(nums[i]);
            }
        }
        return heap.peek();

    }
}
