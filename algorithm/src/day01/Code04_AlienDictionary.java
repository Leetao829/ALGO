import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code04_AlienDictionary {
    //使用拓扑排序完成火星词典
    public String alienOrder(String[] words) {

        //单词数量是固定的
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for(String word : words){
            for(int i = 0;i < word.length();i++){
                indegree[word.charAt(i)-'a'] = 0;//开始有单词的入度设置为零
            }
        }
        //创建图
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0;i < 26;i++) graph.add(new ArrayList<>());
        //分别遍历相邻的单词，创建依赖关系
        for(int i = 0,j,len;i < words.length-1;i++){
            String cur = words[i];
            String next = words[i+1];
            len = Math.min(cur.length(),next.length());
            for(j = 0;j < len;j++){
                if(cur.charAt(j) != next.charAt(j)){
                    //说明cur当前位置排在next前面
                    graph.get(cur.charAt(j)-'a').add(next.charAt(j)-'a');
                    indegree[next.charAt(j)-'a']++;
                    break;//后面没有比较的意义了
                }
            }
            if(j < cur.length() && j == next.length()) return "";

        }
        int[] queue = new int[26];
        int count = 0;
        int l = 0;
        int r = 0;
        for(int i = 0;i < 26;i++){
            if(indegree[i] != -1) count++;
            if(indegree[i] == 0) queue[r++] = i;
        }
        StringBuilder builder = new StringBuilder();
        while(l < r){
            int cur = queue[l++];
            builder.append((char)('a'+cur));
            for(int next : graph.get(cur)){
                if(--indegree[next] == 0){
                    queue[r++] = next;
                }
            }
        }


        return builder.length() == count ? builder.toString() : "";
    }
    
}
