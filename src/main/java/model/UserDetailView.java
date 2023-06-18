package model;

public class UserDetailView {

	private int id;
	private String nameProduct;
	private String unit;
	private int ratio;
	private double value;
	private int quantity;

	public UserDetailView() {

	}

	public UserDetailView(int id, String nameProduct, String unit, int ratio, double value, int quantity) {
		this.id = id;
		this.nameProduct = nameProduct;
		this.unit = unit;
		this.ratio = ratio;
		this.value = value;
		this.quantity = quantity;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
