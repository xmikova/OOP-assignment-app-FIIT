package characters;

public abstract class Sword {
	private int productNumber;

	public Sword(int productNumber) {
		this.productNumber = productNumber;
	}
	
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
}
