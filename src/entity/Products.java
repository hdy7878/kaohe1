package entity;

public class Products {

	private int productID;
	private String product_name;
	private double income_price;
	private Providers pv;
	private int quantity;
	private double sale_price;
	private Categorys cg;
	private String income_time;

	public Products(int productID, String product_name, double income_price, Providers pv, int quantity,
			double sale_price, Categorys cg, String income_time) {
		super();
		this.productID = productID;
		this.product_name = product_name;
		this.income_price = income_price;
		this.pv = pv;
		this.quantity = quantity;
		this.sale_price = sale_price;
		this.cg = cg;
		this.income_time = income_time;
	}

	public Products(int productID, String product_name, double income_price, Providers pv, int quantity,
			double sale_price, Categorys cg) {
		super();
		this.productID = productID;
		this.product_name = product_name;
		this.income_price = income_price;
		this.pv = pv;
		this.quantity = quantity;
		this.sale_price = sale_price;
		this.cg = cg;
	}

	public Products(String product_name, double income_price, Providers pv, int quantity, double sale_price,
			Categorys cg, String income_time) {
		super();
		this.product_name = product_name;
		this.income_price = income_price;
		this.pv = pv;
		this.quantity = quantity;
		this.sale_price = sale_price;
		this.cg = cg;
		this.income_time = income_time;
	}

	public Products(int productID) {
		super();
		this.productID = productID;
	}

	public Products(String product_name, double income_price, Providers pv, int quantity, double sale_price,
			Categorys cg) {
		super();
		this.product_name = product_name;
		this.income_price = income_price;
		this.pv = pv;
		this.quantity = quantity;
		this.sale_price = sale_price;
		this.cg = cg;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getIncome_price() {
		return income_price;
	}

	public void setIncome_price(double income_price) {
		this.income_price = income_price;
	}

	public Providers getPv() {
		return pv;
	}

	public void setPv(Providers pv) {
		this.pv = pv;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public Categorys getCg() {
		return cg;
	}

	public void setCg(Categorys cg) {
		this.cg = cg;
	}

	public String getIncome_time() {
		return income_time;
	}

	public void setIncome_time(String income_time) {
		this.income_time = income_time;
	}

	@Override
	public String toString() {
		return "Products [productID=" + productID + ", product_name=" + product_name + ", income_price=" + income_price
				+ ", pv=" + pv + ", quantity=" + quantity + ", sale_price=" + sale_price + ", cg=" + cg
				+ ", income_time=" + income_time + "]";
	}

}
