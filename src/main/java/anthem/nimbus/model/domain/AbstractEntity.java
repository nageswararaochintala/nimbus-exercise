package anthem.nimbus.model.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.Ignore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all domain model.
 * Implements {@link Persistable} for the audit fields {@link CreatedBy} and {@link CreatedDate} since the {@link Id} field is manually assigned.
 * 
 */
@Model
@Getter
public abstract class AbstractEntity<ID extends Serializable> implements Serializable, Persistable<ID> {
	private static final long serialVersionUID = 1L;
	
	@Ignore
	private final String _class = this.getClass().getName(); 

	public static abstract class IdLong extends AbstractEntity<Long> {
		private static final long serialVersionUID = 1L;
		
		@Id @Getter @Setter 
		private Long id;
	}
	
    public static abstract class IdString extends AbstractEntity<String> {
		private static final long serialVersionUID = 1L;

		@Id @Getter @Setter @StartsWith
		private String id;
	}
	
	
    public abstract ID getId();

    @Setter @CreatedBy
	private String createdBy;
	
    @Setter @CreatedDate
	private ZonedDateTime createdDate;
	
    @Setter @LastModifiedBy
	private String lastModifiedBy;
	
    @Setter @LastModifiedDate
	private ZonedDateTime lastModifiedDate;
	
    //@Version
	@Setter private long version;
	
	
	@JsonIgnore
	public <T extends AbstractEntityBehavior<M, ID>, M extends AbstractEntity<ID>> T newBehaviorInstance(Class<T> clazz) {
		try {
			T t = ConstructorUtils.invokeExactConstructor(clazz, this);
			return t;
		} 
		catch (Exception ex) {
			throw new FrameworkRuntimeException("Failed to instantiate class of type: " + clazz, ex);
		}
	}
	
	@JsonIgnore
	@Override
	public boolean isNew() {
		return this.lastModifiedDate == null;
	}
	
}
