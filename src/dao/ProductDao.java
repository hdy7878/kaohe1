package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Products;

import until.Page;

public interface ProductDao {
	// 查所有信息
	public ResultSet selectAllProduct(Connection conn) throws SQLException;

	// 分页查信息
	public ResultSet selectProductsByPage(Connection conn, Page<Products> page) throws SQLException;

	// 得到所有记录
	public int getTotalRecord(Connection conn) throws SQLException;

	// 改
	public int updateProducts(Connection conn, Products products) throws SQLException;

	// 查
	public ResultSet selectProductsRecord(Connection conn, String name) throws SQLException;

	// 查
	public ResultSet selectProductsByproviderId(Connection conn, int id) throws SQLException;

	// 增
	public int insertProducts(Connection conn, Products products) throws SQLException;

	// 删
	public int deleteProductsById(Connection conn, int id) throws SQLException;

	// 查
	public ResultSet selectProductsBycategoryId(Connection conn, int id) throws SQLException;

	// 查
	public ResultSet selectProductsByproductId(Connection conn, int id) throws SQLException;

	// 查
	public ResultSet selectProductsRecord2(Connection conn, String name, int id) throws SQLException;
}
