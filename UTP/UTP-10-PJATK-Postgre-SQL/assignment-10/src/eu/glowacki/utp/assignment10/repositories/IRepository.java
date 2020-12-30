package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.util.List;

import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

public interface IRepository<TDTO extends DTOBase> {

	Connection getConnection();

	void add(TDTO dto);

	void add2(TDTO dto);

	void update(TDTO dto);

	void addOrUpdate(TDTO dto);

	void addUsertoGroup(UserDTO user, GroupDTO group);

	void addManyUserstoGroup(List<UserDTO> user, GroupDTO group);

	void addUsertoManyGroups(UserDTO user, List<GroupDTO> groups);

	void addManyUserstoManyGroups(List<UserDTO> users, List<GroupDTO> group);

	void removeUserfromGroup(UserDTO user, GroupDTO group);

	void removeManyUsersfromGroup(List<UserDTO> user, GroupDTO group);

	void removeUserfromManyGroups(UserDTO user, List<GroupDTO> groups);

	void removeManyUsersfromManyGroups(List<UserDTO> users, List<GroupDTO> group);

	int getCountUserGroups();

	void delete(TDTO dto);

	TDTO findById(int id);

	void beginTransaction();

	void commitTransaction();

	void rollbackTransaction();

	int getCount();

	boolean exists(TDTO dto);
}