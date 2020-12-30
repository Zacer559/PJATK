package eu.glowacki.utp.assignment10.repositories.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;
import eu.glowacki.utp.assignment10.repositories.UserRepository;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

	@Test
	public void add() {
		IUserRepository repository = getRepository();
		int count = repository.getCount();
		UserDTO user = new UserDTO(0, "username-1", "password1");
		repository.add(user);
		int expectedCount = count + 1;
		int actualCount = repository.getCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() {
		IUserRepository repository = getRepository();
		final String originalName = "original Username-1";
		UserDTO user = new UserDTO(0, originalName, "original Username-1 description");
		repository.add(user);
		List<UserDTO> fetchedOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(fetchedOriginalList);
		Assert.assertEquals(1, fetchedOriginalList.size());
		UserDTO fetchedOriginal = fetchedOriginalList.get(0);
		Assert.assertNotNull(fetchedOriginal);
		Assert.assertEquals(originalName, fetchedOriginal.getLogin());

	}

	@Test
	public void addOrUpdate() {
		IUserRepository repository = getRepository();
		int count = repository.getCount();
		UserDTO user = new UserDTO(5, "user1", "pass1");
		repository.addOrUpdate(user);
		int expectedCount = count + 1;
		int actualCount = repository.getCount();
		Assert.assertEquals(expectedCount, actualCount);

		final String originalName = "original username-1";
		user = new UserDTO(5, originalName, "pass11");
		repository.addOrUpdate(user);
		List<UserDTO> fetchedOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(fetchedOriginalList);
		Assert.assertEquals(1, fetchedOriginalList.size());
		UserDTO fetchedOriginal = fetchedOriginalList.get(0);
		Assert.assertNotNull(fetchedOriginal);
		Assert.assertEquals(originalName, fetchedOriginal.getLogin());
	}

	@Test
	public void delete() {
		IUserRepository repository = getRepository();
		int count = repository.getCount();
		UserDTO user = new UserDTO(0, "user1", "pass1");
		repository.add(user);
		Assert.assertEquals(count + 1, repository.getCount());
		repository.delete(user);
		Assert.assertEquals(count, repository.getCount());
	}

	@Test
	public void findById() {
		IUserRepository repository = getRepository();
		final String originalName = "FindMyID";
		final int id = 666;
		UserDTO user = new UserDTO(id, originalName, "Some Description");
		repository.add(user);
		UserDTO founded = repository.findById(id);
		Assert.assertNotNull(founded);
		Assert.assertEquals(id, founded.getId());
		Assert.assertEquals(originalName, founded.getLogin());
	}

	@Test
	public void findByName() {
		IUserRepository repository = getRepository();
		final String originalName = "FindMyName!!!";
		UserDTO user = new UserDTO(0, originalName, "Some Password");
		repository.add(user);
		List<UserDTO> fetchedOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(fetchedOriginalList);
		Assert.assertEquals(1, fetchedOriginalList.size());
		UserDTO fetchedOriginal = fetchedOriginalList.get(0);
		Assert.assertNotNull(fetchedOriginal);
		Assert.assertEquals(originalName, fetchedOriginal.getLogin());
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