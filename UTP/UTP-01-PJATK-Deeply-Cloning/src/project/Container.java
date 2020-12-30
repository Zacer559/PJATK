package project;

import java.util.ArrayList;
import java.util.List;

public class Container<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>,  TAggregateResult> implements IContainer<TElement, TAggregateResult>{

	public Container() {}
	private List<TElement> list = new ArrayList<TElement>();
	
	
	public void add (TElement element) 
	{ 
		list.add(element);
	}

	public TAggregateResult aggregateAllElements() {
		
		TAggregateResult minNumber = null;
		
		if(list == null) {
			return null;
		}
		
		for (int i =0; i < list.size();i++) {
			TElement compere = list.get(i);
			minNumber = compere.aggregate(minNumber);
		}
		return minNumber;
	}

	public TElement cloneElementAtIndex(int index) {
		
		if (index < 0 || index >= list.size()) {
			return null;
		}
		
		TElement clone = list.get(index).deepClone();
		return clone;
	}
	public TElement get(int index) {
		if (index < 0 || index >= list.size()) {
			return null;
		}
	
		return list.get(index);
	}
	
	public List<TElement> elements() {
		return list;
	}
}