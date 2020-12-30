
public class glowna {

	public static void main(String[] args)
	{ int[] arr = {1,2,3,4,5,6,7}};

	SingLList list = SingLList.arrayToList(arr);
	list.showList();
	list.removeOdd();
	list.showList();
	list.addFront(1);
	list.addBack(8);
	list.showList();
	System.out.println("contains 3? "+list.contains(3));
	System.out.println("contains 8? "+list.contains(8));
	list.clear();
	list.showList();
}