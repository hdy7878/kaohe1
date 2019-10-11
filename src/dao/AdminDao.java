package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;

public interface AdminDao {
	// ��
	public int insertAdmin(Connection conn, Admin admin) throws SQLException;

	// ɾ
	public int deleteAdminById(Connection conn, int id) throws SQLException;

	// ��
	public int updateAdmin(Connection conn, Admin admin) throws SQLException;

	// ��
	public ResultSet selectAdminByLogin(Connection conn, Admin admin) throws SQLException;

	// ��
	public ResultSet selectAdminByPage(Connection conn) throws SQLException;

	// ��
	public ResultSet selectAdminRecord(Connection conn, String name) throws SQLException;
}
