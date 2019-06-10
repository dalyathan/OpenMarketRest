package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import models.Comment;
import models.Product;

public interface CommentRepository extends CrudRepository<Comment,Long>{
	List<Comment> findByUserName(String userName);
	List<Comment> findByProduct(Product product);
}
