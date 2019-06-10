package services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.User;
import repositories.UserRepository;

@Service
public class UserRepositoryService {
	private UserRepository  userRepository;
	
	@Autowired
	public UserRepositoryService(UserRepository  userRepository) {
		this.userRepository=userRepository;
	}
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	public Optional<User> findById(long id) {
		return this.userRepository.findById(id);
	}
	public User save(User user) {
		return this.userRepository.save(user);
	}
	public boolean existsById(long id) {
		return this.userRepository.existsById(id);
	}
	public void deleteById(long id) {
		this.userRepository.deleteById(id);
	}
}
