package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Products;
import until.Page;

public class ProductDaoImp extends BaseDao implements ProductDao {

	@Override
	public ResultSet selectProductsByPage(Connection conn, Page<Products> page) throws SQLException {
		String sql = "select p.*,cg.category_name,pv.provider_name from products p,categorys cg,providers pv where p.categoryID=cg.categoryID and p.providerID=pv.providerID order by p.productID asc limit ?,?";
		Object[] objects = { (page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize() };
		return super.selectDate(conn, sql, objects);
	}

	@Override
	public ResultSet selectAllProduct(Connection conn) throws SQLException {
		String sql = "select p.*,cg.category_name,pv.provider_name from products p,categorys cg,providers pv where p.categoryID=cg.categoryID and p.providerID=pv.providerID order by p.productID asc";
		return super.selectDate(conn, sql, null);
	}

	@Override
	public int getTotalRecord(Connection conn) throws SQLException {
		String sql = "select count(productID) from products";
		ResultSet set = super.selectDate(conn, sql, null);
		if (set.next()) {
			return set.getInt(1);
		}
		return 0;
	}

	@Override
	public int updateProducts(Connection conn, Products products) throws SQLException {
		String sql = "update products set product_name=?,income_price=?,providerID=?,quantity=?,sales_price=?,categoryID=? where productID=?";
		return super.updateDate(conn, sql, products.getProduct_name(), products.getIncome_price(),
				products.getPv().getProviderID(), products.getQuantity(), products.getSale_price(),
				products.getCg().getCategoryID(), products.getProductID());
	}

	@Override
	public ResultSet selectProductsRecord(Connection conn, String name) throws SQLException {
		String sql = "select * from products where product_name=?";
		return super.selectDate(conn, sql, name);
	}

	@Override
	public int insertProducts(Connection conn, Products products) throws SQLException {
		String sql = "insert into products (product_name,income_price,providerID,quantity,sales_price,categoryID,income_time) value(?,?,?,?,?,?,now())";
		return super.updateDate(conn, sql, products.getProduct_name(), products.getIncome_price(),
				products.getPv().getProviderID(), products.getQuantity(), products.getSale_price(),
				products.getCg().getCategoryID());
	}

	@Override
	public int deleteProductsById(Connection conn, int id) throws SQLException {
		String sql = "delete from products where productID = ?";
		return super.updateDate(conn, sql, id);
	}

	@Override
	public ResultSet selectProductsByproviderId(Connection conn, int id) throws SQLException {
		String sql = "select * from products where providerID=?";
		return super.selectDate(conn, sql, id);
	}

	@Override
	public ResultSet selectProductsBycategoryId(Connection conn, int id) throws SQLException {
		String sql = "select * from products where categoryID=?";
		return super.selectDate(conn, sql, id);
	}

	@Override
	public ResultSet selectProductsByproductId(Connection conn, int id) throws SQLException {
		String sql = "select * from products where productID=?";
		return super.selectDate(conn, sql, id);
	}

	@Override
	public ResultSet selectProductsRecord2(Connection conn, String name, int id) throws SQLException {
		String sql = "select * from products where product_name=?and productID!=?";
		return super.selectDate(conn, sql, name, id);
	}

}
