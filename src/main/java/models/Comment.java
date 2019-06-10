package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

@Entity(name="comments")
@Data
public class Comment{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	private String commentdata;
	private String dateOfComment;
	private String userName;
	@ManyToOne
	private Product product;
	public Comment(retrofitclassesmapper.Comment comment) {
		this.id=comment.getId();
		this.commentdata=comment.getCommentdata();
		this.dateOfComment=comment.getDateOfComment();
		this.userName=comment.getUserName();
	}
	public void updateComment(retrofitclassesmapper.Comment comment) {
		this.commentdata=comment.getCommentdata();
		this.dateOfComment=comment.getDateOfComment();
		this.userName=comment.getUserName();
	}
}
