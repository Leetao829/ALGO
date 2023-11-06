package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购买大礼包，很吃状态的一道题，还是使用记忆化搜索比较简洁易懂和靠谱，上来写dp肯定难度是很大的
 * 我们先过滤掉肯定不会使得最终价格降低的大礼包
 * 状态转移：我们考虑在满足清单needs的最后一次购买，我们只需要求出使用大礼包和不使用大礼包直接购买两者的最小值
 * https://leetcode.cn/problems/shopping-offers/description/
 */
public class Code01_MinShopingOffers {
    public static Map<List<Integer>,Integer> rec;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //创建记忆化搜索的空间，因为状态过多，使用hashmap，我还不知道HashMap已经封装好了直接去重
        int n = price.size();
        rec = new HashMap<>();
        //下面对不合适的大礼包进行过滤，也就是大礼包中商品的总价格不能比原价购买的商品价格高
        List<List<Integer>> filterSpecial = new ArrayList<>();
        int totalCount = 0,totalPrice = 0;
        for(List<Integer> s : special){
            for(int i = 0;i < n;i++){
                totalCount += s.get(i);
                totalPrice += s.get(i) * price.get(i);
            }
            if(totalCount > 0 && totalPrice > s.get(n)){
                filterSpecial.add(s);
            }
        }

        return dfs(price, filterSpecial, needs, n);
    }

    //记忆化搜索在当前needs下的最低价格
    public static int dfs(List<Integer> price,List<List<Integer>> filterSpecial,List<Integer> curNeeds,int n){
        if(rec.containsKey(curNeeds)){
            return rec.get(curNeeds);
        }
        //先是一个大礼包都不买
        int curPrice = 0;
        for(int i = 0;i < n;i++){
            curPrice += curNeeds.get(i) * price.get(i);
        }
        //下面开始逐一选择满足条件的大礼包
        for(List<Integer> fs : filterSpecial){
            List<Integer> nextNeeds = new ArrayList<>();
            for(int i = 0;i < n;i++){
                if(fs.get(i) > curNeeds.get(i)) break;
                nextNeeds.add(curNeeds.get(i) - fs.get(i));
            }
            if(nextNeeds.size() == n){
                //表示当前大礼包可以选择
                curPrice = Math.min(curPrice, fs.get(n)+dfs(price, filterSpecial, nextNeeds, n));
            }
        }
        rec.put(curNeeds, curPrice);
        return rec.get(curNeeds);
    }
}
