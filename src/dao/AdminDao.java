package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;

public interface AdminDao {
	// 增
	public int insertAdmin(Connection conn, Admin admin) throws SQLException;

	// 删
	public int deleteAdminById(Connection conn, int id) throws SQLException;

	// 改
	public int updateAdmin(Connection conn, Admin admin) throws SQLException;

	// 查
	public ResultSet selectAdminByLogin(Connection conn, Admin admin) throws SQLException;

	// 查
	public ResultSet selectAdminByPage(Connection conn) throws SQLException;

	// 查
	public ResultSet selectAdminRecord(Connection conn, String name) throws SQLException;
}
