package anthem.nimbus.config;

import java.util.List;


public interface ModelPersistenceHandler {
	
	public boolean handle(List<ModelEvent<Param<?>>> modelEvents);
	
}
