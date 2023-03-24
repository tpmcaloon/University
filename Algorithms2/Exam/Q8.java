class Node
{
	int data;
	Node next;

	Node(int data, Node next)
	{
		this.data = data;
		this.next = next;
	}
}

class Main
{
	public static void printList(String msg, Node head)
	{
		System.out.print(msg);

		Node ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.data + " —> ");
			ptr = ptr.next;
		}

		System.out.println("null");
	}

	public static void alternatingSplit(Node odd, Node even)
	{
		if (odd == null || even == null) {
			return;
		}

		if (odd.next != null) {
			odd.next = odd.next.next;
		}

		if (even.next != null) {
			even.next = even.next.next;
		}

		alternatingSplit(odd.next, even.next);
	}

	public static Node[] alternatingSplit(Node source)
	{
		if (source == null) {
			return new Node[] {null, null};
		}

		Node aRef = source;
		Node bRef = source.next;
		alternatingSplit(aRef, bRef);

		return new Node[] { aRef, bRef };
	}

	public static void main(String[] args)
	{
		// input keys
		int[] keys = { 1, 2, 3, 4, 5, 6 };

		// construct the first linked list
		Node head = null;
		for (int i = keys.length - 1; i >= 0; i--) {
			head = new Node(keys[i], head);
		}

		Node[] nodes = alternatingSplit(head);

		// print both lists
		printList("First List: ", nodes[0]);
		printList("Second List: ", nodes[1]);
	}
}