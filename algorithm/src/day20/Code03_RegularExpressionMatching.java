package day20;

/**
 * 还是正则表达式匹配，与上面那一题很像，只是划分的条件不相同，这一题稍微难一些
 * 直接写一下记忆化搜索的版本了
 * https://leetcode.cn/problems/regular-expression-matching/
 */
public class Code03_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        char[] sr = s.toCharArray();
        char[] pr = p.toCharArray();
        int n = sr.length;
        int m = pr.length;
        Boolean[][] rec = new Boolean[n + 1][m + 1];
        return dfs(sr, pr, rec, 0, 0);
    }

    public static boolean dfs(char[] s, char[] p, Boolean[][] rec, int i, int j) {
        if (rec[i][j] != null)
            return rec[i][j];
        boolean ans = false;
        if (i == s.length) {
            if (j == p.length)
                ans = true;
            else
                ans = j + 1 < p.length && p[j + 1] == '*' && dfs(s, p, rec, i, j + 2);
        } else if (j == p.length)
            ans = false;
        else {
            if (j + 1 == p.length || p[j + 1] != '*') {
                ans = (s[i] == p[j] || p[j] == '.') && dfs(s, p, rec, i + 1, j + 1);
            } else {
                ans = dfs(s, p, rec, i, j + 2) || ((s[i] == p[j] || p[j] == '.') && dfs(s, p, rec, i + 1, j));
            }
        }

        rec[i][j] = ans;
        return ans;
    }

}
