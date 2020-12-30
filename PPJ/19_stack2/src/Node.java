
public class Node {
	private int data;
	Node next;

	Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}

	Node(int data) {
		this(data, null);
	}

	char getData() {
		return data;
	}

	Node getNext() {
		return next;
	}
}
