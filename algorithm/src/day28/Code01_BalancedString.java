package day28;

/**
 * 平衡字符串：滑动窗口
 * https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
 * 单调性：滑动窗口往右滑动，平衡的可能性就越大，当在一定区间中达到平衡时，再扩大区间一定达标，这时候左窗口滑动
 * 指标：能否平衡，只需要维护区间中每个字符的词频即可，并且再根据单调性的窗口滑动过程中更新维护的结构
 * 中间使用了一些技巧进行词频统计
 */
public class Code01_BalancedString {
    public int balancedString(String s) {
        //将字符串转换成为数字，进行词频统计
        int n = s.length();
        int[] arr = new int[n];
        int[] cnt = new int[4];
        char ch = ' ';
        for(int i = 0;i < n;i++){
            ch = s.charAt(i);
            arr[i] = ch == 'Q' ? 0 : (ch == 'W' ? 1 : (ch == 'E' ? 2 : 3));
            cnt[arr[i]]++;
        }
        int requires =n / 4;
        int ans = n;
        //枚举左端点的滑动窗口
        for(int l = 0,r = 0;l < n;l++){
            while (r < n && !ok(l,r,requires,cnt)) {
                //窗口向右滑动，更新结构
                cnt[arr[r++]]--;
            }
            
            //到这里说明[l,r]区间范围可以满足条件
            if(ok(l, r, requires, cnt)){
                ans = Math.min(ans, r - l);
            }
            
            //左窗口滑动，更新结构
            cnt[arr[l]]++;
        }
        return ans;
        
    }
    //根据维护的窗口结构信息进行判断，cnt中维护的是窗口之外的词频信息
    public static boolean ok(int l, int r, int requires,int[] cnt) {
        int len = r - l;
        for(int i = 0;i < 4;i++){
            if(cnt[i] > requires) return false;
            len -= requires - cnt[i];
        }

        return len == 0;
    }



}
