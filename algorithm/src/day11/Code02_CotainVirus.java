package day11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 包含病毒，感觉就是深搜，就是在深搜的基础上变了一点花样
 * 题目想的简单了，里面好多细节实现不了，在实现的过程中还是得进行记录
 * 在做题之前一定得先理顺思路
 * 这个题目的coding难度有点大，我先按照答案的思路走一遍
 * https://leetcode.cn/problems/contain-virus/
 */
public class Code02_CotainVirus {
    public static int[] d = new int[] { -1, 0, 1, 0, -1 };

    public int containVirus(int[][] isInfected) {
        int ans = 0;
        int n = isInfected.length;
        int m = isInfected[0].length;
        while (true) {
            // 在每一天中并病毒区域相邻的能够传播的区域,每一天都需要进行更新
            List<Set<Integer>> neighbors = new ArrayList<>();
            // 每一个病毒区域的防火墙的数量
            List<Integer> firewalls = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (isInfected[i][j] == 1) {
                        // 表示该区域是病毒区域，需要进行统计,在统计的过程中需要将访问过的坐标进行标记，
                        // 将其标记成为病毒区域代号的相反数，并在一天找到最合适的扩展源之后进行恢复
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.add(new int[] { i, j });
                        Set<Integer> neighbor = new HashSet<>();// 用于统计传播点
                        int firewall = 0;
                        int idx = neighbors.size() + 1;// 对病毒区域进行标记
                        isInfected[i][j] = -idx;
                        while (!queue.isEmpty()) {
                            int[] arr = queue.poll();
                            int x = arr[0], y = arr[1];
                            for (int k = 0, nx, ny; k < 4; k++) {
                                nx = x + d[k];
                                ny = y + d[k + 1];
                                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                    if (isInfected[nx][ny] == 1) {
                                        queue.offer(new int[] { nx, ny });
                                        isInfected[nx][ny] = -idx;
                                    } else if (isInfected[nx][ny] == 0) {
                                        firewall++;
                                        neighbor.add(hash(nx, ny));
                                    }
                                }
                            }
                        }
                        neighbors.add(neighbor);
                        firewalls.add(firewall);

                    }
                }
            }
            if (neighbors.isEmpty())
                break;// 表示全为1
            // 当退出循环之后，neighbors和firewalls已经统计完毕当天所有的病毒以及传播点和防火墙等信息
            // 下面需要计算出最大的传播源对应的病毒区域
            int maxIn = 0;
            for (int i = 1; i < neighbors.size(); i++) {
                if (neighbors.get(i).size() > neighbors.get(maxIn).size()) {
                    maxIn = i;
                }
            }
            // 添加防火墙
            ans += firewalls.get(maxIn);
            // 将不是最大病毒区的值恢复为1，添加了病毒区域的值设置为2
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] != -maxIn - 1) {
                            // 需要进行恢复
                            isInfected[i][j] = 1;
                        } else {
                            // 设置防火墙的区域
                            isInfected[i][j] = 2;
                        }
                    }

                }
            }
            // 对不是防火墙的区域进行扩散
            for (int i = 0; i < neighbors.size(); i++) {
                if (i != maxIn) {
                    for (int val : neighbors.get(i)) {
                        // 下面是对坐标进行恢复，现在其实不是很懂
                        int x = val >> 16;
                        int y = val & ((1 << 16) - 1);
                        isInfected[x][y] = 1;
                    }
                }
            }
            if (neighbors.size() == 1) {
                break;// 表示这是最后的病毒区域，所有的病毒区域已经合并了其实
            }

        }
        return ans;
    }

    public static int hash(int x, int y) {
        return (x << 16) ^ y;
    }
}
