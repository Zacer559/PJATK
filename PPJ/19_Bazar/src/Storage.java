
public class Storage {
	Box[] names;

	public Storage(Box[] boxs) {
		names = boxs;

	}

	public int totQuant(String n) {
		int sum=0;
		
		for (Box q : names) {
		for (Product w : q.getP())
		{if (w.getName()==n)
		{sum=sum+w.getQuantity();}
			
			
			
		}
		
		}
		return sum;
	}

}
