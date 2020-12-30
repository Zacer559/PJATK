package eu.glowacki.utp.assignment10.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import eu.glowacki.utp.assignment10.UnimplementedException;

public abstract class ConnectionManager implements IConnectionManager {

	private final Map<String, ConnectionCollection> _connections;

	protected ConnectionManager() {
		_connections = new TreeMap<String, ConnectionCollection>();
	}

	public final Connection getConnection(ConnectionManagerConfiguration configuration) {
		String uri = configuration.getUri();
		String username = configuration.getUsername();
		String password = configuration.getPassword();
		Connection connection = null;
		ConnectionCollection connections = getConnectionCollection(uri);
		if (connections != null) {
			connection = connections.getConnection(username);
			if (connection == null) {
				connection = createConnection(uri, username, password);
				connections.add(username, connection);
			}
		} else {
			connection = createConnection(uri, username, password);
			connections = new ConnectionCollection();
			connections.add(username, connection);
		}
		return connection;

	}

	protected abstract String getDriverClassName();

	private void loadDriver() {
		try {
			Class.forName(getDriverClassName());
		} catch (ClassNotFoundException ex) {
			throw new UnimplementedException(ex);
		}

	}

	private ConnectionCollection getConnectionCollection(String uri) {
		return _connections.get(uri);
	}

	private Connection createConnection(String uri, String username, String password) {
		try {
			loadDriver();
			Connection connection = DriverManager.getConnection(uri, username, password);
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

}
