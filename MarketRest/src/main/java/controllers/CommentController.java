package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.Comment;
import repositories.CommentRepository;

@RestController
@RequestMapping("/comment")
public class CommentController {
	private CommentRepository commentRepository;
	@Autowired
	public CommentController(CommentRepository commentRepository) {
		this.commentRepository=commentRepository;
	}
	@GetMapping("/{subject}")
	public @ResponseBody List<Comment> getRecentComments(
			@PathVariable("subject") String subject){
		List<Comment> recentComments=this.commentRepository.findBySubjectAndSeen(subject, true);
		recentComments.iterator().forEachRemaining(comment->{
			comment.setSeen(true);
			this.commentRepository.save(comment);
		});
		return recentComments;
	}
	@PostMapping("/comment")
	public @ResponseBody void comment(@RequestParam("subject") String subject,
			@RequestParam("content") String content) {
		Comment comment=new Comment();
		comment.setComment(content);
//		comment.setSeen(false); ?????
		comment.setSubject(subject);
		this.commentRepository.save(comment);
	}
}
