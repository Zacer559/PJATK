package eu.glowacki.utp.assignment10.dtos;

public abstract class DTOBase {

	private int _id;

	protected DTOBase() {
	}

	protected DTOBase(int id) {
		_id = id;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		_id = id;
	}

	public boolean hasExistingId() {
		return getId() > 0;
	}
}