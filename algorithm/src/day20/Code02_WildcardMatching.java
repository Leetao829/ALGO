package day20;

/**
 * 通配符匹配,
 * https://leetcode.cn/problems/wildcard-matching/
 */
public class Code02_WildcardMatching {
    public boolean isMatch(String s, String p) {
        char[] sr = s.toCharArray();
        char[] pr = p.toCharArray();
        int n = sr.length;
        int m = pr.length;
        Boolean[][] rec = new Boolean[n + 1][m + 1];

        return dfs1(sr, pr, rec, 0, 0);
    }

    public static boolean dfs(String s, String p, int i, int j) {
        if (i == s.length()) {
            if (j == p.length())
                return true;// 空字符串可以直接匹配
            return p.charAt(j) == '*' && dfs(s, p, i, j + 1);
        }
        if (j == p.length())
            return false;
        if (p.charAt(j) == '*') {
            return dfs(s, p, i, j + 1) || dfs(s, p, i + 1, j);
        }
        return (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') && dfs(s, p, i + 1, j + 1);

    }

    // 上面暴力递归显然会超时，需要写一下记忆化搜索的版本
    public static boolean dfs1(char[] sr, char[] pr, Boolean[][] rec, int i, int j) {
        if (rec[i][j] != null)
            return rec[i][j];
        boolean res;
        if (i == sr.length) {
            if (j == pr.length)
                res = true;
            else
                res = pr[j] == '*' && dfs1(sr, pr, rec, i, j + 1);
        } else if (j == pr.length)
            res = false;
        else {
            if (pr[j] == '*')
                res = dfs1(sr, pr, rec, i, j + 1) || dfs1(sr, pr, rec, i + 1, j);
            else
                res = (sr[i] == pr[j] || pr[j] == '?') && dfs1(sr, pr, rec, i + 1, j + 1);
        }

        rec[i][j] = res;
        return rec[i][j];
    }

}
