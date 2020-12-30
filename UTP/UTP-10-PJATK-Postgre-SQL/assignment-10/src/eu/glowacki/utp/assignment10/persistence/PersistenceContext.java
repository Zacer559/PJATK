package eu.glowacki.utp.assignment10.persistence;

import java.sql.Connection;

import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;
import eu.glowacki.utp.assignment10.repositories.UserRepository;

public class PersistenceContext {

	private final ConnectionManager _connectionManager;
	private final ConnectionManagerConfiguration _connectionManagerConfiguration;
	private IGroupRepository _groupRepository;
	private IUserRepository _userRepository;
	private Connection _connection;

	public PersistenceContext(ConnectionManager manager, ConnectionManagerConfiguration configuration) {
		_connectionManager = manager;
		_connectionManagerConfiguration = configuration;
	}

	public Connection getConnection() {
		if (_connection == null) {
			_connection = _connectionManager.getConnection(_connectionManagerConfiguration);
		}
		return _connection;
	}

	public IGroupRepository getGroupRepository() {
		if (_groupRepository == null) {
			_groupRepository = new GroupRepository(this);
		}
		return _groupRepository;
	}

	public IUserRepository getUserRepository() {
		if (_userRepository == null) {
			 _userRepository = new UserRepository(this);
		}
		return _userRepository;
	}
}
