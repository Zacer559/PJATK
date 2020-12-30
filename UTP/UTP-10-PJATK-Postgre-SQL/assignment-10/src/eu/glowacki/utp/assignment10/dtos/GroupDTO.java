package eu.glowacki.utp.assignment10.dtos;

import java.util.LinkedList;
import java.util.List;

public class GroupDTO extends DTOBase {

	private String _name;
	private String _description;
	private List<UserDTO> _users;

	public GroupDTO() {
	}

	public GroupDTO(int id, String name, String description) {
		super(id);
		_name = name;
		_description = description;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public List<UserDTO> getUsers() {
		return _users;
	}

	public void setUsers(List<UserDTO> users) {
		_users = users;
	}

	public void addUser(UserDTO user) {
		if (_users == null) {
			_users = new LinkedList<UserDTO>();
		}
		_users.add(user);
	}

	public void deleteUser(UserDTO user) {
		if (_users != null) {
			_users.remove(user);
		}
	}
}