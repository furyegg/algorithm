package week2;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        ListNode res = l1;
        while (l1 != null) {
            int sum = l2 == null ? l1.val : l1.val + l2.val;
            if (sum >= 10) {
                l1.val = sum - 10;
            } else {
                l1.val = sum;
            }
    
            ListNode l1Next = l1.next;
            ListNode l2Next = null;
            if (l2 != null) {
                l2Next = l2.next;
            }
            
            if (l1Next == null && l2Next == null && sum >= 10) {
                l1.next = new ListNode(1);
                l1 = l1.next;
                l2 = null;
            } else {
                if (l1Next == null) {
                    l1.next = l2 == null ? null : l2.next;
                    l2 = null;
                    l1 = l1.next;
                } else {
                    l1 = l1.next;
                    l2 = l2 == null ? null : l2.next;
                }
                if (sum >= 10) {
                    l1.val += 1;
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        ListNode l2 = new ListNode(5);
        ListNode l1 = new ListNode(5);
        AddTwoNumbers app = new AddTwoNumbers();
        ListNode l3 = app.addTwoNumbers(l1, l2);
        System.out.println();
    }
}