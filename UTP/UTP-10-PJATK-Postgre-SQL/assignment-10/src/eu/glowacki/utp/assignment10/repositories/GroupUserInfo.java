package eu.glowacki.utp.assignment10.repositories;

public class GroupUserInfo {
	private static final String TABLE_NAME = "groups_users";
	private static final String GROUP_COLUMN = "group_id";
	private static final String USER_COLUMN = "user_id";

	public static String getName() {
		return TABLE_NAME;
	}

	public static String getGroup() {
		return GROUP_COLUMN;
	}

	public static String getUser() {
		return USER_COLUMN;
	}

}
