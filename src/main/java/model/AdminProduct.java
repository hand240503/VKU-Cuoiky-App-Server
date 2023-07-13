package model;

public class AdminProduct {
	private int idOrderdetails;
	private int idUser;
	private String nameProduct;
	private int quantity;
	private float value;
	private int unit;
	private String nameUnit;
	public AdminProduct() {
		
	}

	public AdminProduct(int idOrderdetails, int idUser, String nameProduct, int quantity, float value,int unit,String nameUnit) {
		this.idOrderdetails = idOrderdetails;
		this.idUser = idUser;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.value = value;
		this.unit = unit;
		this.nameUnit = nameUnit;
	}

	public int getIdOrderdetails() {
		return idOrderdetails;
	}

	public void setIdOrderdetails(int idOrderdetails) {
		this.idOrderdetails = idOrderdetails;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getNameUnit() {
		return nameUnit;
	}

	public void setNameUnit(String nameUnit) {
		this.nameUnit = nameUnit;
	}
	
	
	

}
