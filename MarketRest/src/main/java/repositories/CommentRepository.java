package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import models.Comment;

public interface CommentRepository extends CrudRepository<Long,Comment>{
	List<Comment> findBySubjectAndSeen(String subject,boolean seen);
}
