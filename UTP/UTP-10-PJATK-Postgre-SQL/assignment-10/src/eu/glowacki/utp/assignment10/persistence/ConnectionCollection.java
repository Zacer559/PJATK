package eu.glowacki.utp.assignment10.persistence;

import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;

public final class ConnectionCollection {

	private final Map<String, Connection> _connections;

	public ConnectionCollection() {
		_connections = new TreeMap<String, Connection>();
	}

	public void add(String username, Connection connection) {
		if (!_connections.containsKey(username)) {
			_connections.put(username, connection);
		}
	}

	public Connection getConnection(String username) {
		Connection connection = null;
		if (!_connections.containsKey(username)) {
			connection = _connections.get(username);
		}
		return connection;

	}

}
