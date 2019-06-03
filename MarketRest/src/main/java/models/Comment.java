package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String writerName;
	private String comment;
	private String subject;
	private boolean seen;
	private Date commented;
}
