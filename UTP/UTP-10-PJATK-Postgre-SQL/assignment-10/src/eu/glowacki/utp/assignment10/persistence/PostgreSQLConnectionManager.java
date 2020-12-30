package eu.glowacki.utp.assignment10.persistence;

public final class PostgreSQLConnectionManager extends ConnectionManager {

	private static final String POSTGRESQL_DRIVER_NAME = "org.postgresql.Driver";

	protected String getDriverClassName() {
		return POSTGRESQL_DRIVER_NAME;
	}
}
