package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

@Entity
@Data
public class Product{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	private String imagePath;
	private String name;
	private String type;
	private String description;
	private int amount;
	private double price;
	@OneToMany(mappedBy="myProducts")
	private User owner;
	@OneToOne(mappedBy="product")
	private Rating rating;
	@OneToMany(mappedBy="productCommentJoin")
	private Set<Comment> productCommentJoin;
	private String date;
	
	public Product(retrofitclassesmapper.Product product) {
		this.id=product.getId();
		setAttributes(product);
	}
	
	public void updateProduct(retrofitclassesmapper.Product product) {
		setAttributes(product);
	}
	private void setAttributes(retrofitclassesmapper.Product product) {
		this.imagePath=product.getImagePath();
		this.name=product.getName();
		this.type=product.getType();
		this.description=product.getDescription();
		this.amount=product.getAmount();
		this.price=product.getPrice();
	}
}
