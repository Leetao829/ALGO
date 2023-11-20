package day24;
/**
 * 布尔运算：区间dp，枚举运算符号即可，写动态规划的话，得写两张dp表，不如使用记忆化搜索并返回数组形式的结果
 * https://leetcode.cn/problems/boolean-evaluation-lcci/
 */
public class Code03_BoolEvaluation {
    public int countEval(String s, int result) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][][] rec = new int[n][n][];
        int[] res = dfs(str, rec, 0, n-1);
        return res[result];
    }

    public static int[] dfs(char[] str,int[][][] rec,int l,int r){
        if(rec[l][r] != null) return rec[l][r];
        int f = 0,t = 0;
        if(l == r){
            f = str[l] == '0' ? 1 : 0;
            t = str[l] == '1' ? 1 : 0;
            rec[l][r] = new int[]{f,t};
            return rec[l][r];
        }
        //对符号进行枚举
        for(int k = l + 1;k < r;k += 2){
            int[] left = dfs(str, rec, l, k - 1);
            int[] right = dfs(str, rec, k + 1, r);
            char opt = str[k];
            if(opt == '&'){
                f += left[0] * right[0] + left[0] * right[1] + left[1] * right[0];
                t += left[1] * right[1];
            }
            else if(opt == '|'){
                f += left[0] * right[0];
                t += left[0] * right[1] + left[1] * right[0] + left[1] * right[1];
            }
            else if(opt == '^'){
                f += left[0] * right[0] + left[1] * right[1];
                t += left[0] * right[1] + left[1] * right[0];
            }
        
        }
        rec[l][r] = new int[]{f,t};
        return rec[l][r];
    }
}
