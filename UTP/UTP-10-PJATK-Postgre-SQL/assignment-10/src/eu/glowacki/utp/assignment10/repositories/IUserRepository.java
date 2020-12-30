package eu.glowacki.utp.assignment10.repositories;

import java.util.List;

import eu.glowacki.utp.assignment10.dtos.UserDTO;

public interface IUserRepository extends IRepository<UserDTO> {

	List<UserDTO> findByName(String username);
}