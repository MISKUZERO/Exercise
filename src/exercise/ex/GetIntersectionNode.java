package exercise.ex;

import java.util.HashSet;

public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> hashSet = new HashSet<>();
        while (headA != null && headB != null) {
            if (!hashSet.add(headA))
                return headA;
            if (!hashSet.add(headB))
                return headB;
            headA = headA.next;
            headB = headB.next;
        }
        while (headA != null) {
            if (!hashSet.add(headA))
                return headA;
            headA = headA.next;
        }
        while (headB != null) {
            if (!hashSet.add(headB))
                return headB;
            headB = headB.next;
        }
        return null;
    }
}


