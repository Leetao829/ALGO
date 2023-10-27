package dessert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code02_InterSection {
    //跟上面那个一样，只不过这个需要使用哈希表
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int n : nums1){
            if(!map.containsKey(n)){
                map.put(n, 1);
            }else{
                map.put(n, map.get(n)+1);
            }
        }
        for(int n : nums2){
            if(map.containsKey(n) && map.get(n) > 0){
                list.add(n);
                map.put(n, map.get(n)-1);
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0;i < res.length;i++) res[i] = list.get(i);
        return res;
    }
    
}
