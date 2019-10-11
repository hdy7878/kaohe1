package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProviderDaoImp;
import entity.Providers;
import until.DBHelper;
import until.Page;

public class ProviderServiceImp implements ProviderService {
	private ProviderDaoImp pvd;

	public ProviderServiceImp() {
		pvd = new ProviderDaoImp();
	}

	@Override
	public void addProviders(Providers providers) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			int i = pvd.insertProviders(conn, providers);
			if (i == 1) {
				System.out.println("增加成功");
			} else {
				System.out.println("增加失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Providers findProvidersRecord(String name) {

		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			ResultSet set = pvd.selectProvidersRecord(conn, name);
			if (set.next()) {
				int providerID = set.getInt("providerID");
				String provider_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");
				return new Providers(providerID, provider_name, provider_add, provider_tel, account, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pvd.close();
		}
		return null;
	}

	@Override
	public void chagneProviders(Providers providers) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			int i = pvd.updateProviders(conn, providers);
			if (i == 1) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pvd.close();
		}
	}

	@Override
	public Providers findProvidersRecord(int id) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			ResultSet set = pvd.selectProvidersRecord(conn, id);
			if (set.next()) {
				int providerID = set.getInt("providerID");
				String provider_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");
				return new Providers(providerID, provider_name, provider_add, provider_tel, account, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pvd.close();
		}
		return null;
	}

	@Override
	public void subProvidersById(int id) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			int i = pvd.deleteProvidersById(conn, id);
			if (i == 1) {
				System.out.println("删除供应商成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pvd.close();
		}

	}

	@Override
	public Page<Providers> fingProvidersByPage(Page<Providers> page) {
		List<Providers> list = new ArrayList<Providers>();
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			ResultSet set = pvd.selectProvidersByPage(conn, page);
			int totalRecord = pvd.getTotalRecord(conn);
			while (set.next()) {
				int providerID = set.getInt("providerID");
				String provider_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");
				list.add(new Providers(providerID, provider_name, provider_add, provider_tel, account, email));
			}
			page.setList(list);
			page.setTotalRecord(totalRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pvd.close();
		}
		return page;
	}

	@Override
	public Providers findProvidersRecord(String name, int id) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			ResultSet set = pvd.selectProvidersRecord(conn, name, id);
			if (set.next()) {
				int providerID = set.getInt("providerID");
				String provider_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");
				return new Providers(providerID, provider_name, provider_add, provider_tel, account, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pvd.close();
		}
		return null;

	}

}
