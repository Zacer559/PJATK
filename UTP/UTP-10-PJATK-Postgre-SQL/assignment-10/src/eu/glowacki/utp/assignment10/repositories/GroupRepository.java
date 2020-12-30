package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.persistence.PersistenceContext;

public class GroupRepository extends RepositoryBase<GroupDTO> implements IGroupRepository {
	private static final String TABLE_GROUPS = "groups";
	private static final String COLUMN_GROUP_ID = "group_id";
	private static final String COLUMN_GROUP_DESCRIPTION = "group_description";
	private static final String COLUMN_GROUP_NAME = "group_name";
	private static final String SEQUENCE_GROUP_SEQ = "group_seq";

	public GroupRepository(PersistenceContext persistenceContext) {
		super(persistenceContext);
	}

	public GroupDTO findById(int id) {
		try {
			List<GroupDTO> groups = new LinkedList<GroupDTO>();
			Connection connection = getConnection();
			final String statementString = "SELECT " + //
					COLUMN_GROUP_ID + ", " + COLUMN_GROUP_NAME + ", " + COLUMN_GROUP_DESCRIPTION + //
					" FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUP_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int groupId = resultSet.getInt(1);
				String groupName = resultSet.getString(2);
				String groupDescription = resultSet.getString(3);
				GroupDTO group = new GroupDTO(groupId, groupName, groupDescription);
				groups.add(group);
			}
			if (groups.isEmpty()) {
				return null;
			} else {
				return groups.get(0);
			}
		} catch (Exception ex) {
			throw new UnimplementedException(ex);
		}

	}

	public void add2(GroupDTO group) {
		try {
			Connection connection = getConnection();
			final String statementString = "INSERT INTO " + TABLE_GROUPS + " (" //
					+ COLUMN_GROUP_ID + ", " //
					+ COLUMN_GROUP_NAME + ", " //
					+ COLUMN_GROUP_DESCRIPTION + //
					") VALUES (" + sequenceNextValue(SEQUENCE_GROUP_SEQ) + ", ?, ?)";//
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setString(1, group.getName());
			statement.setString(2, group.getDescription());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}

	}

	public void add(GroupDTO group) {
		try {
			Connection connection = getConnection();
			final String statementString = "INSERT INTO " + TABLE_GROUPS + " (" //
					+ COLUMN_GROUP_ID + ", " //
					+ COLUMN_GROUP_NAME + ", " //
					+ COLUMN_GROUP_DESCRIPTION + //
					") VALUES (" + "?" + ", ?, ?)";//
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, group.getId());
			statement.setString(2, group.getName());
			statement.setString(3, group.getDescription());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}

	}

	public void update(GroupDTO group) {
		try {
			Connection connection = getConnection();
			final String statementString = "UPDATE " + TABLE_GROUPS + " SET " + COLUMN_GROUP_NAME + " = ?, "//
					+ COLUMN_GROUP_DESCRIPTION + " = ? WHERE " + COLUMN_GROUP_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setString(1, group.getName());
			statement.setString(2, group.getDescription());
			statement.setInt(3, group.getId());
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	public List<GroupDTO> findByName(String name) {
		try {
			List<GroupDTO> groups = new LinkedList<GroupDTO>();
			Connection connection = getConnection();
			final String statementString = "SELECT " + //
					COLUMN_GROUP_ID + ", " + COLUMN_GROUP_NAME + ", " + COLUMN_GROUP_DESCRIPTION + //
					" FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUP_NAME + " LIKE ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int groupId = resultSet.getInt(1);
				String groupName = resultSet.getString(2);
				String groupDescription = resultSet.getString(3);
				GroupDTO group = new GroupDTO(groupId, groupName, groupDescription);
				groups.add(group);
			}
			return groups;
		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

	protected String getTableName() {
		return TABLE_GROUPS;
	}

	protected String getIdColumnName() {
		return COLUMN_GROUP_ID;
	}

	public void delete(GroupDTO user) {
		try {
			Connection connection = getConnection();
			final String statementString = "DELETE FROM " + TABLE_GROUPS + " WHERE " + COLUMN_GROUP_ID + " = ?";
			PreparedStatement statement = connection.prepareStatement(statementString);
			statement.setInt(1, user.getId());
			statement.executeUpdate();

		} catch (SQLException ex) {
			throw new UnimplementedException(ex);
		}
	}

}
