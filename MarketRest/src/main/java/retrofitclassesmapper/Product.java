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
}
