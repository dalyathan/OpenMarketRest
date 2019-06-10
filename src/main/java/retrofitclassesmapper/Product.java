package retrofitclassesmapper;

import lombok.Data;

@Data
public class Product {
	private long id;
	private String imagePath;
	private String name;
	private String type;
	private String description;
	private int amount;
	private double price;
	private String date;
	private String userName;
	
	public Product(models.Product productModel) {
		this.id=productModel.getId();
		this.imagePath=productModel.getImagePath();
		this.name=productModel.getName();
		this.type=productModel.getType();
		this.description=productModel.getDescription();
		this.amount=productModel.getAmount();
		this.price=productModel.getPrice();
		this.date=productModel.getDate();
		this.userName=productModel.getOwner().getUsername();
	}
}
