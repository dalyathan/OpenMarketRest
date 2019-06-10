package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Comment;
import models.Product;
import repositories.CommentRepository;

@Service
public class CommentRepositoryService {
	
	private CommentRepository commentRepository;
	
	@Autowired
	public CommentRepositoryService(CommentRepository commentRepository) {
		this.commentRepository=commentRepository;
	}
	
	
	public boolean existsById(long id) {
		return this.commentRepository.existsById(id);
	}
	
	
	public void deleteById(long id) {
		this.commentRepository.deleteById(id);
	}
	
	
	public Comment save(Comment entity) {
		return this.commentRepository.save(entity);
	}
	
	
	public List<Comment> findByUserName(String username) {
		return this.commentRepository.findByUserName(username);
	}
	
	public Optional<Comment> findById(long id) {
		return this.commentRepository.findById(id);
	}
	
	public List<Comment> findByProduct(Product product){
		return this.commentRepository.findByProduct(product);
	}
}
