package service;

import entity.Providers;
import until.Page;

public interface ProviderService {
	// ��
	public void addProviders(Providers providers);

	// ��
	public Providers findProvidersRecord(String name);

	// ��
	public Providers findProvidersRecord(int id);

	// ��
	public void chagneProviders(Providers providers);

	// ɾ
	public void subProvidersById(int id);

	// ��
	public Page<Providers> fingProvidersByPage(Page<Providers> page);

	// ��
	public Providers findProvidersRecord(String name, int id);

}
