package com.antheminc.oss.nimbus.domain.model.state.repo.db.mongo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;

import com.antheminc.oss.nimbus.InvalidConfigException;
import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Model;
import com.antheminc.oss.nimbus.domain.model.state.EntityState.Param;
import com.antheminc.oss.nimbus.domain.model.state.ModelEvent;
import com.antheminc.oss.nimbus.domain.model.state.repo.ModelPersistenceHandler;
import com.antheminc.oss.nimbus.domain.model.state.repo.ModelRepository;
import com.antheminc.oss.nimbus.support.EnableLoggingInterceptor;
import com.antheminc.oss.nimbus.support.JustLogit;

import lombok.Getter;

@Getter
@EnableLoggingInterceptor
public class DefaultMongoModelPersistenceHandler implements ModelPersistenceHandler {

	JustLogit logit = new JustLogit(DefaultMongoModelPersistenceHandler.class);
	
	ModelRepository rep;
	
	public DefaultMongoModelPersistenceHandler(ModelRepository rep) {
		this.rep = rep;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean handle(List<ModelEvent<Param<?>>> modelEvents) {
		if(CollectionUtils.isEmpty(modelEvents)) 
			return false;
		
		for(ModelEvent<Param<?>> event: modelEvents) {
			logit.trace(()->"path: "+event.getPath()+ " action: "+event.getType()+" state: "+event.getPayload().getState());
			
			Param<?> param = event.getPayload();
			Model<Object> mRoot = (Model<Object>)param.getRootDomain();
			
			String alias = getRepoAlias(mRoot);
				
			Object coreStateId = mRoot.findParamByPath("/id").getState();
			if(coreStateId == null) {
				getRep()._new(mRoot.getConfig(), mRoot.getState());
				return true;
			}
			
			Serializable coreId = (Serializable)coreStateId; 
			
			String pPath = param.getBeanPath();
			Object pState = param.getState();
			getRep()._update(alias, coreId, pPath, pState);
			return true;
			
		}
		return false;
		
	}

	private String getRepoAlias(Model<Object> mRoot) {
		String alias = mRoot.getConfig().getRepo().alias();
		
		if(StringUtils.isBlank(alias)) {
			Class<Object> mRootClass = (Class<Object>)mRoot.getConfig().getReferredClass();
			alias = AnnotationUtils.findAnnotation(mRootClass, Domain.class).value();
			if(StringUtils.isBlank(alias)) {
				throw new InvalidConfigException("Core Persistent entity must be configured with "+Domain.class.getSimpleName()+" annotation. Not found for root model: "+mRoot);
			} 
		}
		return alias;
	}
	
}
