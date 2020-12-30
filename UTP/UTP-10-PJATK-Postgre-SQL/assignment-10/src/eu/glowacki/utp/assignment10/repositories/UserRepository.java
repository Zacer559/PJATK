package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.persistence.PersistenceContext;

public class UserRepository extends RepositoryBase<UserDTO> implements IUserRepository {
	private static final String TABLE_USERS = "users";
	private static final String COLUMN_USER_ID = "user_id";
	private static final String COLUMN_USER_LOGIN = "user_login";
	private static final String COLUMN_USER_PASSWORD = "user_password";
	private static final String SEQUENCE_USER_SEQ = "user_seq";

	public UserRepository(PersistenceContext persistenceContext) {
		super(persistenceContext);
	}

	public UserDTO findById(int id) {
		try {
			List<UserDTO> users = new LinkedList<UserDTO>();
			Connection connection = getConnection();
			final String statementString = "SELECT " + //
					COLUMN_USER_ID + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + //
					" FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt(1);
				String userLogin = resultSet.getString(2);
				String userPassword = resultSet.getString(3);
				UserDTO user = new UserDTO(userId, userLogin, userPassword);
				users.add(user);
			}
			if (users.isEmpty()) {
				return null;
			} else {
				return users.get(0);
			}
		} catch (Exception ex) {
			throw new UnimplementedException(ex);
		}

	}

	public void add2(UserDTO user) {
		try {
			Connection connection = getConnection();
			final String statementString = "INSERT INTO " + TABLE_USERS + " (" //
					+ COLUMN_USER_ID + ", " //
					+ COLUMN_USER_LOGIN + ", " //
					+ COLUMN_USER_PASSWORD + //
					") VALUES (" + sequenceNextValue(SEQUENCE_USER_SEQ) + ", ?, ?)";//
			PreparedStatement statement = connection.prepareStatement(statementString);

			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}

	}

	public void add(UserDTO user) {
		try {
			Connection connection = getConnection();
			final String statementString = "INSERT INTO " + TABLE_USERS + " (" //
					+ COLUMN_USER_ID + ", " //
					+ COLUMN_USER_LOGIN + ", " //
					+ COLUMN_USER_PASSWORD + //
					") VALUES ( ?, ?, ?)";//
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getPassword());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}

	}

	public void update(UserDTO user) {
		try {
			Connection connection = getConnection();
			final String statementString = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USER_LOGIN + " = ?, "//
					+ COLUMN_USER_PASSWORD + " = ? WHERE " + COLUMN_USER_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getId());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public List<UserDTO> findByName(String name) {
		try {
			List<UserDTO> users = new LinkedList<UserDTO>();
			Connection connection = getConnection();
			final String statementString = "SELECT " + //
					COLUMN_USER_ID + ", " + COLUMN_USER_LOGIN + ", " + COLUMN_USER_PASSWORD + //
					" FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_LOGIN + " LIKE ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt(1);
				String userLogin = resultSet.getString(2);
				String userPassword = resultSet.getString(3);
				UserDTO user = new UserDTO(userId, userLogin, userPassword);
				users.add(user);
			}
			return users;
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	protected String getTableName() {
		return TABLE_USERS;
	}

	protected String getIdColumnName() {
		return COLUMN_USER_ID;
	}

	public void delete(UserDTO user) {
		try {
			Connection connection = getConnection();
			final String statementString = "DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, user.getId());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

}
