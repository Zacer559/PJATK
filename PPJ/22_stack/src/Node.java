
public class Node {
	private char data;
	Node next;

	Node(char data, Node next) {
		this.data = data;
		this.next = next;
	}

	Node(char data) {
		this(data, null);
	}

	char getData() {
		return data;
	}

	Node getNext() {
		return next;
	}
}
