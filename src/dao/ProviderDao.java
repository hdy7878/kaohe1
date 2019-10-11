package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Providers;
import until.Page;

public interface ProviderDao {
	// 增
	public int insertProviders(Connection conn, Providers providers) throws SQLException;

	// 查
	public ResultSet selectProvidersRecord(Connection conn, String name) throws SQLException;

	// 查
	public ResultSet selectProvidersRecord(Connection conn, int id) throws SQLException;

	// 查
	public ResultSet selectProvidersRecord(Connection conn, String pvname, int id) throws SQLException;

	// 改
	public int updateProviders(Connection conn, Providers providers) throws SQLException;

	// 删
	public int deleteProvidersById(Connection conn, int id) throws SQLException;

	// 查
	public int getTotalRecord(Connection conn) throws SQLException;

	// 查
	public ResultSet selectProvidersByPage(Connection conn, Page<Providers> page) throws SQLException;
}
