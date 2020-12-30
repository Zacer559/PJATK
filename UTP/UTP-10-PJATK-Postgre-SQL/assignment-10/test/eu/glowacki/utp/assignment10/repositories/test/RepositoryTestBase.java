package eu.glowacki.utp.assignment10.repositories.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.persistence.ConnectionManager;
import eu.glowacki.utp.assignment10.persistence.ConnectionManagerConfiguration;
import eu.glowacki.utp.assignment10.persistence.PersistenceContext;
import eu.glowacki.utp.assignment10.persistence.PostgreSQLConnectionManager;
import eu.glowacki.utp.assignment10.repositories.IRepository;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

	private static final String URI = "jdbc:postgresql://localhost:5432/utp-task10";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";
	private static final PersistenceContext _context;

	static {
		ConnectionManager manager = new PostgreSQLConnectionManager();
		ConnectionManagerConfiguration configuration = new ConnectionManagerConfiguration(URI, USERNAME, PASSWORD);
		_context = new PersistenceContext(manager, configuration);

	}
	private TRepository _repository;

	protected PersistenceContext getContext() {
		return _context;
	}

	protected TRepository getRepository() {
		if (_repository == null) {
			_repository = getRepositoryFromPersistenceContext();
		}
		Assert.assertNotNull(_repository);
		// Assert.assertNotNull(_repository.getContext());
		Assert.assertNotNull(_repository.getConnection());
		return _repository;
	}

	@Before
	public void before() {
		_repository = Create();
		if (_repository != null) {
			_repository.beginTransaction();
		}
	}

	@After
	public void after() {
		if (_repository != null) {
			_repository.rollbackTransaction();
		}
	}

	@SuppressWarnings("unused")
	private void beginTransaction() {
		TRepository repository = getRepository();
		repository.beginTransaction();
	}

	@SuppressWarnings("unused")
	private void rollbackTransaction() {
		TRepository repository = getRepository();
		repository.rollbackTransaction();
	}

	@SuppressWarnings("unused")
	private void commitTransaction() {
		TRepository repository = getRepository();
		repository.commitTransaction();
	}

	protected abstract TRepository Create();

	protected abstract TRepository getRepositoryFromPersistenceContext();
}