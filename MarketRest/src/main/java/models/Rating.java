package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Rating {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int likeNo;
	private int dislikeNo;
	private int ratingNo;
	@OneToOne
	private Product product;
}
