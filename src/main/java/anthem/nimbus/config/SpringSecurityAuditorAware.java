package anthem.nimbus.config;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.AuditorAware;


/**
 * This class is used to provide the auditor information (currently it is a user name set during the authentication) for the spring auditing fields {@link CreatedBy} and {@link LastModifiedBy}
 * TODO if the authentication implementation is changed (e.g. store the whole user object instead of just the login name), this class needs to be updated as well
 *
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {
	
	private final SessionProvider sessionProvider;

	public SpringSecurityAuditorAware(BeanResolverStrategy beanResolver) {
		this.sessionProvider = beanResolver.get(SessionProvider.class);
	}	
	
	@Override
	public String getCurrentAuditor() {
		
		ClientUser loggedInUser = sessionProvider.getLoggedInUser();
		if(loggedInUser != null) {
			return loggedInUser.getLoginId();
		}
		
		return null;		
	}	
}
