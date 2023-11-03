package day09;

import java.util.HashSet;
import java.util.Set;

/**
 * 水壶问题，就是直接采用暴力枚举的方法，中间维护着两个状态，使用两个状态进行枚举，每次有六条路经可以走
 * https://leetcode.cn/problems/water-and-jug-problem/
 */
public class Code05_CanMeasureWater {
    public static Set<Long> set = new HashSet<>();
    public static int target;
    public static boolean res;
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        set.clear();
        res = false;
        target = targetCapacity;
        dfs(0, 0, jug1Capacity, jug2Capacity);
        return res;
    }

    /**
     * 
     * @param x 表示当前水壶一的状态
     * @param y 表示当前水壶二的状态
     */
    public static void dfs(int x,int y,int jug1Capacity,int jug2Capacity){
        if(res) return;
        if(x == target || y == target || x + y == target) {
            res = true;
            return;
        }
        if(set.contains(hash(x,y))) return;
        set.add(hash(x, y));
        //水壶一全部装满
        dfs(jug1Capacity,y, jug1Capacity, jug2Capacity);
        //水壶二全部装满
        dfs(x, jug2Capacity, jug1Capacity, jug2Capacity);
        //水壶一全部放空
        dfs(0, y, jug1Capacity, jug2Capacity);
        //水壶二全部放空
        dfs(x, 0, jug1Capacity, jug2Capacity);
        //水壶一全部倒向水壶二
        if(jug2Capacity-y >= x){
            dfs(0, y+x, jug1Capacity, jug2Capacity);
        }else{
            dfs(x+y-jug2Capacity, jug2Capacity, jug1Capacity, jug2Capacity);
        }

        //水壶二全部倒向水壶一
        if(jug1Capacity-x >= y){
            dfs(y+x, 0, jug1Capacity, jug2Capacity);
        }else{
            dfs(jug1Capacity, x+y-jug1Capacity, jug1Capacity, jug2Capacity);
        }
    }

    //这个是哈希函数，应该是能够保证二元组的去重效果
    public static long hash(int x,int y) {
        return (long) x * 1000001 + y;
    }


}
