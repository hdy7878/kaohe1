package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Providers;
import until.Page;

public interface ProviderDao {
	// ��
	public int insertProviders(Connection conn, Providers providers) throws SQLException;

	// ��
	public ResultSet selectProvidersRecord(Connection conn, String name) throws SQLException;

	// ��
	public ResultSet selectProvidersRecord(Connection conn, int id) throws SQLException;

	// ��
	public ResultSet selectProvidersRecord(Connection conn, String pvname, int id) throws SQLException;

	// ��
	public int updateProviders(Connection conn, Providers providers) throws SQLException;

	// ɾ
	public int deleteProvidersById(Connection conn, int id) throws SQLException;

	// ��
	public int getTotalRecord(Connection conn) throws SQLException;

	// ��
	public ResultSet selectProvidersByPage(Connection conn, Page<Providers> page) throws SQLException;
}
