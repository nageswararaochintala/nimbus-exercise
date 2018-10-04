package anthem.nimbus.model.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Domain(value="clientusergroup", includeListeners={ListenerType.persistence})
@Repo(Database.rep_mongodb)
@Getter @Setter @ToString(callSuper=true)
public class ClientUserGroup extends AbstractUserGroup {

	private static final long serialVersionUID = 1L;
	
	private String organizationId;
	
	private List<GroupUser> members;
	
	private String[] memberUserIds;
	
	//TODO delete this attribute once the vgusergroup.drl is triggered on setState to set the count
	private Integer memberCount;
}
