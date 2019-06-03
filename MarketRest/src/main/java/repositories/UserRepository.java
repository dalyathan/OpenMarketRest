package repositories;

import org.springframework.data.repository.CrudRepository;

import models.User;

public interface UserRepository extends CrudRepository<Long,User>{
	User findByUsername(String username);
}
