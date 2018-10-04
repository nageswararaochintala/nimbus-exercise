package anthem.nimbus.config;


public interface ModelRepositoryFactory {

	public ModelRepository get(Repo repo);

	public ModelRepository get(Repo.Database db);
	
	public ModelPersistenceHandler getHandler(Repo repo);
}
