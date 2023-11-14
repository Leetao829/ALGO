package day18;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 出现频率前k高的元素，很明显使用小顶堆，只用暴露前k个中最小的给新来的数字进行pk，小于k的直接加入到小根堆中即可
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class Code04_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        //标准答案是先对数组中的元素进行了统计，不是一边遍历一边进行更新
        //后面可以自己写一下手动更新的，中间肯定需要自己实现小根堆
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            if(!map.containsKey(num)) map.put(num, 1);
            else map.put(num,map.get(num)+1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(heap.size() < k) heap.add(new int[]{entry.getKey(),entry.getValue()});
            else {
                int[] val = heap.peek();
                if(entry.getValue() > val[1]) {
                    heap.poll();
                    heap.add(new int[]{entry.getKey(),entry.getValue()});
                }
            }
        }
        int[] res = new int[k];
        for(int i = 0;i < k;i++){
            res[k-i-1] = heap.poll()[0];
        }
        return res;
    }
    
}
