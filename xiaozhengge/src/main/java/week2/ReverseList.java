package week2;

import java.util.ArrayList;
import java.util.List;

public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        
        ListNode n = list.get(list.size() - 1);
        ListNode nn = n;
        for (int i = list.size() - 2; i >= 0; --i) {
            nn.next = list.get(i);
            nn = nn.next;
            if (i == 0) {
                nn.next = null;
            }
        }
        return n;
    }
    
    public static void main(String[] args) {
        ReverseList app = new ReverseList();
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode list2 = app.reverseList(list);
        System.out.println();
    }
}