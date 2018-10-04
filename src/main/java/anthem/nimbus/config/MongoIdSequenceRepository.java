package anthem.nimbus.config;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import lombok.Getter;

@Getter
public class MongoIdSequenceRepository implements IdSequenceRepository {

	MongoOperations mongoOperations;
	
	public MongoIdSequenceRepository(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	@Override
	public long getNextSequenceId(String key) throws SequenceException {
		//get sequence id
		Query query = new Query(Criteria.where("_id").is(key));
		
		//increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		//return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		options.upsert(true);
		
		DBSequence seqId = getMongoOperations().findAndModify(query, update, options, DBSequence.class, "sequence");

		//if no id, throws SequenceException]
		if (seqId == null) {
			throw new SequenceException("Unable to get sequence id for key : " + key);
		}

		return seqId.getSeq();
	}

}
