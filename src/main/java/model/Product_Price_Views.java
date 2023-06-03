package model;

public class Product_Price_Views {
	private int idProduct;
	private String nameProduct;
	private String nameUnit;
	private int ratio;
	private double price;
	private int idPrice;

	public Product_Price_Views() {

	}

	public Product_Price_Views(int idProduct, String nameProduct, String nameUnit, int ratio, double price,
			int idPrice) {
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.nameUnit = nameUnit;
		this.ratio = ratio;
		this.price = price;
		this.idPrice = idPrice;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getNameUnit() {
		return nameUnit;
	}

	public void setNameUnit(String nameUnit) {
		this.nameUnit = nameUnit;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdPrice() {
		return idPrice;
	}

	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
	}

}
