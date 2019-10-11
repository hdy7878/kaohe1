package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Products;

import until.Page;

public interface ProductDao {
	// ��������Ϣ
	public ResultSet selectAllProduct(Connection conn) throws SQLException;

	// ��ҳ����Ϣ
	public ResultSet selectProductsByPage(Connection conn, Page<Products> page) throws SQLException;

	// �õ����м�¼
	public int getTotalRecord(Connection conn) throws SQLException;

	// ��
	public int updateProducts(Connection conn, Products products) throws SQLException;

	// ��
	public ResultSet selectProductsRecord(Connection conn, String name) throws SQLException;

	// ��
	public ResultSet selectProductsByproviderId(Connection conn, int id) throws SQLException;

	// ��
	public int insertProducts(Connection conn, Products products) throws SQLException;

	// ɾ
	public int deleteProductsById(Connection conn, int id) throws SQLException;

	// ��
	public ResultSet selectProductsBycategoryId(Connection conn, int id) throws SQLException;

	// ��
	public ResultSet selectProductsByproductId(Connection conn, int id) throws SQLException;

	// ��
	public ResultSet selectProductsRecord2(Connection conn, String name, int id) throws SQLException;
}
