package project;


public interface IAggregable<TElement extends IAggregable<TElement, TResult>, TResult> {

	TResult aggregate(TResult intermediateResult);
}