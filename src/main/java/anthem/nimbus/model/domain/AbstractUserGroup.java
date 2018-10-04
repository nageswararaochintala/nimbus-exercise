package anthem.nimbus.model.domain;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public abstract class AbstractUserGroup extends AbstractEntity.IdLong {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String displayName;
	
	private String description;
	
	private boolean admin;
	
	private String status;
	
	public enum Status {
		ACTIVE,
		INACTIVE
	}
}
