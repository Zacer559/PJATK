package eu.glowacki.utp.assignment10.repositories.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	protected IGroupRepository getRepositoryFromPersistenceContext() {
		IGroupRepository repository = getContext().getGroupRepository();
		return repository;
	}

	@Test
	public void add() {
		IGroupRepository repository = getRepository();
		int count = repository.getCount();
		GroupDTO group = new GroupDTO(0, "groupname-1", "groupname description");
		repository.add(group);
		int expectedCount = count + 1;
		int actualCount = repository.getCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() {
		IGroupRepository repository = getRepository();
		final String originalName = "original groupname-1";
		GroupDTO group = new GroupDTO(0, originalName, "original groupname-1 description");
		repository.add(group);
		List<GroupDTO> fetchedOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(fetchedOriginalList);
		Assert.assertEquals(1, fetchedOriginalList.size());
		GroupDTO fetchedOriginal = fetchedOriginalList.get(0);
		Assert.assertNotNull(fetchedOriginal);
		Assert.assertEquals(originalName, fetchedOriginal.getName());

	}

	@Test
	public void addOrUpdate() {
		IGroupRepository repository = getRepository();
		int count = repository.getCount();
		GroupDTO group = new GroupDTO(5, "groupname-1", "groupname");
		repository.addOrUpdate(group);
		int expectedCount = count + 1;
		int actualCount = repository.getCount();
		Assert.assertEquals(expectedCount, actualCount);

		final String originalName = "original groupname-1";
		group = new GroupDTO(5, originalName, "original groupname-1 description");
		repository.addOrUpdate(group);
		List<GroupDTO> fetchedOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(fetchedOriginalList);
		Assert.assertEquals(1, fetchedOriginalList.size());
		GroupDTO fetchedOriginal = fetchedOriginalList.get(0);
		Assert.assertNotNull(fetchedOriginal);
		Assert.assertEquals(originalName, fetchedOriginal.getName());

	}

	@Test
	public void delete() {
		IGroupRepository repository = getRepository();
		int count = repository.getCount();
		GroupDTO group = new GroupDTO(0, "groupname-1", "groupname");
		repository.add(group);
		Assert.assertEquals(count + 1, repository.getCount());
		repository.delete(group);
		Assert.assertEquals(count, repository.getCount());
	}

	@Test
	public void findById() {
		IGroupRepository repository = getRepository();
		final String originalName = "FindMyID";
		final int id = 666;
		GroupDTO group = new GroupDTO(id, originalName, "Some Description");
		repository.add(group);
		GroupDTO founded = repository.findById(id);
		Assert.assertNotNull(founded);
		Assert.assertEquals(id, founded.getId());
		Assert.assertEquals(originalName, founded.getName());
	}

	@Test
	public void findByName() {
		IGroupRepository repository = getRepository();
		final String originalName = "FindMyName!!!";
		GroupDTO group = new GroupDTO(0, originalName, "Some Description");
		repository.add(group);
		List<GroupDTO> fetchedOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(fetchedOriginalList);
		Assert.assertEquals(1, fetchedOriginalList.size());
		GroupDTO fetchedOriginal = fetchedOriginalList.get(0);
		Assert.assertNotNull(fetchedOriginal);
		Assert.assertEquals(originalName, fetchedOriginal.getName());
	}

	@Override
	protected IGroupRepository Create() {
		IGroupRepository repository = new GroupRepository(getContext());
		return repository;
	}

}
