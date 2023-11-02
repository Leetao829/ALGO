package day06;

public class Code03_MinRotateStep {

    public static int res ;
    public int findRotateSteps(String ring, String key) {
        res = 0;
        dfs(ring,key,0,0);
        return res;
    }
    public static void dfs(String ring,String key,int i,int j){
        if(j == key.length()) return;
        int[] r = rightFind(ring, key, i, j);
        int[] l = leftFind(ring,key,i,j);
        if(r[1] < l[1]){
            res += 1 + r[1];
            dfs(ring,key,r[0],j+1);
        }else{
            res += 1 + l[1];
            dfs(ring,key,l[0],j+1);
        }
    }

    public static int[] rightFind(String ring,String key,int i,int j){
        int n = ring.length();
        int k;
        int len = 0;
        for(k = 0;;k++){
            len += k;
            if(ring.charAt((i+k)%n) == key.charAt(j)){
                break;
            }
        }
        return new int[]{(i+k)%n,len};
    }
    public static int[] leftFind(String ring,String key,int i,int j){
        int n = ring.length();
        int k;
        int len = 0;
        for(k = 0;;k++){
            len += k;
            if(ring.charAt((i-k+n)%n) == key.charAt(j)){
                break;
            }
        }
        return new int[]{(i-k+n)%n,len};
    }

}
