package models;

public class Product {

	String name;
	String price;
	String type;
	String description;
	
	public Product(String name, String price, String type, String description) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", type=" + type + ", description=" + description + "]";
	}
	
	
	
	
}
