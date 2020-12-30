package eu.glowacki.utp.assignment10.persistence;

public final class ConnectionManagerConfiguration {

	private final String _uri;
	private final String _username;
	private final String _password;

	public ConnectionManagerConfiguration(String _uri, String _username, String _password) {
		this._uri = _uri;
		this._username = _username;
		this._password = _password;
	}

	public String getUri() {
		return _uri;
	}

	public String getUsername() {
		return _username;
	}

	public String getPassword() {
		return _password;
	}
}
