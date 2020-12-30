package eu.glowacki.utp.assignment10.repositories.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;
import eu.glowacki.utp.assignment10.repositories.UserRepository;

public class GroupUserTest extends RepositoryTestBase<UserDTO, IUserRepository> {
	@Test
	public void addUserToGroup() {
		IUserRepository repository = getRepository();
		final String originalName = "FindMyName!!!";
		int count = repository.getCountUserGroups();
		UserDTO user = new UserDTO(0, originalName, "Some Password");
		repository.add(user);
		IGroupRepository repository2 = new GroupRepository(getContext());
		GroupDTO group = new GroupDTO(0, "Grupa A", "Opis grupy A");
		repository2.add(group);
		repository2.addUsertoGroup(user, group);
		Assert.assertEquals(count + 1, repository.getCountUserGroups());
	}

	@Test
	public void removeUserFromGroup() {
		IUserRepository repository = getRepository();
		int count = repository.getCountUserGroups();
		final String originalName = "FindMyName!!!";
		UserDTO user = new UserDTO(0, originalName, "Some Password");
		repository.add(user);
		IGroupRepository repository2 = new GroupRepository(getContext());
		GroupDTO group = new GroupDTO(0, "Grupa A", "Opis grupy A");
		repository2.add(group);
		repository2.addUsertoGroup(user, group);
		Assert.assertEquals(count + 1, repository.getCountUserGroups());
		repository2.removeUserfromGroup(user, group);
		Assert.assertEquals(count, repository.getCountUserGroups());
	}

	@Test
	public void AddAndRemoveManyToMany() {
		IUserRepository repository = getRepository();

		IGroupRepository repository2 = new GroupRepository(getContext());
		int count = repository2.getCountUserGroups();
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		List<UserDTO> users = new ArrayList<UserDTO>();
		UserDTO user = new UserDTO(0, "Someone1", "Some Password");
		repository.add(user);
		users.add(user);
		user = new UserDTO(1, "Someone2", "Some Password");
		repository.add(user);
		users.add(user);
		user = new UserDTO(2, "Someone3", "Some Password");
		repository.add(user);
		users.add(user);
		GroupDTO group = new GroupDTO(0, "Grupa A", "Opis grupy A");
		repository2.add(group);
		groups.add(group);
		group = new GroupDTO(1, "Grupa B", "Opis grupy B");
		repository2.add(group);
		groups.add(group);
		group = new GroupDTO(2, "Grupa C", "Opis grupy C");
		repository2.add(group);
		groups.add(group);
		repository2.addManyUserstoManyGroups(users, groups);
		Assert.assertEquals(users.size() * groups.size() + count, repository2.getCountUserGroups());
		repository2.removeManyUsersfromManyGroups(users, groups);
		Assert.assertEquals(count, repository2.getCountUserGroups());
	}

	protected IUserRepository Create() {
		IUserRepository repository = new UserRepository(getContext());
		return repository;
	}

	protected IUserRepository getRepositoryFromPersistenceContext() {
		IUserRepository repository = getContext().getUserRepository();
		return repository;
	}

}
