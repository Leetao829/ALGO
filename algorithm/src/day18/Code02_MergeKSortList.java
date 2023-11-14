package day18;
/*
 * 合并K个升序链表
 * 使用堆进行求解。堆又叫做优先级队列，堆顶始终维护着加入堆的最大值或者最小值
 * 并且进行对调整（heapInsert或者heapfy）操作时的复杂度均是logn级别的
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 */

import java.util.PriorityQueue;



public class Code02_MergeKSortList {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        //首先建立小根堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a,b)->a.val-b.val);
        for(ListNode node : lists){
            if(node != null){
                heap.add(node);
            }
        }
        ListNode head = new ListNode();
        ListNode pre = head;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            pre.next = node;
            pre = node;
            if(node.next != null) heap.add(node.next);
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
