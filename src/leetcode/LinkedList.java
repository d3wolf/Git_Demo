package leetcode;

public class LinkedList {
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	// 经典快慢指针找环问题
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slower = head;
		ListNode faster = head;

		while (slower.next != null && faster.next != null && faster.next.next != null) {
			slower = slower.next;
			faster = faster.next.next;

			if (slower == faster) {
				return true;
			}
		}

		return false;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode listNode = new ListNode(0);
		int store = 0;
		while(l1 != null){
			
			int val = l1.val + l2.val + store;
			if(val > 10){
				val = val -10;
				store = 1;
			}else{
				store = 0;
			}
			listNode.val = val;
			listNode.next = new ListNode(0);

			listNode = listNode.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		
		
		return listNode;
	}

}
