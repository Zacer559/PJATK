package eu.glowacki.utp.assignment10;

public final class UnimplementedException extends RuntimeException {

	private static final long serialVersionUID = -9136633471042484748L;

	public UnimplementedException(Exception e) {
		super(e);
	}

	public UnimplementedException(String s) {
		super(s);
	}
}