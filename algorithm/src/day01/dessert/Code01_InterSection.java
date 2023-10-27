package dessert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code01_InterSection {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        
        for(int n : nums1){
            if(!set.contains(n)) set.add(n);
        }
        for(int n : nums2){
            if(set.contains(n)){
                list.add(n);
                set.remove(n);
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0;i < res.length;i++){
            res[i] = list.get(i);
        }
        return res;
    }
    
}
