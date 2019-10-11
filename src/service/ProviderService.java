package service;

import entity.Providers;
import until.Page;

public interface ProviderService {
	// 增
	public void addProviders(Providers providers);

	// 查
	public Providers findProvidersRecord(String name);

	// 查
	public Providers findProvidersRecord(int id);

	// 改
	public void chagneProviders(Providers providers);

	// 删
	public void subProvidersById(int id);

	// 查
	public Page<Providers> fingProvidersByPage(Page<Providers> page);

	// 查
	public Providers findProvidersRecord(String name, int id);

}
