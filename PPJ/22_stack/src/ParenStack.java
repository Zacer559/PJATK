
public class ParenStack {
	private Node head = null;

	public void push(char data) {
		head = new Node(data, head);
	}

	public char pop() {
		char d = head.getData();
		head = head.getNext();
		return d;
	}

	public boolean empty() {
		return head == null;
	}
	public char getTop()
	{
		return head.getData();		
		
	}
}
