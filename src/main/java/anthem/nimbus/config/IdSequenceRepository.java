package anthem.nimbus.config;

/**
 * Repository for auto sequence generator (for implementation check {@link MongoIdSequenceRepository})
 * Can be implemented for other data sources
 * 
 *
 */
public interface IdSequenceRepository {

	public long getNextSequenceId(String key) throws SequenceException;
}
