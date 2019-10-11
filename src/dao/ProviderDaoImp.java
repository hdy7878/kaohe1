package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Providers;
import until.Page;

public class ProviderDaoImp extends BaseDao implements ProviderDao {

	@Override
	public int insertProviders(Connection conn, Providers providers) throws SQLException {
		String sql = "insert into providers(provider_name,provider_add,provider_tel,account,email) value(?,?,?,?,?)";
		return super.updateDate(conn, sql, providers.getProvider_name(), providers.getProvider_add(),
				providers.getProvider_tel(), providers.getAccount(), providers.getEmail());
	}

	@Override
	public ResultSet selectProvidersRecord(Connection conn, String pvname) throws SQLException {
		String sql = "select * from Providers where provider_name=?";
		return super.selectDate(conn, sql, pvname);
	}

	@Override
	public int updateProviders(Connection conn, Providers providers) throws SQLException {
		String sql = "update providers set provider_name=?,provider_add=?,provider_tel=?,account=?,email=? where providerID=?";
		return super.updateDate(conn, sql, providers.getProvider_name(), providers.getProvider_add(),
				providers.getProvider_tel(), providers.getAccount(), providers.getEmail(), providers.getProviderID());
	}

	@Override
	public ResultSet selectProvidersRecord(Connection conn, int ppvid) throws SQLException {
		String sql = "select * from Providers where providerID=?";
		return super.selectDate(conn, sql, ppvid);
	}

	@Override
	public int deleteProvidersById(Connection conn, int id) throws SQLException {
		String sql = "delete from providers where providerID = ?";
		return super.updateDate(conn, sql, id);

	}

	@Override
	public int getTotalRecord(Connection conn) throws SQLException {
		String sql = "select count(providerID) from providers";
		ResultSet set = super.selectDate(conn, sql, null);
		if (set.next()) {
			return set.getInt(1);
		}
		return 0;
	}

	@Override
	public ResultSet selectProvidersByPage(Connection conn, Page<Providers> page) throws SQLException {
		String sql = "select * from providers limit ?,?";
		Object[] objects = { (page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize() };
		return super.selectDate(conn, sql, objects);

	}

	@Override
	public ResultSet selectProvidersRecord(Connection conn, String pvname, int id) throws SQLException {
		String sql = "select * from Providers where provider_name=?and providerID!=?";
		return super.selectDate(conn, sql, pvname, id);
	}
}
