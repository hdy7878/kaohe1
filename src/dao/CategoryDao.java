package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Categorys;

import until.Page;

public interface CategoryDao {
	// ��
	public int insertCategorys(Connection conn, Categorys categorys) throws SQLException;

	// ��
	public ResultSet selectCategorysRecord(Connection conn, String name) throws SQLException;

	// ��
	public ResultSet selectCategorysRecord(Connection conn, int id) throws SQLException;

	// ��
	public int getTotalRecord(Connection conn) throws SQLException;

	// ��
	public ResultSet selectCategorysByPage(Connection conn, Page<Categorys> page) throws SQLException;

	// ��
	public ResultSet selectCategorysRecord(Connection conn, String cgname, int id) throws SQLException;

	// ��
	public int updateCategorys(Connection conn, Categorys categorys) throws SQLException;

	// ɾ
	public int deleteCategorysById(Connection conn, int id) throws SQLException;
}
