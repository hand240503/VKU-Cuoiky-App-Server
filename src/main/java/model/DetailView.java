package model;

public class DetailView {
	private int idproduct;
	private String nameProduct;
	private String nameUnit;
	private int ratio;
	private double value;
	
	private int idPrice;
	private int idUser;

	public DetailView() {

	}
	private int quantity;
	public DetailView(int idproduct, String nameProduct, String nameUnit, int ratio, double value, int quantity,
			int idPrice, int idUser) {
		this.idproduct = idproduct;
		this.nameProduct = nameProduct;
		this.nameUnit = nameUnit;
		this.ratio = ratio;
		this.value = value;
		this.idUser = idUser;
		this.idPrice = idPrice;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getIdPrice() {
		return idPrice;
	}

	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	

}
