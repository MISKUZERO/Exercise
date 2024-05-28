package exercise.ex;

public class HasCycle {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        if (slow == null) return false;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
