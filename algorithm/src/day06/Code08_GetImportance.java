package day06;

import java.util.List;
//https://leetcode.cn/problems/employee-importance/description/
public class Code08_GetImportance {
    public static int res;
    public static int[] rec = new int[2001];
    public int getImportance(List<Employee> employees, int id) {
        //先将员工对应的下表进行统计
        for(int i = 0;i < employees.size();i++){
            rec[employees.get(i).id] = i;
        }
        res = 0;
        dfs(employees, id);
        return res;
    }
    public static void dfs(List<Employee> employees,int id){
        if(employees.get(rec[id]).subordinates == null) {
            res += employees.get(rec[id]).importance;
            return;
        }
        res += employees.get(rec[id]).importance;
        List<Integer> subs = employees.get(rec[id]).subordinates;
        for(int sub : subs){
            dfs(employees, sub);

        }

    }
    
}


class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}