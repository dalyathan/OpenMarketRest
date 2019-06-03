package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private String type;
	private int amount;
	private double price;
	private String status;
	private String pictureId;
	@ManyToMany(mappedBy="myProducts")
	private Set<User> owners;
	@OneToOne(mappedBy="product")
	private Rating rating;
}
