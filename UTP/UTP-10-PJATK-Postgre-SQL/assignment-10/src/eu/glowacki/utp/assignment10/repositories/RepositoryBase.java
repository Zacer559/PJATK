package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.persistence.PersistenceContext;

public abstract class RepositoryBase<TEntity extends DTOBase> implements IRepository<TEntity> {
	private static final String TRANSACTION_BEGIN = "BEGIN";
	private static final String TRANSACTION_COMMIT = "COMMIT";
	private static final String TRANSACTION_ROLLBACK = "ROLLBACK";

	private final PersistenceContext _context;

	protected RepositoryBase(PersistenceContext context) {
		_context = context;
	}

	public final PersistenceContext getContext() {
		return _context;
	}

	public final Connection getConnection() {
		return getContext().getConnection();
	}

	public final void addOrUpdate(TEntity dto) {
		if (exists(dto)) {
			update((dto));
		} else {
			add(dto);
		}

	}

	public final boolean exists(TEntity dto) {
		boolean exists = false;
		if (dto.hasExistingId()) {
			TEntity savedEntity = findById(dto.getId());
			exists = savedEntity != null;
		}
		return exists;
	}

	public final void beginTransaction() {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(TRANSACTION_BEGIN);
			statement.execute();
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public final void commitTransaction() {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(TRANSACTION_COMMIT);
			statement.execute();
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public final void rollbackTransaction() {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(TRANSACTION_ROLLBACK);
			statement.execute();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public int getCount() {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(getCountQuery());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count;
			} else {
				throw new UnimplementedException("Cannot be iterated");
			}
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public int getCountUserGroups() {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT COUNT (" + GroupUserInfo.getUser() + ") FROM " + GroupUserInfo.getName());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count;
			} else {
				throw new UnimplementedException("Cannot be iterated");
			}
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	protected final String sequenceNextValue(String sequenceName) {
		return "NEXTVAL('" + sequenceName + "')";
		// return "nextval('group_seq')";
	}

	protected abstract String getTableName();

	protected abstract String getIdColumnName();

	private String getCountQuery() {
		String query = "SELECT COUNT (" + getIdColumnName() + ") FROM " + getTableName();
		return query;
	}

	public void addUsertoGroup(UserDTO user, GroupDTO group) {
		try {
			Connection connection = getConnection();
			final String statementString = "INSERT INTO " + GroupUserInfo.getName() + " (" //
					+ GroupUserInfo.getUser() + ", " //
					+ GroupUserInfo.getGroup() + //
					") VALUES ( ?, ?)";//
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, user.getId());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public void removeUserfromGroup(UserDTO user, GroupDTO group) {
		try {
			Connection connection = getConnection();
			final String statementString = "DELETE FROM " + GroupUserInfo.getName() + " WHERE "
					+ GroupUserInfo.getUser() + " = ? AND " + GroupUserInfo.getGroup() + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, user.getId());
			statement.setInt(2, group.getId());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public void removeManyUsersfromGroup(List<UserDTO> users, GroupDTO group) {
		users.forEach((n) -> removeUserfromGroup(n, group));

	}

	public void removeUserfromManyGroups(UserDTO user, List<GroupDTO> groups) {
		groups.forEach((n) -> removeUserfromGroup(user, n));
	}

	public void removeManyUsersfromManyGroups(List<UserDTO> users, List<GroupDTO> groups) {
		groups.forEach((n) -> removeManyUsersfromGroup(users, n));
	}

	public void addManyUserstoGroup(List<UserDTO> users, GroupDTO group) {
		users.forEach((n) -> addUsertoGroup(n, group));
	}

	public void addUsertoManyGroups(UserDTO user, List<GroupDTO> groups) {
		groups.forEach((n) -> addUsertoGroup(user, n));
	}

	public void addManyUserstoManyGroups(List<UserDTO> users, List<GroupDTO> groups) {
		groups.forEach((n) -> addManyUserstoGroup(users, n));
	}

}
