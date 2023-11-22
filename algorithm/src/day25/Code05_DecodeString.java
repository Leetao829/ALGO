package day25;
/**
 * 字符串解码，使用嵌套类型递归
 */
public class Code05_DecodeString {
    public static int where;//用于记录当前到达了什么位置
    public String decodeString(String s){
        where = 0;
        return dfs(s.toCharArray(), 0);
    }

    public static String dfs(char[] str,int i){
        StringBuilder builder = new StringBuilder();
        int cnt = 0;
        while (i < str.length && str[i] != ']') {//说明嵌套终止条件
            if(str[i] >= 'a' && str[i] <= 'z' || str[i] >= 'A' && str[i] <= 'Z'){
                builder.append(str[i++]);
            }else if(str[i] >= '0' && str[i] <= '9'){
                //说明是数字
                cnt = cnt * 10 + str[i++] - '0';
            }else{
                //说明遇到'('，递归条件触发
                builder.append(get(cnt,dfs(str, i+1)));
                cnt = 0;
                i = where + 1;
            }
        }
        where = i;
        return builder.toString();
    }

    private static String get(int cnt, String s) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < cnt;i++) builder.append(s);
        return builder.toString();
    }
}
